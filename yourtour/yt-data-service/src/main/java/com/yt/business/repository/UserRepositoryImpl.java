package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.neo4j.repository.UserAccountBeanRepository;
import com.yt.business.neo4j.repository.UserProfileBeanRepository;

@Component
public class UserRepositoryImpl extends CrudAllInOneOperateImpl implements
		UserRepository {
	private static final Log LOG = LogFactory.getLog(UserRepositoryImpl.class);

	@Autowired
	private UserProfileBeanRepository profileRepository;
	
	@Autowired
	private UserAccountBeanRepository accountRepository;

	public UserRepositoryImpl() {
		super();
	}

	@Override
	public UserAccountBean getUserAccount(String userName) throws Exception {
		return accountRepository.getUserAccountInfo(userName);
	}
}
