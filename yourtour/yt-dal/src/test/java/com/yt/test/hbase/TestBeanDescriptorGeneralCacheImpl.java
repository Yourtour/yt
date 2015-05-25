package com.yt.test.hbase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.BeanDescriptor.Family;
import com.yt.dal.hbase.cache.BeanDescriptor.Qualifier;
import com.yt.dal.hbase.cache.IBeanDescriptorCache;
import com.yt.test.hbase.bean.BaseServiceInfo;
import com.yt.test.hbase.bean.NoNamespaceBean;
import com.yt.test.hbase.bean.TestBean;

public class TestBeanDescriptorGeneralCacheImpl {
	private ApplicationContext context;
	private IBeanDescriptorCache cache;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "hbaseApplicationContext.xml" });
		cache = context.getBean(IBeanDescriptorCache.class);
	}

	@Test
	public void testStandardBean() {
		assertNotNull(cache);
		BeanDescriptor bd = cache.get(TestBean.class);
		assertNotNull(bd);
		assertTrue(bd.getNamespace().equals("default"));
		assertTrue(bd.getTableName().equals("T_TestBean"));
		assertTrue(bd.getFamilies().size() == 1);
		assertTrue(bd.getFamilies().containsKey("if"));
		assertFalse(bd.getFamilies().containsKey("e"));
		BeanDescriptor.Family family = bd.getFamily("if");
		assertNotNull(family);
		assertTrue(family.getName().equals("if"));
		assertTrue(family.getQualifiers().size() == 3);
		assertTrue("tn".equals(family.getQualifier("tableName").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("tableName").getVersion()));
		assertTrue("f".equals(family.getQualifier("family").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("family").getVersion()));
		assertTrue("q".equals(family.getQualifier("qualifier").getQualifier()));
		assertTrue("1.1".equals(family.getQualifier("qualifier").getVersion()));
		assertTrue("1.1".equals(bd.getVersion()));
		assertFalse("1.0".equals(bd.getVersion()));
	}
	
	@Test
	public void testClone() {
		assertNotNull(cache);
		BeanDescriptor bd1 = cache.get(TestBean.class);
		BeanDescriptor bd2 = cache.get(TestBean.class);
		assertNotNull(bd1);
		assertNotNull(bd2);
		assertNotSame(bd1, bd2);
		assertSame(bd1.getTableName(), bd1.getTableName());
		assertSame(bd1.getFamily("if"), bd1.getFamily("if"));
		Family family1 = bd1.getFamily("if");
		Family family2 = bd2.getFamily("if");
		assertNotNull(family1);
		assertNotNull(family2);
		assertNotSame(family1, family2);
		assertSame(family1.getName(), family1.getName());
		assertSame(family1.getByteFamily(), family1.getByteFamily());
		Qualifier qualifier1 = family1.getQualifier("tableName");
		Qualifier qualifier2 = family2.getQualifier("tableName");
		assertNotNull(qualifier1);
		assertNotNull(qualifier2);
		assertNotSame(qualifier1, qualifier2);
	}

	@Test
	public void testNonStandardBean() {
		assertNotNull(cache);
		BeanDescriptor bd = cache.get(BaseServiceInfo.class);
		assertNotNull(bd);
		assertTrue(bd.getNamespace().equals("service"));
		assertTrue(bd.getTableName().equals("T_BASESERVICEINFO"));
		assertTrue(bd.getFamilies().size() == 1);
		assertTrue(bd.getFamilies().containsKey("d"));
		assertFalse(bd.getFamilies().containsKey("e"));
		Family family = bd.getFamily("d");
		assertNotNull(family);
		assertTrue(family.getQualifiers().size() == 10);
		assertTrue("code".equals(family.getQualifier("code").getQualifier()));
		assertTrue("code".equals(family.getQualifier("code").getName()));
		assertTrue("1.0".equals(family.getQualifier("code").getVersion()));
		assertTrue("name".equals(family.getQualifier("name").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("name").getVersion()));
		assertTrue("memo".equals(family.getQualifier("memo").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("memo").getVersion()));
		assertTrue("mode".equals(family.getQualifier("mode").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("mode").getVersion()));
		assertTrue("type".equals(family.getQualifier("type").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("type").getVersion()));
		assertTrue("prep".equals(family.getQualifier("prepayment").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("prepayment").getVersion()));
		assertTrue("cuid".equals(family.getQualifier("createdUserId").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("createdUserId").getVersion()));
		assertTrue("uuid".equals(family.getQualifier("updatedUserId").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("updatedUserId").getVersion()));
		assertTrue("ut".equals(family.getQualifier("updatedTime").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("updatedTime").getVersion()));
		assertTrue("stat".equals(family.getQualifier("status").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("status").getVersion()));
		assertFalse("1.1".equals(bd.getVersion()));
	}
	
	@Test
	public void testNoNamespace() {
		assertNotNull(cache);
		BeanDescriptor bd = cache.get(NoNamespaceBean.class);
		assertNotNull(bd);
		assertTrue(bd.getNamespace().equals(""));
		assertTrue(bd.getTableName().equals("T_NO"));
		assertTrue(bd.getFamilies().size() == 1);
		assertTrue(bd.getFamilies().containsKey("d"));
		assertFalse(bd.getFamilies().containsKey("e"));
		Family family = bd.getFamily("d");
		assertNotNull(family);
		assertTrue(family.getQualifiers().size() == 2);
		assertTrue("code".equals(family.getQualifier("code").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("code").getVersion()));
		assertTrue("name".equals(family.getQualifier("name").getQualifier()));
		assertTrue("1.0".equals(family.getQualifier("name").getVersion()));
	}

}
