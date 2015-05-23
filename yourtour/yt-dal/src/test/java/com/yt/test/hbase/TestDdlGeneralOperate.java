package com.yt.test.hbase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.dal.hbase.HbaseManager;
import com.yt.dal.hbase.IDdlOperate;
import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.BeanDescriptorGeneralCacheImpl;
import com.yt.dal.hbase.cache.IBeanDescriptorCache;
import com.yt.test.hbase.bean.TestBean;

public class TestDdlGeneralOperate {
	private ApplicationContext context;
	private HbaseManager manager;
	private IDdlOperate ddlOperate;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "hbaseApplicationContext.xml" });
		manager = context.getBean(HbaseManager.class);
		ddlOperate = context.getBean(IDdlOperate.class);
	}

	@After
	public void tearDown() throws Exception {
		ddlOperate = null;
		manager.getAdmin().close();
		manager.getConnection().close();
		manager = null;
		context = null;
	}

	@Test
	public void testContext() {
		assertNotNull(ddlOperate);
	}

	@Test
	public void testCreateAndDropTable() {
		try {
			ddlOperate.dropTable(TestBean.class);
			assertFalse(ddlOperate.tableExist(TestBean.class));
			ddlOperate.createTable(TestBean.class);
			assertTrue(ddlOperate.tableExist(TestBean.class));
			ddlOperate.dropTable(TestBean.class);
			assertFalse(ddlOperate.tableExist(TestBean.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testMetadata() {
		HbaseManager manager = context.getBean(HbaseManager.class);
		assertNotNull(manager);
		IBeanDescriptorCache cache = context
				.getBean(BeanDescriptorGeneralCacheImpl.class);
		assertNotNull(cache);
		try {
			ddlOperate.dropTable(TestBean.class);
			assertFalse(ddlOperate.tableExist(TestBean.class));
			ddlOperate.createTable(TestBean.class);
			assertTrue(ddlOperate.tableExist(TestBean.class));

			BeanDescriptor bd = cache.get(TestBean.class);
			assertNotNull(bd);
			NamespaceDescriptor nd = manager.getAdmin().getNamespaceDescriptor(bd.getNamespace());
			assertNotNull(nd);
			assertTrue("default".equals(nd.getName()));
			HTableDescriptor htd = manager.getAdmin().getTableDescriptor(
					TableName.valueOf(bd.getTableName()));
			assertNotNull(htd);
			assertTrue(bd.getTableName().equals(
					htd.getTableName().getNameAsString()));
			HColumnDescriptor hcd = htd.getFamily(Bytes.toBytes("if"));
			assertNotNull(hcd);
			assertNull(htd.getFamily(Bytes.toBytes("d")));
			assertTrue("".equals(htd.getValue("startKey")));
			assertTrue("".equals(htd.getValue("endKey")));
			assertTrue("1.1".equals(htd.getValue("version")));
			assertTrue(1 == Bytes
					.toInt(htd.getValue(Bytes.toBytes("regionNum"))));

			ddlOperate.dropTable(TestBean.class);
			assertFalse(ddlOperate.tableExist(TestBean.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void dropDefaultNamespace() {
		try {
			ddlOperate.dropNamespace("default");
			ddlOperate.dropNamespace("hbase");
			fail("Drop default namespace success, it's fail.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
