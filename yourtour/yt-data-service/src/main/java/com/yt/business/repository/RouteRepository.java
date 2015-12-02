package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.RouteMainBean;

public interface RouteRepository extends CrudAllInOneOperate {
	/**
	 * 根据指定的行程ID，返回完整的行程对象。
	 * 
	 * @param routeId
	 *            行程ID
	 * @return 完整的行程对象
	 * @throws Exception
	 *             获取数据过程中发生的异常
	 */
	public RouteMainBean getCompleteRoute(Long routeId) throws Exception;

	/**
	 * /** 根据指定的用户，返回该用户拥有的行程
	 * 
	 * @param userId
	 *            用户Id
	 * @return 指定用户拥有的行程列表
	 * @throws Exception
	 *             获取数据过程中发生的异常
	 */
	public List<RouteMainBean> getRoutesByOwner(Long userId) throws Exception;

	/**
	 * 同时保存传输进来的行程基本信息和其中包含的日程信息
	 * 
	 * @param route
	 *            行程对象，其中包括了要保存的日程信息
	 * @param operator
	 *            保存操作者ID
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void saveRouteMainAndSchedules(RouteMainBean route, String operator)
			throws Exception;
}
