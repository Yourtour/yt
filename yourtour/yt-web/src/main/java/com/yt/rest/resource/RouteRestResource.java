package com.yt.rest.resource;

import com.yt.business.bean.*;
import com.yt.business.service.IRouteService;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.SessionUtils;
import com.yt.vo.route.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
@Path("app/routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteRestResource extends RestResource {
	private static final Log LOG = LogFactory
			.getLog(RouteRestResource.class);

	// spring自动装配的行程操作库
	@Autowired
	private IRouteService routeService;

	/**
	 * 根据指定的用户ID，获取该用户的行程列表
	 * 
	 * @return 统一的ResponseVO数据对象，其中的data字段包括了行程数据列表。
	 */
	@GET
	@Path("/personal/query")
	public ResponseDataVO<List<RouteItemVO>> getOwnedRoutes() throws Exception {
			long uid = SessionUtils.getCurrentLoginUser();

			// 根据用户ID获取对应的行程列表
			List<RouteMainBean> routes = routeService.getRoutesByOwner(uid);
			List<RouteItemVO> list = new Vector<RouteItemVO>(routes.size());
			for (RouteMainBean route : routes) {
				if (route == null)
					continue;

				RouteItemVO vo = new RouteItemVO(route);
				list.add(vo);
			}
			return new ResponseDataVO<List<RouteItemVO>>(list);
	}

	/**
	 * 根据指定的ID获取对应的行程数据
	 * 
	 * @param rid 行程ID，该ID可能是GrphId，也可能是RowKey
	 * @return 行程数据值对象，如果指定行程不存在，则行程数据值对象中的data为空。
	 */
	@GET
	@Path("{rid}/query")
	public ResponseDataVO<RouteLoadVO> getRoute(@PathParam("rid") Long rid) throws Exception {
			RouteMainBean bean = routeService.getCompleteRoute(rid);

			RouteLoadVO vo = new RouteLoadVO(bean);
			return new ResponseDataVO<RouteLoadVO>(vo);
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
	public ResponseDataVO<RouteLoadVO> saveMain(RouteVO vo) throws Exception{
		RouteMainBean bean = RouteVO.transform(vo);

		UserProfileBean profileBean = new UserProfileBean(SessionUtils.getCurrentLoginUser());
		bean.setOwner(profileBean);

		routeService.saveRouteMainAndSchedules(bean,SessionUtils.getCurrentLoginUser());

		RouteLoadVO loadvo = new RouteLoadVO(bean);
		return new ResponseDataVO<RouteLoadVO>(loadvo);
	}

	/**
	 * 行程复制接口
	 * 
	 * @param sourceId
	 * @param vo
	 * @return
	 */
	@POST
	@Path("/{routeId}/clone")
	public ResponseDataVO<RouteLoadVO> cloneRoute(@PathParam("routeId") Long routeId, RouteVO vo) throws Exception{
			Long uid = super.getCurrentUserId();

			RouteMainBean bean = RouteVO.transform(vo);
			UserProfileBean profileBean = new UserProfileBean(uid);
			bean.setOwner(profileBean);

			RouteMainBean route = routeService.cloneRoute(routeId, bean, uid);

			RouteLoadVO loadVo = new RouteLoadVO(route);

			return new ResponseDataVO<RouteLoadVO>(loadVo);
	}

	/**
	 * 保存行程日程值对象到图数据库
	 * @param routeId
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/{routeId}/schedule/save")
	public ResponseDataVO<RouteScheduleVO> saveSchedule(@PathParam("routeId") Long routeId,RouteScheduleVO vo) throws Exception{
		RouteScheduleBean bean = RouteScheduleVO.transform(vo);
		routeService.saveSchedule(bean, SessionUtils.getCurrentLoginUser());
		return new ResponseDataVO<>();
	}

	/**
	 * 保存行程活动值对象到图数据库
	 * @param routeId
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/{routeId}/activity/save")
	public ResponseDataVO<Long> saveScheduleActivity(@PathParam("routeId") Long routeId, RouteActivityVO vo) throws Exception {
		RouteActivityBean bean = RouteActivityVO.transform(vo);

		routeService.saveScheduleActivity(routeId, bean, SessionUtils.getCurrentLoginUser());
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Save RouteActivityBean['%s'] success.", vo.getRowKey()));
		}
		return new ResponseDataVO<Long>(bean.getId());
	}

	@GET
	@Path("activity/{activityId}")
	public ResponseDataVO<RouteActivityVO> getScheduleActivity(@PathParam("activityId") Long activityId) throws Exception {
		RouteActivityBean activity = routeService.getScheduleActivity(activityId);
		return new ResponseDataVO<>(RouteActivityVO.transform(activity));
	}

	/**
	 * 保存行程事项
	 * @param routeId
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/{routeId}/provision/save")
	public ResponseDataVO<Long> saveProvision(@PathParam("routeId") Long routeId, RouteProvisionVO vo) throws Exception{
		RouteProvisionBean bean = RouteProvisionVO.transform(vo);
		routeService.saveRouteProvision(routeId, bean, SessionUtils.getCurrentLoginUser());
		return new ResponseDataVO<>(bean.getId());
	}

	/**
	 * 根据指定的行程ID删除行程
	 * 
	 * @param routeId
	 *            行程ID，该ID可能是GrphId，也可能是RowKey
	 * @return 统一的ResponseVO对象
	 */
	@GET
	@Path("{routeId}/delete")
	public ResponseVO deleteRoute(@PathParam("routeId") Long routeId) throws Exception{
		routeService.deleteRoute(routeId, SessionUtils.getCurrentLoginUser());
		return new ResponseVO();
	}

	/**
	 * 删除指定的行程日程
	 * @param routeId
	 * @param scheduleId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/routeId/schedule/{scheduleId}/delete")
	public ResponseVO deleteSchedule(@PathParam("routeId") Long routeId, @PathParam("scheduleId") Long scheduleId) throws Exception{
		routeService.deleteSchedule(routeId, scheduleId, SessionUtils.getCurrentLoginUser());
		return new ResponseVO();
	}

	/**
	 * 根据指定的行程活动项
	 * @param routeId
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{routeId}/activity/{activityId}/delete")
	public ResponseVO deleteScheduleActivity(@PathParam("routeId") Long routeId, @PathParam("activityId") Long activityId) throws Exception{
			routeService.deleteScheduleActivity(routeId, activityId, SessionUtils.getCurrentLoginUser());
			return new ResponseVO();
	}

	/**
	 * 删除行程准备事项
	 * @param routeId
	 * @param provisionId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{routeId}/provision/{provisionId}/delete")
	public ResponseVO deleteProvision(@PathParam("routeId") Long routeId, @PathParam("provisionId") Long provisionId) throws Exception{
		routeService.deleteRouteProvision(routeId, provisionId, SessionUtils.getCurrentLoginUser());
		return new ResponseVO();
	}

	/**
	 * 删除行程中活动安排中活动相
	 * @param routeId
	 * @param activityId
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/{routeId}/activity/{activityId}/item/{itemId}/delete")
	public ResponseVO deleteScheduleActivityItem(@PathParam("routeId") Long routeId,
														   @PathParam("activityId") Long activityId,
														   @PathParam("itemId") Long itemId) throws Exception {
		this.routeService.deleteScheduleActivityItem(routeId, activityId, itemId, SessionUtils.getCurrentLoginUser());
		return new ResponseVO();
	}

	@SuppressWarnings("unchecked")
	@Path("/recommend/{placeIds}/{duration}")
	@GET
	public ResponseDataVO<List<RouteVO>> getRecommendedRoutes(@PathParam("placeIds") String placeIds,
															  @PathParam("duration") String duration)
	throws Exception {
		List<RouteVO> list = new ArrayList<RouteVO>();
		String[] ids = placeIds.split(",");
		Long[] lIds = new Long[ids.length];
		for (int index = 0; index < ids.length; index++) {
			lIds[index] = Long.valueOf(ids[index]);
		}

		List<RouteMainBean> result = (List<RouteMainBean>) routeService
				.getRecommendRoutes(lIds);
		for (RouteMainBean bean : result) {
			if (bean == null) {
				continue;
			}
			RouteVO vo = RouteVO.transform(bean);
			list.add(vo);
		}

		return new ResponseDataVO<List<RouteVO>>(list);
	}
}
