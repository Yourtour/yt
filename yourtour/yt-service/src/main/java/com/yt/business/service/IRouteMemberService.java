package com.yt.business.service;

import com.yt.business.bean.RouteMemberBean;

import java.util.List;

public interface IRouteMemberService {
	/**
	 * 添加组成员
	 * @param routeId 行程
	 * @param userId  用户ID
	 * @param member  成员属性
	 * @param operatorId 操作者ID
	 * @throws Exception
	 */
	public void saveMember(Long routeId, Long userId, RouteMemberBean member, Long operatorId) throws Exception;

	/**
	 * 删除组成员
	 * @param routeId 行程
	 * @param userId 用户ID
	 * @param operatorId 操作者ID
	 * @throws Exception
	 */
	public void deleteMember(Long routeId, Long userId, Long operatorId) throws Exception;

	/**
	 * 获取行程成员
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	public List<RouteMemberBean> getMembers(Long routeId) throws Exception;
}
