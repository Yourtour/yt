package com.yt.business.utils;

import com.yt.business.bean.UserProfileBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yt.PropertiesReader;
import com.yt.business.bean.UserAccountBean;
import com.yt.business.service.IUserService;
import com.yt.core.utils.MessageDigestUtils;

@Service
public class AdminUserInitializeService implements InitializingBean {
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory
			.getLog(AdminUserInitializeService.class);

	@Autowired
	private IUserService service;

	@Autowired
	private PropertiesReader propertiesReader;

	/**
	 * 默认构造方法
	 */
	public AdminUserInitializeService() {
		super();
	}

	/**
	 * 默认的构造函数
	 * 
	 * @param algorithm
	 *            签名算法名称
	 */
	public AdminUserInitializeService(String algorithm) {
		this();
	}

	/**
	 * 判断输入的密码是否和已经签名并BASE64编码的密文一致。
	 * 
	 * @param origin
	 *            输入的原始密码
	 * @param base64
	 *            已经经过签名和BASE64编码的密文
	 * @return 如果一致，则返回true；否则返回false。
	 * @throws Exception
	 *             判断过程中发生的异常
	 */
	public boolean checkPassword(String origin, String base64) throws Exception {
		String target = MessageDigestUtils.digest(
				propertiesReader.getProperty("digest.algorithm", "SHA-1"),
				origin);
		return base64.equals(target);
	}

	@Transactional
	private void initializeAdminEmployee() throws Exception {
		// 检测默认的admin账户是否存在
		UserAccountBean admin = service.getUserAccount("admin");
		if (admin != null) {
			// admin账户已经存在，返回
			return;
		}

		// admin账户不存在，需要初始化
		if (LOG.isWarnEnabled()) {
			LOG.warn("The admin user is not exist, will initialize it.");
		}

		UserAccountBean account = new UserAccountBean();
		account.setPwd("admin");
		account.setUserName("admin");
		account.setType(UserAccountBean.AccountType.ADMINISTRATOR);

		UserProfileBean profile = new UserProfileBean();
		profile.setRealName("admin");

		service.register(account, profile, 0l);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Initialize admin employee successfully, default code = admin, password = admin, pls change the password..");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initializeAdminEmployee();
	}
}
