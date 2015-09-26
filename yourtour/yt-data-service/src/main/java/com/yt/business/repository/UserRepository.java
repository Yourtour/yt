package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.UserBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface UserRepository extends ICrudOperate {
	public UserBean getUserByRowkey(String rowKey) throws Exception;

	public UserBean getUserByField(String fieldName, String value)
			throws Exception;

	public List<UserBean> getUsersByPage(long start, long limit)
			throws Exception;

	public void followUser(String srcUserId, String tarUserId) throws Exception;

	public void unfollowUser(String srcUserId, String tarUserId)
			throws Exception;

	public void watchUser(String srcUserId, String tarUserId) throws Exception;

	public void unwatchUser(String srcUserId, String tarUserId)
			throws Exception;
	
	public void watchScene(String userId, String sceneId) throws Exception;
	
	public void unwatchScene(String userId, String sceneId) throws Exception;
}
