package com.yt.business.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.yt.business.bean.UserBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public class AdminUserInitializeService implements InitializingBean {
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory
			.getLog(AdminUserInitializeService.class);

	@Autowired
	@Qualifier("crudGeneralOperate")
	private ICrudOperate crudOperate;

	/**
	 * 默认构造方法
	 */
	public AdminUserInitializeService() {
		super();
	}

	@Transactional
	private void initializeAdminEmployee() throws Exception {
		// 检测默认的admin账户是否存在
		UserBean admin = (UserBean) crudOperate.get(UserBean.class, "userName",
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
		admin.setUserName("admin");
		admin.setRowKey("admin");
		admin.setRealName("管理员");
		// TODO 后续将明码的密码更换为摘要加密的密码
		admin.setPwd("admin");
		crudOperate.save(admin, "admin");
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Initialize admin employee successfully, default code = %s, password = %s.",
							admin.getUserName(), admin.getPwd()));
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
