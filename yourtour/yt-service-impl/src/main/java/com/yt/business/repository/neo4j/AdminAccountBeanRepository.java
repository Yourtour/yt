/** 
 * @(#)UserBeanRepository.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.business.repository.neo4j;

import com.yt.business.bean.AdminAccountBean;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * 
 * @author Tony.Zhang
 *
 */
public interface AdminAccountBeanRepository extends GraphRepository<AdminAccountBean> {
	/**
	 * 根据账号检索账号注册信息
	 * @param userName
	 * @return
	 */
	@Query("MATCH (account:AdminAccountBean) WHERE account.userName={0} RETURN account")
	public AdminAccountBean getAccountByUserName(String userName);

}
