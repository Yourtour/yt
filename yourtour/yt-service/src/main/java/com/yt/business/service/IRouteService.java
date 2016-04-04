package com.yt.business.service;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.*;

import java.util.List;
import java.util.Map;

public interface IRouteService {


	/**
	 * 保存行程信息，包括行程计划内容以及日程信息；
	 * 
	 * @param route
	 * @param operatorId
	 * @throws Exception
	 */
	public void saveRouteInfo(RouteMainBean route, Long operatorId, String relation) throws Exception;

	/**
	 * 行程复制
	 * 
	 * @param sourceId
	 * @param target
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	public RouteMainBean cloneRouteInfo(Long sourceId, RouteMainBean target, Long operatorId, String relation) throws Exception;

	/**
	 * 删除行程
	 * 
	 * @param routeId
	 * @param operatorId
	 * @throws Exception
	 */
	public void deleteRouteInfoes(Long[] routeId, Long operatorId) throws Exception;

	/**
	 * 根据指定的行程ID，返回完整的行程对象。
	 * @param routeId
	 * @param mode  0:仅仅获取行程概要信息 1：在0的基础上获取行程日程安排 2：在1的基础上获取行程关联的资源
	 * @return
	 * @throws Exception
	 */
	public RouteMainBean getRouteInfo(Long routeId, int mode) throws Exception;

	/**
	 * 查询行程(带用户输入查询条件)
	 * @param userId
	 * @param condition
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<RouteMainBean>> getUserRouteInfoes(Long userId, String relationship, PagingConditionBean condition,  Map<String, Object> params) throws Exception;

	/**
	 * 查询行程(不带用户输入查询条件)
	 * @param userId
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<RouteMainBean>> getUserRouteInfoes(Long userId, String relationship, PagingConditionBean condition) throws Exception;

	/**
	 * 目的地下行程检索
	 * @param placeId
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<RouteMainBean>> getPlaceRouteInfoes(Long placeId, PagingConditionBean condition) throws Exception;

	/**
	 * 行程智能匹配，实现根据用户输入的行程条件，系统进行匹配并推荐
	 * @param placeIds
	 * @param duration
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<RouteMainBean>> getPlaceRouteInfoes(Long[] placeIds, int duration, PagingConditionBean condition) throws Exception;

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
	 *
	 * @param routeId
	 * @param userId
	 * @return
	 */
	public boolean isRouteMember(Long routeId, Long userId);

	/**
	 * 
	 * @param schedule
	 * @param operatorId
	 * @throws Exception
	 */
	public void saveRouteSchedule(RouteScheduleBean schedule, Long operatorId) throws Exception;

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
	public void deleteRouteSchedule(Long routeId, Long scheduleId, Long operatorId)
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
