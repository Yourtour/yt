package com.yt.business.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.CrudAllInOneOperate;
import com.yt.neo4j.repository.CrudOperate;

public class TestRouteMainBean {
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
			crud.delete(RouteMainBean.class);
			assertEquals(crud.count(RouteMainBean.class), 0);

			// TEST INSERT
			RouteMainBean rm = new RouteMainBean();
			rm.setName("name 1");
			rm.setRowKey("rowkey 1");
			rm.setStartDate(1000);
			rm.setEndDate(1001);
			assertNull(rm.getGraphId());
			crud.save(rm, "john");
			assertEquals(crud.count(RouteMainBean.class), 1);
			RouteMainBean rm1 = (RouteMainBean) crud.get(RouteMainBean.class,
					rm.getGraphId());
			assertNotNull(rm1);
			assertNotNull(rm1.getGraphId());
			RouteMainBean rm2 = (RouteMainBean) crud.get(RouteMainBean.class,
					"rowKey", rm.getRowKey());
			assertNotNull(rm2);
			assertNotNull(rm2.getGraphId());
			assertEquals(rm2.getRowKey(), rm.getRowKey());
			assertEquals(rm2.getCreatedTime(), rm.getCreatedTime());
			assertTrue(rm2.getCreatedTime() > 0);
			assertEquals(rm2.getCreatedUserId(), rm.getCreatedUserId());
			assertEquals(rm2.getCreatedUserId(), "john");
			assertEquals(rm2.getName(), rm.getName());
			assertEquals(rm2.getStartDate(), rm.getStartDate());
			assertEquals(rm2.getEndDate(), rm.getEndDate());
			assertEquals(rm2.getUpdatedTime(), rm.getUpdatedTime());
			assertFalse(rm2.getUpdatedTime() > 0);
			assertEquals(rm2.getUpdatedUserId(), rm.getUpdatedUserId());
			assertNull(rm2.getFromPlace());
			assertNotNull(rm2.getSchedules());
			assertEquals(rm2.getSchedules().size(), 0);
			assertNotNull(rm2.getProvisions());
			assertEquals(rm2.getProvisions().size(), 0);

			// TEST GRAPHID = NULL AND SAME ROWKEY
			rm2.setGraphId(null);
			crud.save(rm2, "john");
			assertEquals(crud.count(RouteMainBean.class), 1);
			RouteMainBean rm2_1 = (RouteMainBean) crud.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm2_1);
			RouteMainBean rm2_11 = (RouteMainBean) crud.get(
					RouteMainBean.class, "rowKey", rm2.getRowKey());
			assertNotNull(rm2_11);
			assertEquals(rm2_1.getGraphId(), rm2_11.getGraphId());
			assertEquals(rm2_1.getRowKey(), rm2_11.getRowKey());
			assertEquals(rm2_11.getGraphId(), rm.getGraphId());
			assertEquals(rm2_11.getName(), rm.getName());
			assertNull(rm2_11.getFromPlace());
			assertNotNull(rm2_11.getSchedules());
			assertEquals(rm2_11.getSchedules().size(), 0);
			assertNotNull(rm2_11.getProvisions());
			assertEquals(rm2_11.getProvisions().size(), 0);

			// TEST GRAPHID = NULL AND NOT SAME ROWKEY
			rm2_1.setGraphId(null);
			rm2_1.setRowKey(rm2_1.getRowKey() + " modified");
			crud.save(rm2_1, "john");
			assertEquals(crud.count(RouteMainBean.class), 2);
			RouteMainBean rm2_2 = (RouteMainBean) crud.get(RouteMainBean.class,
					rm2_1.getGraphId());
			assertEquals(rm2_2.getGraphId(), rm2_1.getGraphId());
			assertEquals(rm2_2.getRowKey(), rm2_1.getRowKey());
			assertEquals(rm2_2.getName(), rm2_1.getName());
			RouteMainBean rm2_22 = (RouteMainBean) crud.get(
					RouteMainBean.class, "rowKey", rm2_1.getRowKey());
			assertNotNull(rm2_22);
			assertEquals(rm2_2.getGraphId(), rm2_22.getGraphId());
			assertFalse(rm2_2.getGraphId().longValue() == rm.getGraphId()
					.longValue());
			assertNull(rm2_22.getFromPlace());
			assertNotNull(rm2_22.getSchedules());
			assertEquals(rm2_22.getSchedules().size(), 0);
			assertNotNull(rm2_22.getProvisions());
			assertEquals(rm2_22.getProvisions().size(), 0);

			// TEST UPDATE
			rm2.setName(rm2.getName() + " modified.");
			rm2.setEndDate(9999);
			crud.save(rm2, "john peng");
			assertEquals(crud.count(RouteMainBean.class), 2);
			RouteMainBean rm3 = (RouteMainBean) crud.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm3);
			assertNotNull(rm2.getGraphId());
			assertEquals(rm2.getEndDate(), rm3.getEndDate());
			assertEquals(rm2.getCreatedTime(), rm3.getCreatedTime());
			assertTrue(rm3.getCreatedTime() > 0);
			assertEquals(rm2.getCreatedUserId(), rm3.getCreatedUserId());
			assertEquals(rm3.getCreatedUserId(), "john");
			assertEquals(rm2.getName(), rm3.getName());
			assertEquals(rm2.getRowKey(), rm3.getRowKey());
			assertEquals(rm2.getUpdatedTime(), rm3.getUpdatedTime());
			assertTrue(rm3.getUpdatedTime() > 0);
			assertEquals(rm2.getUpdatedUserId(), rm3.getUpdatedUserId());
			assertNull(rm3.getFromPlace());
			assertNotNull(rm3.getSchedules());
			assertEquals(rm3.getSchedules().size(), 0);
			assertNotNull(rm3.getProvisions());
			assertEquals(rm3.getProvisions().size(), 0);

			crud.delete(RouteMainBean.class);
			assertEquals(crud.count(RouteMainBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	// TODO 相关的关系单元测试

}
