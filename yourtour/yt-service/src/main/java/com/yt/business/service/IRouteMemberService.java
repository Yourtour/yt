package com.yt.business.service;

import com.yt.business.bean.RouteMemberBean;

import java.util.List;

public interface IRouteMemberService {
	/**
	 * 添加组成员
	 * @param member
	 * @param userId
	 * @throws Exception
	 */
	public void saveMember(RouteMemberBean member, Long userId) throws Exception;

	/**
	 * 删除组成员
	 * @param routeId
	 * @param muid
	 * @param userId
	 * @throws Exception
	 */
	public void deleteMember(Long routeId, Long muid, Long userId) throws Exception;

	/**
	 * 获取行程成员
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	public List<RouteMemberBean> getMembers(Long routeId) throws Exception;
}
