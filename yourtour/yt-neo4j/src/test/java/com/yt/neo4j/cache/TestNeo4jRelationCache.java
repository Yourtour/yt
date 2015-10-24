package com.yt.neo4j.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.bean.Account;
import com.yt.business.bean.Organization;
import com.yt.business.bean.Person;
import com.yt.business.bean.Privilege;
import com.yt.business.bean.Role;
import com.yt.neo4j.cache.Neo4jBeanDescriptor;
import com.yt.neo4j.cache.Neo4jBeanDescriptor.RelationDescriptor;
import com.yt.neo4j.cache.Neo4jBeanDescriptorCache;

public class TestNeo4jRelationCache {
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "application-neo4j-context.xml" });
		assertNotNull(context);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
		context = null;
	}

	@Test
	public void testBeanDefine() {
		Neo4jBeanDescriptorCache cache = context
				.getBean(Neo4jBeanDescriptorCache.class);
		assertNotNull(cache);

		List<Neo4jBeanDescriptor> beans = cache.get();
		assertNotNull(beans);
		assertEquals(beans.size(), 5);

		String[] keys = { "com.yt.business.bean.Account",
				"com.yt.business.bean.Organization",
				"com.yt.business.bean.Person",
				"com.yt.business.bean.Privilege", "com.yt.business.bean.Role" };
		for (String key : keys) {
			Neo4jBeanDescriptor nbd = cache.get(key);
			assertNotNull(nbd);
			assertEquals(nbd.getName(), key);
			assertNotNull(nbd.getRelations());
			assertTrue(nbd.getRelations().size() > 0);
		}
	}

	@Test
	public void testPersonBean() {
		Neo4jBeanDescriptorCache cache = context
				.getBean(Neo4jBeanDescriptorCache.class);
		assertNotNull(cache);

		Neo4jBeanDescriptor nbd = cache.get(Person.class);
		assertNotNull(nbd);
		assertEquals(nbd.getName(), "com.yt.business.bean.Person");
		assertEquals(nbd.getRelations().size(), 1);

		RelationDescriptor rd = nbd.getRelation("accounts");
		assertEquals(rd.getClazz(), Account.class);
		assertEquals(rd.getFieldName(), "accounts");
		assertEquals(rd.getRelationship(), "ASSIGNED");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.OUTGOING);
	}

	@Test
	public void testAccountBean() {
		Neo4jBeanDescriptorCache cache = context
				.getBean(Neo4jBeanDescriptorCache.class);
		assertNotNull(cache);

		Neo4jBeanDescriptor nbd = cache.get(Account.class);
		assertNotNull(nbd);
		assertEquals(nbd.getName(), "com.yt.business.bean.Account");
		assertEquals(nbd.getRelations().size(), 2);

		RelationDescriptor rd = nbd.getRelation("person");
		assertEquals(rd.getClazz(), Person.class);
		assertEquals(rd.getFieldName(), "person");
		assertEquals(rd.getRelationship(), "ASSOCIATED");
		assertFalse(rd.isSet());
		assertEquals(rd.getDirection(), Direction.INCOMING);

		rd = nbd.getRelation("roles");
		assertEquals(rd.getClazz(), Role.class);
		assertEquals(rd.getFieldName(), "roles");
		assertEquals(rd.getRelationship(), "HAS");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.BOTH);
	}

	@Test
	public void testRoleBean() {
		Neo4jBeanDescriptorCache cache = context
				.getBean(Neo4jBeanDescriptorCache.class);
		assertNotNull(cache);

		Neo4jBeanDescriptor nbd = cache.get(Role.class);
		assertNotNull(nbd);
		assertEquals(nbd.getName(), "com.yt.business.bean.Role");
		assertEquals(nbd.getRelations().size(), 2);

		RelationDescriptor rd = nbd.getRelation("accounts");
		assertEquals(rd.getClazz(), Account.class);
		assertEquals(rd.getFieldName(), "accounts");
		assertEquals(rd.getRelationship(), "ASSOCIATED");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.BOTH);

		rd = nbd.getRelation("privileges");
		assertEquals(rd.getClazz(), Privilege.class);
		assertEquals(rd.getFieldName(), "privileges");
		assertEquals(rd.getRelationship(), "ASSOCIATED");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.BOTH);
	}

	@Test
	public void testOrganizationBean() {
		Neo4jBeanDescriptorCache cache = context
				.getBean(Neo4jBeanDescriptorCache.class);
		assertNotNull(cache);

		Neo4jBeanDescriptor nbd = cache.get(Organization.class);
		assertNotNull(nbd);
		assertEquals(nbd.getName(), "com.yt.business.bean.Organization");
		assertEquals(nbd.getRelations().size(), 3);

		RelationDescriptor rd = nbd.getRelation("manager");
		assertEquals(rd.getClazz(), Account.class);
		assertEquals(rd.getFieldName(), "manager");
		assertEquals(rd.getRelationship(), "MANAGED");
		assertFalse(rd.isSet());
		assertEquals(rd.getDirection(), Direction.OUTGOING);

		rd = nbd.getRelation("roles");
		assertEquals(rd.getClazz(), Role.class);
		assertEquals(rd.getFieldName(), "roles");
		assertEquals(rd.getRelationship(), "HAS");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.OUTGOING);

		rd = nbd.getRelation("accounts");
		assertEquals(rd.getClazz(), Account.class);
		assertEquals(rd.getFieldName(), "accounts");
		assertEquals(rd.getRelationship(), "HAS");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.INCOMING);
	}

	@Test
	public void testPrivilegeBean() {
		Neo4jBeanDescriptorCache cache = context
				.getBean(Neo4jBeanDescriptorCache.class);
		assertNotNull(cache);

		Neo4jBeanDescriptor nbd = cache.get(Privilege.class);
		assertNotNull(nbd);
		assertEquals(nbd.getName(), "com.yt.business.bean.Privilege");
		assertEquals(nbd.getRelations().size(), 1);

		RelationDescriptor rd = nbd.getRelation("roles");
		assertEquals(rd.getClazz(), Role.class);
		assertEquals(rd.getFieldName(), "roles");
		assertEquals(rd.getRelationship(), "ASSOCIATED");
		assertTrue(rd.isSet());
		assertEquals(rd.getDirection(), Direction.BOTH);
	}

}
