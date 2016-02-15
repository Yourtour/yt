package com.yt.rest.resource;

import com.yt.business.bean.*;
import com.yt.business.repository.RouteRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.WebUtils;
import com.yt.vo.route.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
@Path("routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteMainRestResource extends BaseRestResource {
	private static final Log LOG = LogFactory
			.getLog(RouteMainRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private RouteRepository routeRepository;

	/**
	 * 根据指定的用户ID，获取该用户的行程列表
	 * 
	 * @return 统一的ResponseVO数据对象，其中的data字段包括了行程数据列表。
	 */
	@GET
	@Path("/personal/query")
	public ResponseDataVO<List<RouteItemVO>> getOwnedRoutes(
			@Context HttpServletRequest request) {
		String userId = null;
		try {
			userId = super.getCurrentUserId(request);
			long userGraphId = Neo4jUtils.getGraphIDFromString(userId);

			// 根据用户ID获取对应的行程列表
			List<RouteMainBean> routes = routeRepository
					.getRoutesByOwner(userGraphId);
			List<RouteItemVO> list = new Vector<RouteItemVO>(routes.size());
			for (RouteMainBean route : routes) {
				if (route == null)
					continue;

				RouteItemVO vo = new RouteItemVO(route);
				list.add(vo);
			}
			return new ResponseDataVO<List<RouteItemVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch RouteMainBean by UserBean[id='%s'] fail.",
						userId), ex);
			}
			return new ResponseDataVO<List<RouteItemVO>>(
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
	public ResponseDataVO<RouteLoadVO> getRoute(@PathParam("id") String id) {
		long graphId = Neo4jUtils.getGraphIDFromString(id);
		try {
			RouteMainBean bean = null;
			if (graphId != -1) {
				// id是GraphID
				bean = (RouteMainBean) routeRepository
						.getCompleteRoute(graphId);
			}
			if (bean == null) {
				return new ResponseDataVO<RouteLoadVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
			id = bean.getRowKey();
			RouteLoadVO vo = new RouteLoadVO(bean);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Get RouteMainBean['%s'] success.", id));
			}
			return new ResponseDataVO<RouteLoadVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Fetch RouteMainBean[id='%s'] fail.", id),
						ex);
			}
			return new ResponseDataVO<RouteLoadVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 保存行程基本值对象到图数据库
	 * 
	 * @param vo
	 *            行程值对象
	 * @return 统一的ResponseVO对象
	 */
	@POST
	@Path("main/save")
	public ResponseDataVO<RouteLoadVO> saveMain(RouteVO vo) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteVO is null.");
			}
			return new ResponseDataVO<RouteLoadVO>(
					StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteMainBean bean = RouteVO.transform(vo);

			UserProfileBean profileBean = null;
			long userGraphId = Neo4jUtils.getGraphIDFromString(super
					.getCurrentUserId());
			if (userGraphId != -1) {
				profileBean = (UserProfileBean) routeRepository.get(
						UserProfileBean.class, userGraphId, false);
				bean.setOwner(profileBean);
			}

			routeRepository.saveRouteMainAndSchedules(bean,
					WebUtils.getCurrentLoginUser());
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Save RouteMainBean['%s'] success.",
						vo.getRowKey()));
			}

			RouteLoadVO loadvo = new RouteLoadVO(bean);
			return new ResponseDataVO<RouteLoadVO>(loadvo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format("Save the RouteMainBean[id='%s'] fail.",
								vo.getRowKey()), ex);
			}
			return new ResponseDataVO<RouteLoadVO>(
					StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 行程复制接口
	 * 
	 * @param sourceId
	 * @param vo
	 * @return
	 */
	@POST
	@Path("/{sourceId}/clone")
	public ResponseDataVO<RouteLoadVO> cloneRoute(
			@PathParam("sourceId") String sourceId, RouteVO vo) {
		try {
			String userId = super.getCurrentUserId();

			RouteMainBean bean = RouteVO.transform(vo);

			UserProfileBean profileBean = null;
			long userGraphId = Neo4jUtils.getGraphIDFromString(super
					.getCurrentUserId());
			if (userGraphId != -1) {
				profileBean = (UserProfileBean) routeRepository.get(
						UserProfileBean.class, userGraphId, false);
				bean.setOwner(profileBean);
			}

			RouteMainBean route = routeRepository.cloneRoute(
					Long.valueOf(sourceId), bean, userId);

			RouteLoadVO loadVo = new RouteLoadVO(route);

			return new ResponseDataVO<RouteLoadVO>(loadVo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("System Exception.", ex);
			}
			return new ResponseDataVO<RouteLoadVO>(
					StaticErrorEnum.DB_OPERATE_FAIL);
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
	@Path("/{routeId}/schedule/save")
	public ResponseVO saveSchedule(@PathParam("routeId") String routeId,
			RouteScheduleVO vo, @Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteScheduleVO is null.");
			}
			return new ResponseVO(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteScheduleBean bean = null;
			if (vo.getId() == null) {
				bean = RouteScheduleVO.transform(vo);
			} else {
				bean = (RouteScheduleBean) routeRepository.get(
						RouteScheduleBean.class, vo.getId());
				bean.setMemo(vo.getMemo());
			}

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
	@Path("/{routeId}/activity/save")
	public ResponseDataVO<Long> saveScheduleActivity(
			@PathParam("routeId") String routeId, RouteActivityVO vo,
			@Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteActivityVO is null.");
			}
			return new ResponseDataVO<Long>(StaticErrorEnum.THE_INPUT_IS_NULL);
		}

		try {
			RouteActivityBean bean = RouteActivityVO.transform(vo);
			routeRepository.save(bean, WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save RouteActivityBean['%s'] success.", vo.getRowKey()));
			}
			return new ResponseDataVO<Long>(bean.getGraphId());
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the RouteActivityBean[id='%s'] fail.",
						vo.getRowKey()), ex);
			}
			return new ResponseDataVO<Long>(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	/**
	 * 
	 * @param activityId
	 * @param itemId
	 * @return
	 */
	@POST
	@Path("/activity/{activityId}/item/{itemId}/save")
	public ResponseDataVO<Long> saveScheduleActivityItem(
			@PathParam("activityId") String activityId,
			@PathParam("itemId") String itemId) {
		try {
			RouteActivityBean activity = (RouteActivityBean) routeRepository
					.get(RouteActivityBean.class,
							Neo4jUtils.getGraphIDFromString(activityId));
			if (activity == null) {
				return new ResponseDataVO<Long>(
						StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			ResourceActivityItemBean item = (ResourceActivityItemBean) routeRepository
					.get(ResourceActivityItemBean.class,
							Neo4jUtils.getGraphIDFromString(itemId));
			if (item == null) {
				return new ResponseDataVO<Long>(
						StaticErrorEnum.FETCH_DB_DATA_FAIL);
			}

			RouteActivityItemBean activityItem = new RouteActivityItemBean(
					WebUtils.getCurrentLoginUser());
			activityItem.setActivity(activity);
			activityItem.setResourceActivityItem(item);
			activityItem.setResourceActivityItemId(item.getGraphId());

			routeRepository.save(activityItem, WebUtils.getCurrentLoginUser());

			return new ResponseDataVO<Long>(activityItem.getGraphId());
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("System Exception", ex);
			}
			return new ResponseDataVO<Long>(StaticErrorEnum.DB_OPERATE_FAIL);
		}
	}

	@GET
	@Path("activity/{activityId}")
	public ResponseDataVO<RouteActivityVO> getActivity(
			@PathParam("activityId") String activityId,
			@Context HttpServletRequest request) {
		try {
			RouteActivityBean activity = routeRepository
					.getCompleteActivity(Long.valueOf(activityId));
			return new ResponseDataVO<>(RouteActivityVO.transform(activity));
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format("Get the RouteActivity[id='%s'] fail.",
						activityId), ex);
			}
			return new ResponseDataVO<>(StaticErrorEnum.DB_OPERATE_FAIL);
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
	@Path("/{routeId}/provision/save")
	public ResponseDataVO<Long> saveProvision(
			@PathParam("routeId") String routeId, RouteProvisionVO vo,
			@Context HttpServletRequest request) {
		if (vo == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The RouteProvisionVO is null.");
			}
			return new ResponseDataVO<>(StaticErrorEnum.THE_INPUT_IS_NULL);
		}
		try {
			RouteProvisionBean bean = RouteProvisionVO.transform(vo);
			routeRepository.save(bean, WebUtils.getCurrentLoginUser(request));
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Save RouteProvisionBean['%s'] success.",
						vo.getRowKey()));
			}
			return new ResponseDataVO<>(bean.getGraphId());
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Save the RouteProvisionBean[id='%s'] fail.",
						vo.getRowKey()), ex);
			}
			return new ResponseDataVO<>(StaticErrorEnum.DB_OPERATE_FAIL);
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
	public ResponseVO deleteRoute(@PathParam("id") String id) {
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

	@POST
	@Path("/activity/item/{itemId}/delete")
	public ResponseDataVO<Long> deleteActivityItem(
			@PathParam("itemId") String itemId) {
		try {
			RouteActivityItemBean itemBean = (RouteActivityItemBean) routeRepository
					.get(RouteActivityItemBean.class,
							Neo4jUtils.getGraphIDFromString(itemId), false);

			this.routeRepository.delete(itemBean);

			return new ResponseDataVO<Long>(itemBean.getGraphId());
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<Long>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	/**
	 * 
	 * @param serviceId
	 * @return
	 */
	@POST
	@Path("/service/{serviceId}/delete")
	public ResponseDataVO<Long> deleteService(
			@PathParam("serviceId") String serviceId) {
		try {
			RouteServiceBean routeService = (RouteServiceBean) routeRepository
					.get(RouteServiceBean.class, Long.valueOf(serviceId), false);

			this.routeRepository.delete(routeService);

			return new ResponseDataVO<Long>(routeService.getGraphId());
		} catch (Exception ex) {
			LOG.error("Exception raised when saving service.", ex);
			return new ResponseDataVO<Long>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@SuppressWarnings("unchecked")
	@Path("/recommend/{placeIds}/{duration}")
	@GET
	public ResponseDataVO<List<RouteRecommendVO>> getRecommendedRoutes(
			@PathParam("placeIds") String placeIds,
			@PathParam("duration") String duration) {
		List<RouteRecommendVO> list = new ArrayList<RouteRecommendVO>();
		try {
			String[] ids = placeIds.split(",");
			Long[] lIds = new Long[ids.length];
			for (int index = 0; index < ids.length; index++) {
				lIds[index] = Long.valueOf(ids[index]);
			}

			List<RouteMainBean> result = (List<RouteMainBean>) routeRepository
					.getRecommendRoutes(lIds);
			for (RouteMainBean bean : result) {
				if (bean == null) {
					continue;
				}
				RouteRecommendVO vo = RouteRecommendVO.transform(bean);
				list.add(vo);
			}

			return new ResponseDataVO<List<RouteRecommendVO>>(list);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the LineBean fail.", ex);
			}
			return new ResponseDataVO<List<RouteRecommendVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@Path("/recommend/{routeId}")
	@GET
	public ResponseDataVO<RouteRecommendVO> getRecommendedRoute(
			@PathParam("routeId") String routeId) {
		try {
			RouteMainBean bean = (RouteMainBean) routeRepository
					.getCompleteRoute(Long.valueOf(routeId));

			RouteRecommendVO vo = RouteRecommendVO.transform(bean);
			return new ResponseDataVO<RouteRecommendVO>(vo);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch all the LineBean fail.", ex);
			}
			return new ResponseDataVO<RouteRecommendVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}
}
