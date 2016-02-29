/** 
 * @(#)UserBeanRepository.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.business.neo4j.repository;

import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.UserAccountBean;

import java.util.List;

/**
 * 
 * @author Tony.Zhang
 *
 */
public interface UserBeanRepository extends GraphRepository<UserAccountBean> {
	/**
	 * 获取关注了相同行程的用户列表
	 *
	 * @param user
	 *            　指定的用户
	 * @return　用户列表
	 */
	@Query("START u=node({0}) MATCH u-[:watchRoute]->(route)<-[:watchRoute]-(users) RETURN users")
	public List<UserProfileBean> getUsersWatchSameRoutes(UserProfileBean user);

	/**
	 * 根据昵称获取用户信息
	 * @param nickName
	 * @return
	 */
	@Query("MATCH (profile:UserProfileBean)<-[:BELONG]-(account:UserAccount) WHERE profile.nickName={0} RETURN profile, account")
	public UserTuple getUserByNickName(String nickName);

	/**
	 * 根据账号检索账号注册信息
	 * @param userName
	 * @return
	 */
	@Query("MATCH (profile:UserProfileBean)<-[:BELONG]-(account:UserAccountBean) WHERE account.userName={0} RETURN profile, account")
	public UserTuple getUserByUserName(String userName);

	@Query("match (account:UserAccountBean)-[:BELONG]->(profile:UserProfileBean) where account.userName={0} or profile.nickName={0} or profile.mobileNo={0} RETURN profile;")
	public List<UserProfileBean> getUsers(String searchWords);
}
