package com.yt.business.service;

import java.util.List;

import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.bean.UserProfileBean;

public interface IRouteService {
	public boolean isRouteMember(Long routeId, Long userId);

	/**
	 * 保存行程
	 * 
	 * @param route
	 * @param operatorId
	 * @throws Exception
	 */
	public void saveRoute(RouteMainBean route, Long operatorId)
			throws Exception;

	/**
	 * 保存行程信息，包括行程计划内容以及日程信息；
	 * 
	 * @param route
	 * @param operatorId
	 * @throws Exception
	 */
	public void saveRouteMainAndSchedules(RouteMainBean route, Long operatorId)
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
	 * 获取行程活动完整数据
	 * 
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	public RouteActivityBean getScheduleActivity(Long activityId)
			throws Exception;

	/**
	 * 保存行程日程安排
	 * 
	 * @param routeId
	 * @param activity
	 * @throws Exception
	 */
	public void saveScheduleActivity(Long routeId, RouteActivityBean activity,
			Long operatorId) throws Exception;

	/**
	 * 删除行程具体某项活动安排
	 * 
	 * @param routeId
	 * @param activityId
	 * @param operatorId
	 * @throws Exception
	 */
	public void deleteScheduleActivity(Long routeId, Long activityId,
			Long operatorId) throws Exception;

	/**
	 * 删除日程活动安排中具体活动项
	 * 
	 * @param routeId
	 * @param activityId
	 * @param itemId
	 * @param operatorId
	 * @throws Exception
	 */
	public void deleteScheduleActivityItem(Long routeId, Long activityId,
			Long itemId, Long operatorId) throws Exception;

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
