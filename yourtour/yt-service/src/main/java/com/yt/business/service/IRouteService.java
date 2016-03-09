package com.yt.business.service;

import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;

import java.util.List;

public interface IRouteService {
	/**
	 *
	 * @param route
	 * @param userId
	 * @throws Exception
	 */
	public void saveRoute(RouteMainBean route, Long userId) throws Exception;

	/**
	 * 同时保存传输进来的行程基本信息和其中包含的日程信息
	 * @param route
	 * @param UserId
	 * @throws Exception
	 */
	public void saveRouteMainAndSchedules(RouteMainBean route, Long UserId)	throws Exception;

	/**
	 * 行程复制
	 * @param sourceId
	 * @param target
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public RouteMainBean cloneRoute(Long sourceId, RouteMainBean target, Long userId) throws Exception;

	/**
	 * 删除行程
	 * @param routeId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteRoute(Long routeId, Long userId) throws Exception;

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
	 * 获取指定目的地的推荐行程
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor, int limit) throws Exception;

	/**
	 * 行程智能匹配，实现根据用户输入的行程条件，系统进行匹配并推荐
	 * @param placeIds
	 * @param duration
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long[] placeIds, int duration, Long nextCursor, int limit) throws Exception;

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
	 * @param uid
	 * @throws Exception
	 */
	public void saveSchedule(RouteScheduleBean schedule, Long uid) throws Exception;

	/**
	 * 删除行程日程安排
	 * @param routeId
	 * @param scheduleId
	 * @param uid
	 * @throws Exception
	 */
	public void deleteSchedule(Long routeId, Long scheduleId, Long uid) throws Exception;

	/**
	 * 获取行程活动完整数据
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	public RouteActivityBean getScheduleActivity(Long activityId) throws Exception;

	/**
	 * 保存行程日程安排
	 * @param routeId
	 * @param activity
	 * @throws Exception
	 */
	public void saveScheduleActivity(Long routeId, RouteActivityBean activity, Long userId) throws Exception;

	/**
	 * 删除行程具体某项活动安排
	 * @param routeId
	 * @param activityId
	 * @param uid
	 * @throws Exception
	 */
	public void deleteScheduleActivity(Long routeId, Long activityId, Long uid) throws Exception;

	/**
	 * 删除日程活动安排中具体活动项
	 * @param routeId
	 * @param activityId
	 * @param itemId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteScheduleActivityItem(Long routeId, Long activityId, Long itemId, Long userId) throws Exception;

	/**
	 * 保存行程准备事项
	 * @param routeId
	 * @param provision
	 * @throws Exception
	 */
	public void saveRouteProvision(Long routeId, RouteProvisionBean provision, Long userId) throws Exception;

	/**
	 * 删除行程准备事项
	 * @param routeId
	 * @param provisionId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteRouteProvision(Long routeId, Long provisionId, Long userId) throws Exception;


}
