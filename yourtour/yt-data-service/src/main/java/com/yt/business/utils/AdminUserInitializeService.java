package com.yt.business.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.UserBean;
import com.yt.core.utils.MessageDigestUtils;

public class AdminUserInitializeService implements InitializingBean {
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory
			.getLog(AdminUserInitializeService.class);

	@Autowired
	@Qualifier("crudAllInOneOperate")
	private CrudAllInOneOperate crudOperate;

	// 密码进行签名的算法
	private String algorithm = "SHA-1";

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
		this.setAlgorithm(algorithm);
	}

	/**
	 * 设置密码进行签名计算的算法，默认采用SHA-1。
	 * 
	 * @param algorithm
	 *            签名算法名称
	 */
	public void setAlgorithm(String algorithm) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("The algorithm is changed: '%s' -> '%s'.",
					this.algorithm, algorithm));
		}
		this.algorithm = algorithm;
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
		String target = MessageDigestUtils.digest(algorithm, origin);
		return base64.equals(target);
	}

	@Transactional
	private void initializeAdminEmployee() throws Exception {
		// 检测默认的admin账户是否存在
		UserBean admin = (UserBean) crudOperate.get(UserBean.class, "code",
				"admin");
		if (admin != null) {
			// admin账户已经存在，返回
			return;
		}
		// admin账户不存在，需要初始化
		if (LOG.isWarnEnabled()) {
			LOG.warn("The admin user is not exist, will initialize it.");
		}
		admin = new UserBean();
		admin.setCode("admin");
		admin.setName("管理员");
		// 将明码的密码更换为摘要加密的密码
		admin.setPwd(MessageDigestUtils.digest(this.algorithm, "admin"));
		crudOperate.save(admin, "admin");
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Initialize admin employee successfully, default code = %s, password = %s.",
							admin.getCode(), admin.getPwd()));
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
