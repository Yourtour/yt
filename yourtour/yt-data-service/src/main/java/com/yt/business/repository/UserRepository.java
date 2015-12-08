package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.neo4j.repository.UserTuple;

public interface UserRepository extends CrudAllInOneOperate {
	/**
	 * 根据手机号账户信息
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserTuple getUserAccount(String userName) throws Exception;
	
	/**
	 * 
	 * @param searchWords
	 * @return
	 */
	public List<UserProfileBean> getUsers(String searchWords);
}
