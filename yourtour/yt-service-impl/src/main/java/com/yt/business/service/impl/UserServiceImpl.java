package com.yt.business.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.PropertiesReader;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.neo4j.UserBeanRepository;
import com.yt.business.repository.neo4j.UserTuple;
import com.yt.business.service.IUserService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.Base64Utils;
import com.yt.core.utils.MessageDigestUtils;
import com.yt.neo4j.repository.CrudOperate;

/**
 * 和用户相关的服务接口 Created by 林平 on 2016/3/7.
 */
@Service
public class UserServiceImpl extends ServiceBase implements IUserService {
	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserBeanRepository repository;

	@Autowired
	private CrudOperate<UserProfileBean> profileCrudOperate;

	@Autowired
	private CrudOperate<UserAccountBean> accountCrudOperate;

	@Autowired
	private PropertiesReader propertiesReader;

	@Override
	public UserProfileBean getUserProfileInfo(Long userId) throws Exception {
		UserProfileBean user = profileCrudOperate.get(userId);
		if (user == null) {
			LOG.warn(String
					.format("No UserProfileBean found for ID=%d", userId));
		}

		return user;
	}

	@Override
	public UserProfileBean getUserProfileInfo(String userName) throws Exception {
		return profileCrudOperate.get("mobileNo", userName);
	}

	@Override
	public UserProfileBean login(String userName, String password)
			throws Exception {
		UserTuple tuple = repository.getUserByUserName(userName);
		if (tuple == null) {
			throw new AppException(StaticErrorEnum.USER_NOT_EXIST);
		}

		String rPassword = tuple.getAccount().getPwd();
		if (!rPassword.equals(Base64Utils.MD5(password.trim()))) {
			throw new AppException(StaticErrorEnum.AUTHENTICATE_FAIL);
		}

		return tuple.getProfile();
	}

	@Override
	public UserAccountBean getUserAccount(String userName) throws Exception {
		return accountCrudOperate.get("userName", userName);
	}

	@Override
	public void logout(Long userId) throws Exception {

	}

	@Override
	public UserProfileBean regist(String userName, String password)
			throws Exception {
		UserAccountBean account = accountCrudOperate.get("userName", userName);
		if (account != null) {
			throw new AppException(StaticErrorEnum.USER_MOBILE_EXIST);
		}

		// 先创建UserProfile
		UserProfileBean profile = new UserProfileBean();
		profile.setMobileNo(userName);
		profileCrudOperate.save(profile);

		account = new UserAccountBean();
		account.setUserName(userName);
		account.setPwd(MessageDigestUtils.digest(
				propertiesReader.getProperty("digest.algorithm", "SHA-1"),
				password));
		account.setProfile(profile);

		this.accountCrudOperate.save(account);

		return account.getProfile();
	}

	@Override
	public UserProfileBean saveUseProfile(UserProfileBean profile)
			throws Exception {
		UserTuple existed = repository.getUserByNickName(profile.getNickName());
		if (existed != null) {
			throw new AppException(StaticErrorEnum.NICKNAME_EXIST);
		}

		this.profileCrudOperate.save(profile, false);

		return (UserProfileBean) profileCrudOperate.get(profile.getId(), false);
	}
}
