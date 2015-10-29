package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.bean.UserBean;
import com.yt.business.repository.RouteRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.route.RouteActivityVO;
import com.yt.vo.route.RouteProvisionVO;
import com.yt.vo.route.RouteScheduleVO;
import com.yt.vo.route.RouteVO;

@Component
@Path("routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteRestResource {
	private static final Log LOG = LogFactory.getLog(RouteRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteRepository routeRepository;

	/**
	 * 根据指定的用户ID，获取该用户的行程列表
	 * 
	 * @param userId
	 *            用户ID，该ID可能是GrphId，也可能是RowKey
	 * @return 统一的ResponseVO数据对象，其中的data字段包括了行程数据列表。
	 */
	@GET
	@Path("user/{id}/query")
	public ResponseDataVO<List<RouteVO>> getRoutesByUser(
			@PathParam("id") String userId) {
		long graphId = Neo4jUtils.getGraphIDFromString(userId);
		try {
			UserBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (UserBean) routeRepository.get(RouteMainBean.class,
						graphId, false);
			} else {
				// id 是rowkey
				bean = (UserBean) routeRepository.get(UserBean.class, "rowKey",
						userId, false);
			}
			if (bean == null) {
				return new ResponseDataVO<List<RouteVO>>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			// 根据用户ID获取对应的行程列表
			List<RouteMainBean> routes = routeRepository.getRoutesByOwner(bean
					.getGraphId());
			List<RouteVO> list = new Vector<RouteVO>(routes.size());
			for (RouteMainBean route : routes) {
				RouteVO vo = RouteVO.transform(route);
				if (vo == null) {
					continue;
				}
				list.add(vo);
			}
			return new ResponseDataVO<List<RouteVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RouteMainBean by UserBean[id='%s'] fail.",
						userId), ex);
			}
			return new ResponseDataVO<List<RouteVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 根据指定的ID获取对应的行程数据
	 * 
	 * @param id
	 *            行程ID，该ID可能是GrphId，也可能是RowKey
	 * @return 行程数据值对象，如果指定行程不存在，则行程数据值对象中的data为空。
	 */
	@GET
	@Path("{id}/query")
	public ResponseDataVO<RouteVO> getRoute(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteMainBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RouteMainBean) routeRepository.get(RouteMainBean.class,
						graphId);
			} else {
				// id 是rowkey
				bean = (RouteMainBean) routeRepository.get(RouteMainBean.class,
						"rowKey", id);
			}
			if (bean == null) {
				return new ResponseDataVO<RouteVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			id = bean.getRowKey();
			RouteVO vo = RouteVO.transform(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Get RouteMainBean['%s'] success.", id));
			}
			return new ResponseDataVO<RouteVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Fetch RouteMainBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseDataVO<RouteVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存行程基本信息和日程信息值对象到图数据库
	 * 
	 * @param vo
	 *            行程值对象，其中包括了行程日程信息
	 * @param request
	 *            本次的HttpServletRequest对象
	 * @return 统一的ResponseVO对象
	 */
	@POST
	@Path("main_schedule/save")
	public ResponseVO saveMainAndSchedules(RouteVO vo,
			@Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteMainBean bean = RouteVO.transform(vo);
			// 详细转换日程对象，并填充到基本对象中
			bean.getSchedules().clear();
			for (RouteScheduleVO scheduleVO : vo.getSchedules()) {
				RouteScheduleBean scheduleBean = RouteScheduleVO
						.transform(scheduleVO);
				if (scheduleBean == null) {
					continue;
				}
				bean.getSchedules().add(scheduleBean);
			}
			routeRepository.saveRouteMainAndSchedules(bean,
					WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Save RouteMainBean['%s'] and some RouteSchedules(%d items) success.",
								vo.getRowKey(), bean.getSchedules().size()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the RouteMainBean[id='%s'] fail.",
								vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 保存行程基本值对象到图数据库
	 * 
	 * @param vo
	 *            行程值对象
	 * @param request
	 *            本次的HttpServletRequest对象
	 * @return 统一的ResponseVO对象
	 */
	@POST
	@Path("main/save")
	public ResponseVO saveMain(RouteVO vo, @Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteMainBean bean = RouteVO.transform(vo);
			routeRepository.save(bean, WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save RouteMainBean['%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the RouteMainBean[id='%s'] fail.",
								vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 保存行程日程值对象到图数据库
	 * 
	 * @param vo
	 *            行程日程值对象
	 * @param request
	 *            本次的HttpServletRequest对象
	 * @return 统一的ResponseVO对象
	 */
	@POST
	@Path("schedule/save")
	public ResponseVO saveSchedule(RouteScheduleVO vo,
			@Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteScheduleVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteScheduleBean bean = RouteScheduleVO.transform(vo);
			routeRepository.save(bean, WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save RouteScheduleBean['%s'] success.", vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the RouteScheduleBean[id='%s'] fail.",
						vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 保存行程活动值对象到图数据库
	 * 
	 * @param vo
	 *            行程活动值对象
	 * @param request
	 *            本次的HttpServletRequest对象
	 * @return 统一的ResponseVO对象
	 */
	@POST
	@Path("activity/save")
	public ResponseVO saveActivity(RouteActivityVO vo,
			@Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteActivityVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteActivityBean bean = RouteActivityVO.transform(vo);
			routeRepository.save(bean, WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save RouteActivityBean['%s'] success.", vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the RouteActivityBean[id='%s'] fail.",
						vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 保存行程准备值对象到图数据库
	 * 
	 * @param vo
	 *            行程准备值对象
	 * @param request
	 *            本次的HttpServletRequest对象
	 * @return 统一的ResponseVO对象
	 */
	@POST
	@Path("provision/save")
	public ResponseVO saveProvision(RouteProvisionVO vo,
			@Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteProvisionVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteProvisionBean bean = RouteProvisionVO.transform(vo);
			routeRepository.save(bean, WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save RouteProvisionBean['%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the RouteProvisionBean[id='%s'] fail.",
						vo.getRowKey()), ex);
			}
			return new ResponseVO(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 根据指定的行程ID删除行程
	 * 
	 * @param id
	 *            行程ID，该ID可能是GrphId，也可能是RowKey
	 * @return 统一的ResponseVO对象
	 */
	@GET
	@Path("{id}/delete")
	public ResponseVO delete(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteMainBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RouteMainBean) routeRepository.get(RouteMainBean.class,
						graphId);
			} else {
				// id是rowkey
				bean = (RouteMainBean) routeRepository.get(RouteMainBean.class,
						"rowKey", id);
			}
			id = bean.getRowKey();
			routeRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete RouteMainBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Fetch RouteMainBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 根据指定的行程日程ID删除行程安排
	 * 
	 * @param id
	 *            行程日程ID，该ID可能是GrphId，也可能是RowKey
	 * @return 统一的ResponseVO对象
	 */
	@GET
	@Path("schedule/{id}/delete")
	public ResponseVO deleteSchedule(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteScheduleBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RouteScheduleBean) routeRepository.get(
						RouteScheduleBean.class, graphId);
			} else {
				// id是rowkey
				bean = (RouteScheduleBean) routeRepository.get(
						RouteScheduleBean.class, "rowKey", id);
			}
			id = bean.getRowKey();
			routeRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete RouteScheduleBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RouteScheduleBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 根据指定的行程活动ID删除行程安排
	 * 
	 * @param id
	 *            行程活动ID，该ID可能是GrphId，也可能是RowKey
	 * @return 统一的ResponseVO对象
	 */
	@GET
	@Path("activity/{id}/delete")
	public ResponseVO deleteActivity(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteActivityBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RouteActivityBean) routeRepository.get(
						RouteActivityBean.class, graphId);
			} else {
				// id是rowkey
				bean = (RouteActivityBean) routeRepository.get(
						RouteActivityBean.class, "rowKey", id);
			}
			id = bean.getRowKey();
			routeRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete RouteActivityBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RouteActivityBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 根据指定的行程准备ID删除行程安排
	 * 
	 * @param id
	 *            行程准备ID，该ID可能是GrphId，也可能是RowKey
	 * @return 统一的ResponseVO对象
	 */
	@GET
	@Path("provision/{id}/delete")
	public ResponseVO deleteProvision(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteProvisionBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RouteProvisionBean) routeRepository.get(
						RouteProvisionBean.class, graphId);
			} else {
				// id是rowkey
				bean = (RouteProvisionBean) routeRepository.get(
						RouteProvisionBean.class, "rowKey", id);
			}
			id = bean.getRowKey();
			routeRepository.delete(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete RouteProvisionBean[id='%s'] success.", id));
			}
			return new ResponseVO();
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RouteProvisionBean[id='%s'] fail.", id), ex);
			}
			return new ResponseVO(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

}
