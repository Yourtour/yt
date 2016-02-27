package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.AppException;
import com.yt.business.neo4j.repository.UserTuple;

public interface UserRepository extends CrudAllInOneOperate {
	/**
	 * 用户登录验证
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean getUser(String userName, String password) throws AppException, Exception;

	/**
	 * 根据注册手机号查询用户
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean getUserByUserName(String userName) throws Exception;

	/**
	 * 根据昵称处查询用户
	 * @param nickName
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean getUserByNickName(String nickName) throws Exception;
	
	/**
	 * 
	 * @param searchWords
	 * @return
	 */
	public List<UserProfileBean> getUsers(String searchWords);
}
