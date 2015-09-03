/** 
 * @(#)TestUserBeanHbase.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.business.hbase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.bean.UserBean;
import com.yt.business.common.Constants.GenderType;
import com.yt.business.common.Constants.Role;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.DdlGeneralOperate;
import com.yt.dal.hbase.HbaseManager;
import com.yt.dal.hbase.ICrudOperate;
import com.yt.dal.hbase.IDdlOperate;

public class TestUserBeanHbase {

	private ApplicationContext context;
	private HbaseManager manager;
	private ICrudOperate crudOperate;
	private IDdlOperate ddlOperate;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "hbaseApplicationContext.xml" });
		manager = context.getBean(HbaseManager.class);
		crudOperate = context.getBean(ICrudOperate.class);
		ddlOperate = context.getBean(DdlGeneralOperate.class);
	}

	@After
	public void tearDown() throws Exception {
		crudOperate = null;
		manager.getAdmin().close();
		manager.getConnection().close();
		manager = null;
		context = null;
	}

	@Test
	public void testContext() {
		assertNotNull(context);
		assertNotNull(manager);
		assertNotNull(crudOperate);
	}

	@Test
	public void testCrudUserBean() {
		try {
			if (ddlOperate.tableExist(UserBean.class)) {
				ddlOperate.dropTable(UserBean.class);
			}
			ddlOperate.createTable(UserBean.class);
			assertEquals(crudOperate.get(UserBean.class).size(), 0);

			UserBean bean = new UserBean();
			bean.setBirthday(System.currentTimeMillis());
			bean.setCharacter("character");
			bean.setConstellation("constellation");
			bean.setCreatedTime(System.currentTimeMillis());
			bean.setCreatedUserId("created user");
			bean.setEmail("email");
			bean.setImageUrl("image url");
			bean.setMobileNo("modile no");
			bean.setNativePlace("nateive place");
			bean.setNickName("nick name");
			bean.setPwd("password");
			bean.setRole(Role.EXPERT);
			bean.setRealName("real name");
			bean.setResidence("residence");
			bean.setRowKey("row key 001");
			bean.setGender(GenderType.FEMALE);
			bean.setStatus(Status.ACTIVED);
			bean.setUpdatedTime(System.currentTimeMillis());
			bean.setUpdatedUserId("update user");
			bean.setUserName("user name");
			crudOperate.save(bean);
			assertEquals(crudOperate.get(UserBean.class).size(), 1);

			UserBean bean1 = (UserBean) crudOperate.get(UserBean.class,
					bean.getRowKey());
			assertNotNull(bean1);
			assertEquals(bean1.getBirthday(), bean.getBirthday());
			assertEquals(bean1.getCharacter(), bean.getCharacter());
			assertEquals(bean1.getConstellation(), bean.getConstellation());
			assertEquals(bean1.getCreatedTime(), bean.getCreatedTime());
			assertEquals(bean1.getCreatedUserId(), bean.getCreatedUserId());
			assertEquals(bean1.getEmail(), bean.getEmail());
			assertEquals(bean1.getImageUrl(), bean.getImageUrl());
			assertEquals(bean1.getMobileNo(), bean.getMobileNo());
			assertEquals(bean1.getNativePlace(), bean.getNativePlace());
			assertEquals(bean1.getNickName(), bean.getNickName());
			assertEquals(bean1.getPwd(), bean.getPwd());
			assertEquals(bean1.getRole(), bean.getRole());
			assertEquals(bean1.getRealName(), bean.getRealName());
			assertEquals(bean1.getResidence(), bean.getResidence());
			assertEquals(bean1.getRowKey(), bean.getRowKey());
			assertEquals(bean1.getGender(), bean.getGender());
			assertEquals(bean1.getStatus(), bean.getStatus());
			assertEquals(bean1.getUpdatedTime(), bean.getUpdatedTime());
			assertEquals(bean1.getUpdatedUserId(), bean.getUpdatedUserId());
			assertEquals(bean1.getUserName(), bean.getUserName());

			bean1.setCharacter("new character");
			bean1.setUpdatedTime(System.currentTimeMillis() + 1000);
			bean1.setUserName("new user name");
			crudOperate.save(bean1);
			assertEquals(crudOperate.get(UserBean.class).size(), 1);
			UserBean bean11 = (UserBean) crudOperate.get(UserBean.class,
					bean1.getRowKey());
			assertNotNull(bean11);
			assertEquals(bean1.getCharacter(), bean11.getCharacter());
			assertEquals(bean1.getUpdatedTime(), bean11.getUpdatedTime());
			assertEquals(bean1.getUserName(), bean11.getUserName());
			assertFalse(bean.getCharacter().equals(bean11.getCharacter()));
			assertFalse(bean.getUpdatedTime() == bean11.getUpdatedTime());
			assertFalse(bean.getUserName().equals(bean11.getUserName()));
			assertEquals(bean.getBirthday(), bean11.getBirthday());
			assertEquals(bean.getRealName(), bean11.getRealName());

			bean.setRowKey("row key 002");
			crudOperate.save(bean);
			assertEquals(crudOperate.get(UserBean.class).size(), 2);
			UserBean bean12 = (UserBean) crudOperate.get(UserBean.class,
					"row key 002");
			assertNotNull(bean12);
			assertEquals(bean.getBirthday(), bean12.getBirthday());
			assertEquals(bean.getRealName(), bean12.getRealName());

			crudOperate.deleteRow(bean);
			assertEquals(crudOperate.get(UserBean.class).size(), 1);
			UserBean bean2 = (UserBean) crudOperate.get(UserBean.class,
					bean.getRowKey());
			assertNull(bean2);

			if (ddlOperate.tableExist(UserBean.class)) {
				ddlOperate.dropTable(UserBean.class);
			}
			assertFalse(ddlOperate.tableExist(UserBean.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
