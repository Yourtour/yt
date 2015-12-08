package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.RouteMemberBean;

public interface RouteMemberRepository extends CrudAllInOneOperate {
	/**
	 * /** 根据指定的用户，返回该用户拥有的行程
	 * 
	 * @param userId
	 *            用户Id
	 * @return 指定用户拥有的行程列表
	 * @throws Exception
	 *             获取数据过程中发生的异常
	 */
	public List<RouteMemberBean> getRouteMembers(Long routeId) throws Exception;
}
