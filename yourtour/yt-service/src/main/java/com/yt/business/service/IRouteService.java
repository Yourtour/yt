package com.yt.business.service;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.bean.UserProfileBean;

import java.util.List;
import java.util.Map;

public interface IRouteService {
	public boolean isRouteMember(Long routeId, Long userId);

	/**
	 * 保存行程信息，包括行程计划内容以及日程信息；
	 * 
	 * @param route
	 * @param operatorId
	 * @throws Exception
	 */
	public void saveRouteInfo(RouteMainBean route, Long operatorId)
			throws Exception;

	/**
	 * 行程复制
	 * 
	 * @param sourceId
	 * @param target
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	public RouteMainBean cloneRoute(Long sourceId, RouteMainBean target,
			Long operatorId) throws Exception;

	/**
	 * 删除行程
	 * 
	 * @param routeId
	 * @param operatorId
	 * @throws Exception
	 */
	public void deleteRoute(Long routeId, Long operatorId) throws Exception;

	/**
	 * 根据指定的行程ID，返回行程概要信息。
	 *
	 * @param routeId
	 *            行程ID
	 * @return 完整的行程对象
	 * @throws Exception
	 *             获取数据过程中发生的异常
	 */
	public RouteMainBean getRouteMain(Long routeId) throws Exception;

	/**
	 * 根据指定的行程ID，返回完整的行程对象。
	 * 
	 * @param routeId
	 *            行程ID
	 * @return 完整的行程对象
	 * @throws Exception
	 *             获取数据过程中发生的异常
	 */
	public RouteMainBean getRoute(Long routeId) throws Exception;

	/**
	 * 获取指定行程的成员列表
	 * 
	 * @param routeId
	 *            行程ID
	 * @return 该行程的成员列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<UserProfileBean> getRouteMember(Long routeId) throws Exception;

	/**
	 * 获取指定目的地的推荐行程
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor,
			int limit) throws Exception;

	/**
	 * 查询行程
	 * @param nextCursor
	 * @param limit
	 * @param total
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long nextCursor, int limit, int total, Map<String, Object> params) throws Exception;

	/**
	 * 行程智能匹配，实现根据用户输入的行程条件，系统进行匹配并推荐
	 * 
	 * @param placeIds
	 * @param duration
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long[] placeIds, int duration,
			Long nextCursor, int limit) throws Exception;

	/**
	 * /** 根据指定的用户，返回该用户拥有的行程
	 * 
	 * @param userId
	 *            用户Id
	 * @return 指定用户拥有的行程列表
	 * @throws Exception
	 *             获取数据过程中发生的异常
	 */
	public List<RouteMainBean> getRoutes(Long userId) throws Exception;

	/**
	 * 
	 * @param schedule
	 * @param operatorId
	 * @throws Exception
	 */
	public void saveSchedule(RouteScheduleBean schedule, Long operatorId)
			throws Exception;

	/**
	 * 获取行程的日程安排
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	public List<RouteScheduleBean> getRouteSchedules(Long routeId) throws Exception;

	/**
	 * 删除行程日程安排
	 * 
	 * @param routeId
	 * @param scheduleId
	 * @param operatorId
	 * @throws Exception
	 */
	public void deleteSchedule(Long routeId, Long scheduleId, Long operatorId)
			throws Exception;

	/**
	 * 保存行程准备事项
	 * 
	 * @param routeId
	 * @param provision
	 * @throws Exception
	 */
	public void saveRouteProvision(Long routeId, RouteProvisionBean provision,
			Long operatorId) throws Exception;

	/**
	 * 删除行程准备事项
	 * 
	 * @param routeId
	 * @param provisionId
	 * @param operatorId
	 * @throws Exception
	 */
	public void deleteRouteProvision(Long routeId, Long provisionId,
			Long operatorId) throws Exception;
}
