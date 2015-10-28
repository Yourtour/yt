package com.yt.business.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.CrudAllInOneOperate;
import com.yt.neo4j.repository.CrudOperate;

public class TestRouteProvisionBean {
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {
				"application-hbase-context.xml",
				"application-neo4j-context.xml" });
		assertNotNull(context);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
		context = null;
	}

	@Test
	public void testBeanDefined() {
		CrudAllInOneOperate crud = context.getBean("crudAllInOneOperate",
				CrudAllInOneOperate.class);
		assertNotNull(crud);
	}

	@Test
	public void testBaseCrud() {
		CrudOperate crud = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(crud);

		try {
			crud.delete(RouteProvisionBean.class);
			assertEquals(crud.count(RouteProvisionBean.class), 0);

			// TEST INSERT
			RouteProvisionBean rp = new RouteProvisionBean();
			rp.setIndex(5);
			rp.setMemo("memo 1");
			rp.setName("name 1");
			rp.setRowKey("rowkey 1");
			assertNull(rp.getGraphId());
			crud.save(rp, "john");
			assertEquals(crud.count(RouteProvisionBean.class), 1);
			RouteProvisionBean rp1 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, rp.getGraphId());
			assertNotNull(rp1);
			assertNotNull(rp1.getGraphId());
			RouteProvisionBean rp2 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, "rowKey", rp.getRowKey());
			assertNotNull(rp2);
			assertNotNull(rp2.getGraphId());
			assertEquals(rp2.getRowKey(), rp.getRowKey());
			assertEquals(rp2.getCreatedTime(), rp.getCreatedTime());
			assertTrue(rp2.getCreatedTime() > 0);
			assertEquals(rp2.getCreatedUserId(), rp.getCreatedUserId());
			assertEquals(rp2.getCreatedUserId(), "john");
			assertEquals(rp2.getIndex(), rp.getIndex());
			assertEquals(rp2.getMemo(), rp.getMemo());
			assertEquals(rp2.getName(), rp.getName());
			assertEquals(rp2.getUpdatedTime(), rp.getUpdatedTime());
			assertFalse(rp2.getUpdatedTime() > 0);
			assertEquals(rp2.getUpdatedUserId(), rp.getUpdatedUserId());
			assertNull(rp2.getRouteMain());

			// TEST GRAPHID = NULL AND SAME ROWKEY
			rp2.setGraphId(null);
			crud.save(rp2, "john");
			assertEquals(crud.count(RouteProvisionBean.class), 1);
			RouteProvisionBean rp2_1 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, rp2.getGraphId());
			assertNotNull(rp2_1);
			RouteProvisionBean rp2_11 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, "rowKey", rp2.getRowKey());
			assertNotNull(rp2_11);
			assertEquals(rp2_1.getGraphId(), rp2_11.getGraphId());
			assertEquals(rp2_1.getRowKey(), rp2_11.getRowKey());
			assertEquals(rp2_11.getGraphId(), rp.getGraphId());
			assertEquals(rp2_11.getName(), rp.getName());

			// TEST GRAPHID = NULL AND NOT SAME ROWKEY
			rp2_1.setGraphId(null);
			rp2_1.setRowKey(rp2_1.getRowKey() + " modified");
			crud.save(rp2_1, "john");
			assertEquals(crud.count(RouteProvisionBean.class), 2);
			RouteProvisionBean rp2_2 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, rp2_1.getGraphId());
			assertEquals(rp2_2.getGraphId(), rp2_1.getGraphId());
			assertEquals(rp2_2.getRowKey(), rp2_1.getRowKey());
			assertEquals(rp2_2.getName(), rp2_1.getName());
			RouteProvisionBean ra2_22 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, "rowKey", rp2_1.getRowKey());
			assertNotNull(ra2_22);
			assertEquals(rp2_2.getGraphId(), ra2_22.getGraphId());
			assertFalse(rp2_2.getGraphId().longValue() == rp.getGraphId()
					.longValue());

			// TEST UPDATE
			rp2.setMemo(rp2.getMemo() + " modified");
			rp2.setIndex(9999);
			crud.save(rp2, "john peng");
			assertEquals(crud.count(RouteProvisionBean.class), 2);
			RouteProvisionBean rp3 = (RouteProvisionBean) crud.get(
					RouteProvisionBean.class, rp2.getGraphId());
			assertNotNull(rp3);
			assertNotNull(rp2.getGraphId());
			assertEquals(rp2.getIndex(), rp3.getIndex());
			assertEquals(rp2.getCreatedTime(), rp3.getCreatedTime());
			assertTrue(rp3.getCreatedTime() > 0);
			assertEquals(rp2.getCreatedUserId(), rp3.getCreatedUserId());
			assertEquals(rp3.getCreatedUserId(), "john");
			assertEquals(rp2.getMemo(), rp3.getMemo());
			assertEquals(rp2.getName(), rp3.getName());
			assertEquals(rp2.getRowKey(), rp3.getRowKey());
			assertEquals(rp2.getUpdatedTime(), rp3.getUpdatedTime());
			assertTrue(rp3.getUpdatedTime() > 0);
			assertEquals(rp2.getUpdatedUserId(), rp3.getUpdatedUserId());
			assertNull(rp3.getRouteMain());
			
			rp3.setName(rp3.getName() + " modified.");
			crud.save(rp3, "john");
			assertEquals(crud.count(RouteProvisionBean.class), 2);

			crud.delete(RouteProvisionBean.class);
			assertEquals(crud.count(RouteProvisionBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRelationRouteMain() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
			repo.delete(RouteProvisionBean.class);
			assertEquals(repo.count(RouteProvisionBean.class), 0);

			RouteMainBean rm1 = new RouteMainBean();
			rm1.setName("route main 1");
			repo.save(rm1, "john");
			RouteMainBean rm2 = new RouteMainBean();
			rm2.setName("route main 2");
			repo.save(rm2, "john");
			assertEquals(repo.count(RouteMainBean.class), 2);

			RouteProvisionBean rp1 = new RouteProvisionBean();
			rp1.setName("route provision 1");
			repo.save(rp1, "john");
			assertEquals(repo.count(RouteProvisionBean.class), 1);
			RouteProvisionBean rp1_1 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp1.getGraphId());
			assertNotNull(rp1_1);
			assertNull(rp1_1.getRouteMain());

			// TEST SAVE RELATIONS ONLY
			rp1_1.setRouteMain(rm1);
			repo.saveRelationsOnly(rp1_1);
			assertEquals(repo.count(RouteProvisionBean.class), 1);
			RouteProvisionBean rp1_2 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp1.getGraphId());
			assertNotNull(rp1_2);
			assertNotNull(rp1_2.getRouteMain());
			assertEquals(rp1_2.getRouteMain().getGraphId(), rm1.getGraphId());
			RouteMainBean rm1_1 = (RouteMainBean)repo.get(RouteMainBean.class, rm1.getGraphId());
			assertNotNull(rm1_1);
			assertNotNull(rm1_1.getProvisions());
			assertEquals(rm1_1.getProvisions().size(), 1);
			assertEquals(rm1_1.getProvisions().get(0).getGraphId(), rp1_1.getGraphId());
			rp1_2.setRouteMain(rm2);
			repo.saveRelationsOnly(rp1_2);
			assertEquals(repo.count(RouteProvisionBean.class), 1);
			RouteProvisionBean ra1_2 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp1_2.getGraphId());
			assertNotNull(ra1_2);
			assertNotNull(ra1_2.getRouteMain());
			assertEquals(ra1_2.getRouteMain().getGraphId(), rm2.getGraphId());
			RouteMainBean rm1_2 = (RouteMainBean)repo.get(RouteMainBean.class, rm1.getGraphId());
			assertNotNull(rm1_2);
			assertNotNull(rm1_2.getProvisions());
			assertEquals(rm1_2.getProvisions().size(), 0);
			RouteMainBean rm2_1 = (RouteMainBean)repo.get(RouteMainBean.class, rm2.getGraphId());
			assertNotNull(rm2_1);
			assertNotNull(rm2_1.getProvisions());
			assertEquals(rm2_1.getProvisions().size(), 1);
			assertEquals(rm2_1.getProvisions().get(0).getGraphId(), rp1_1.getGraphId());

			// TEST SAVE ENTITY AND RELATIONS
			RouteProvisionBean rp2 = new RouteProvisionBean();
			rp2.setName("activity 2");
			rp2.setRouteMain(rm2);
			repo.save(rp2, "john");
			assertEquals(repo.count(RouteProvisionBean.class), 2);
			RouteProvisionBean rp2_1 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp2.getGraphId());
			assertNotNull(rp2_1);
			assertEquals(rp2_1.getName(), rp2.getName());
			assertNotNull(rp2_1.getRouteMain());
			assertEquals(rp2_1.getRouteMain().getGraphId(), rm2.getGraphId());
			RouteMainBean rm2_2 = (RouteMainBean)repo.get(RouteMainBean.class, rm1.getGraphId());
			assertNotNull(rm2_2);
			assertNotNull(rm2_2.getProvisions());
			assertEquals(rm2_2.getProvisions().size(), 0);
			RouteMainBean rm2_3 = (RouteMainBean)repo.get(RouteMainBean.class, rm2.getGraphId());
			assertNotNull(rm2_3);
			assertNotNull(rm2_3.getProvisions());
			assertEquals(rm2_3.getProvisions().size(), 2);
			assertEquals(rm2_3.getProvisions().get(0).getGraphId(), rp1_1.getGraphId());
			assertEquals(rm2_3.getProvisions().get(1).getGraphId(), rp2_1.getGraphId());

			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
			repo.delete(RouteProvisionBean.class);
			assertEquals(repo.count(RouteProvisionBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
