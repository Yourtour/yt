/* 
 * @(#)TestCrudGeneralOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.
 */
package com.yt.test.hbase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.dal.hbase.HbaseManager;
import com.yt.dal.hbase.ICrudOperate;
import com.yt.test.hbase.bean.TestBean;

public class TestCrudGeneralOperate {
	private ApplicationContext context;
	private HbaseManager manager;
	private ICrudOperate crudOperate;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "hbaseApplicationContext.xml" });
		manager = context.getBean(HbaseManager.class);
		crudOperate = context.getBean(ICrudOperate.class);
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
	public void testCrudTestBean() {
		TestBean bean = new TestBean();
		bean.setRowKey("row-key-001");
		bean.setTableName("table name 03");
		bean.setFamily("family 03");
		bean.setQualifier("qualifier 03");
		try {
			crudOperate.save(bean);
			TestBean bean1 = (TestBean) crudOperate.getNewest(TestBean.class,
					bean.getRowKey());
			assertNotNull(bean1);
			assertTrue(bean.getRowKey().equals(bean1.getRowKey()));
			assertTrue(bean.getFamily().equals(bean1.getFamily()));
			assertTrue(bean.getQualifier().equals(bean1.getQualifier()));
			assertTrue(bean.getTableName().equals(bean1.getTableName()));
			crudOperate.deleteRow(bean);
			TestBean bean2 = (TestBean) crudOperate.getNewest(TestBean.class,
					bean.getRowKey());
			assertNull(bean2);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
	
}
