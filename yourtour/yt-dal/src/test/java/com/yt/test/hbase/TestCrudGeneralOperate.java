/* 
 * @(#)TestCrudGeneralOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.
 */
package com.yt.test.hbase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.dal.hbase.HbaseManager;
import com.yt.dal.hbase.ICrudOperate;
import com.yt.test.hbase.bean.BaseServiceInfo;
import com.yt.test.hbase.bean.TestBean;

public class TestCrudGeneralOperate {
	private ApplicationContext context;
	private HbaseManager manager;
	private ICrudOperate crudOperate;
	
	public static void main(String[] args) throws Exception {
		TestCrudGeneralOperate test = new TestCrudGeneralOperate();
		test.setUp();
		
		test.testCrudTestBean();
		
		/*
		BaseServiceInfo info = new BaseServiceInfo();
		info.setRowKey("BSI-01-02-003-02");
		info.setCode(info.getRowKey());
		info.setName("餐饮服务");
		info.setCreatedUserId("admin");
		info.setMemo("餐饮服务");
		info.setMode("01");
		info.setPrepayment(true);
		info.setStatus("有效");
		info.setType("02");
		test.crudOperate.save(info);
		*/
		//info.setMemo(info.getMemo() + ", modify once.");
		//test.crudOperate.save(info);
		
		test.tearDown();
	}

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
		bean.setTimestamp(System.currentTimeMillis());
		bean.setValue("This is a test message, asldjlaskjd09823098.");
		try {
		crudOperate.save(bean);
		Thread.sleep(1000);
		crudOperate.deleteRow(bean);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
