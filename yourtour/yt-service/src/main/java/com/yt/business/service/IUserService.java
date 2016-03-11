package com.yt.business.service;

import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;

/**
 * 和用户相关的服务接口 Created by 林平 on 2016/3/7.
 */
public interface IUserService {
	/**
	 * 根据用户ID获取个人信息
	 * 
	 * @param useId
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean getUserProfileInfo(Long useId) throws Exception;

	/**
	 * 根据账号获取用户信息
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean getUserProfileInfo(String userName) throws Exception;

	/**
	 * 根据用户名获取用户账户对象
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserAccountBean getUserAccount(String userName) throws Exception;

	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean login(String userName, String password)
			throws Exception;

	/**
	 * 用户注销
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public void logout(Long userId) throws Exception;

	/**
	 * 用户账号注册
	 * 
	 * @param userName
	 *            用户名，一般使用手机号
	 * @param password
	 *            用户口令，将使用配置的加密算法进行存储加密。
	 * @return 用户Profile对象
	 * @throws Exception
	 */
	public UserProfileBean regist(String userName, String password)
			throws Exception;

	/**
	 * 用户信息注册
	 * 
	 * @param profile
	 * @return
	 * @throws Exception
	 */
	public UserProfileBean saveUseProfile(UserProfileBean profile)
			throws Exception;

}
