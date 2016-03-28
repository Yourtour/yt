package com.yt.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import com.yt.PropertiesReader;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.UserBeanRepository;
import com.yt.business.repository.neo4j.UserTuple;
import com.yt.business.service.IUserService;
import com.yt.core.common.AppException;
import com.yt.core.common.StaticErrorEnum;
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

	@Autowired
	protected Neo4jTemplate template;

	@Override
	public UserProfileBean getUserProfileInfo(Long userId,
			boolean loadRelationship) throws Exception {
		UserProfileBean user = profileCrudOperate.get(userId, loadRelationship);
		if (user == null) {
			LOG.warn(String
					.format("No UserProfileBean found for ID=%d", userId));
		}

		return user;
	}

	@Override
	public UserProfileBean getUserProfileInfo(Long userId) throws Exception {
		return getUserProfileInfo(userId, true);
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
		if (!rPassword.equals(MessageDigestUtils.digest(
				propertiesReader.getProperty("digest.algorithm", "SHA-1"),
				password.trim()))) {
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
		// TODO 登出系统
	}

	@Cacheable("default")
	@Override
	public String getAuthcode(String mobileNO) throws Exception {
		Random random = new Random(new Date().getTime());
		int len = 6;
		StringBuffer sb = new StringBuffer();
		// 生成6位随机数
		for (int i = 0; i < len; i++) {
			int value = random.nextInt(10);
			sb.append(value);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Generate a authenticate code: '%s'.",
					sb.toString()));
		}
		// TODO 发送短信动态验证码
		return sb.toString();
	}

	@CacheEvict(value = "default", key = "#userName")
	@Override
	public UserProfileBean regist(String userName, String password)
			throws Exception {
		UserAccountBean account = accountCrudOperate.get("userName", userName);
		if (account != null) {
			throw new AppException(StaticErrorEnum.USER_MOBILE_EXIST);
		}

		// 先创建UserProfile
		UserProfileBean profile = new UserProfileBean();
		super.updateBaseInfo(profile, -1l);
		profile.setMobileNo(userName);
		profileCrudOperate.save(profile);

		account = new UserAccountBean();
		super.updateBaseInfo(account, profile.getId());
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
		super.updateBaseInfo(profile, profile.getId());
		this.profileCrudOperate.save(profile, false);

		return (UserProfileBean) profileCrudOperate.get(profile.getId(), false);
	}

	@Override
	public void collectSomething(long userId, String type, long id)
			throws Exception {
		Node srcNode = template.getNode(userId);
		Node tarNode = template.getNode(id);
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("type", type);
		template.createRelationshipBetween(srcNode, tarNode,
				Constants.RELATION_TYPE_COLLECTED, properties);
	}

	@Override
	public void uncollectSomething(long userId, String type, long id)
			throws Exception {
		Node srcNode = template.getNode(userId);
		Node tarNode = template.getNode(id);
		template.deleteRelationshipBetween(srcNode, tarNode,
				Constants.RELATION_TYPE_COLLECTED);
	}

	@Override
	public void watchSomeone(long userId, long id) throws Exception {
		Node srcNode = template.getNode(userId);
		Node tarNode = template.getNode(id);
		template.createRelationshipBetween(srcNode, tarNode,
				Constants.RELATION_TYPE_WATCH, null);
	}

	@Override
	public void unwatchSomeone(long userId, long id) throws Exception {
		Node srcNode = template.getNode(userId);
		Node tarNode = template.getNode(id);
		template.deleteRelationshipBetween(srcNode, tarNode,
				Constants.RELATION_TYPE_WATCH);
	}
}
