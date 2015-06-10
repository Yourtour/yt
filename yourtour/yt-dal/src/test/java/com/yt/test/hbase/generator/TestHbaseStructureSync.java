package com.yt.test.hbase.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.dal.hbase.DdlGeneralOperate;
import com.yt.dal.hbase.HbaseManager;
import com.yt.dal.hbase.IDdlOperate;
import com.yt.dal.hbase.generator.HbaseStructureSync;
import com.yt.test.hbase.bean.BaseServiceInfo;
import com.yt.test.hbase.bean.NoNamespaceBean;
import com.yt.test.hbase.bean.TestBean;

public class TestHbaseStructureSync {
	private ApplicationContext context;
	private HbaseManager manager;
	private IDdlOperate ddlOperate;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "hbaseApplicationContext.xml" });
		manager = context.getBean(HbaseManager.class);
		ddlOperate = context.getBean(DdlGeneralOperate.class);
	}

	@After
	public void tearDown() throws Exception {
		manager.getAdmin().close();
		manager.getConnection().close();
		manager = null;
		context = null;
	}

	@Test
	public void test() {
		try {
			assertTrue(ddlOperate.tableExist(BaseServiceInfo.class));
			assertTrue(ddlOperate.tableExist(NoNamespaceBean.class));
			assertTrue(ddlOperate.tableExist(TestBean.class));

			HbaseStructureSync sync = context.getBean(HbaseStructureSync.class);
			assertNotNull(sync);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
