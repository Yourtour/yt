package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.UserAccountBean;

public interface UserRepository extends CrudAllInOneOperate {
	/**
	 * 根据账号获取账户信息
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserAccountBean getUserAccount(String userName) throws Exception;
}
