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
			assertNull(rm2_22.getOwner());
			assertNotNull(rm2_22.getSchedules());
			assertEquals(rm2_22.getSchedules().size(), 0);
			assertNotNull(rm2_22.getProvisions());
			assertEquals(rm2_22.getProvisions().size(), 0);

			// TEST UPDATE
			rm2.setName(rm2.getName() + " modified.");
			crud.save(rm2, "john peng");
			assertEquals(crud.count(RouteMainBean.class), 2);
			RouteMainBean rm3 = (RouteMainBean) crud.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm3);
			assertNotNull(rm2.getGraphId());
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
			assertNull(rm3.getOwner());
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

	@Test
	public void testRelationFromPlace() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);

			PlaceBean p1 = new PlaceBean();
			p1.setCode("place 1");
			repo.save(p1, "john");
			PlaceBean p2 = new PlaceBean();
			p2.setCode("place 2");
			repo.save(p2, "john");
			assertEquals(repo.count(PlaceBean.class), 2);

			RouteMainBean rm1 = new RouteMainBean();
			rm1.setName("route 1");
			repo.save(rm1, "john");
			assertEquals(repo.count(RouteMainBean.class), 1);
			RouteMainBean rm1_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_1);
			assertNull(rm1_1.getFromPlace());
			repo.saveRelationsOnly(rm1_1);
			assertEquals(repo.count(RouteMainBean.class), 1);
			RouteMainBean rm1_2 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_2);
			assertNotNull(rm1_2.getFromPlace());

			RouteMainBean rm2 = new RouteMainBean();
			rm2.setName("route 2");
			repo.save(rm2, "john");
			assertEquals(repo.count(RouteMainBean.class), 2);
			RouteMainBean rm2_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm2_1);
			assertNotNull(rm2_1.getFromPlace());

			repo.saveRelationsOnly(rm1_2);
			assertEquals(repo.count(RouteMainBean.class), 2);
			assertEquals(repo.count(PlaceBean.class), 2);
			RouteMainBean rm1_3 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_3);
			assertNotNull(rm1_3.getFromPlace());

			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationOwner() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(UserProfileBean.class);
			assertEquals(repo.count(UserProfileBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);

			UserProfileBean u1 = new UserProfileBean();
			repo.save(u1, "john");
			UserProfileBean u2 = new UserProfileBean();
			repo.save(u2, "john");
			assertEquals(repo.count(UserProfileBean.class), 2);

			RouteMainBean rm1 = new RouteMainBean();
			rm1.setName("route 1");
			repo.save(rm1, "john");
			assertEquals(repo.count(RouteMainBean.class), 1);
			RouteMainBean rm1_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_1);
			assertNull(rm1_1.getOwner());
			rm1_1.setOwner(u1);
			repo.saveRelationsOnly(rm1_1);
			assertEquals(repo.count(RouteMainBean.class), 1);
			RouteMainBean rm1_2 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_2);
			assertNotNull(rm1_2.getOwner());
			assertEquals(rm1_2.getOwner().getGraphId(), u1.getGraphId());

			RouteMainBean rm2 = new RouteMainBean();
			rm2.setName("route 2");
			rm2.setOwner(u2);
			repo.save(rm2, "john");
			assertEquals(repo.count(RouteMainBean.class), 2);
			RouteMainBean rm2_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm2_1);
			assertNotNull(rm2_1.getOwner());
			assertEquals(rm2_1.getOwner().getGraphId(), u2.getGraphId());

			rm1_2.setOwner(u2);
			repo.saveRelationsOnly(rm1_2);
			assertEquals(repo.count(RouteMainBean.class), 2);
			assertEquals(repo.count(UserProfileBean.class), 2);
			RouteMainBean rm1_3 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_3);
			assertNotNull(rm1_3.getOwner());
			assertEquals(rm1_3.getOwner().getGraphId(), u2.getGraphId());

			repo.delete(UserProfileBean.class);
			assertEquals(repo.count(UserProfileBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationSchedules() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);
		try {
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);

			RouteScheduleBean rs1 = new RouteScheduleBean();
			rs1.setDate(1000l);
			repo.save(rs1, "john");
			RouteScheduleBean rs2 = new RouteScheduleBean();
			rs2.setDate(1001l);
			repo.save(rs2, "john");
			RouteScheduleBean rs3 = new RouteScheduleBean();
			rs3.setDate(1002l);
			repo.save(rs3, "john");
			RouteScheduleBean rs4 = new RouteScheduleBean();
			rs4.setDate(1003l);
			repo.save(rs4, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 4);

			RouteMainBean rm1 = new RouteMainBean();
			rm1.setName("route 1");
			rm1.getSchedules().add(rs1);
			rm1.getSchedules().add(rs2);
			repo.save(rm1, "john");
			assertEquals(repo.count(RouteMainBean.class), 1);
			RouteMainBean rm1_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_1);
			assertNotNull(rm1_1.getSchedules());
			assertEquals(rm1_1.getSchedules().size(), 2);
			assertEquals(rm1_1.getSchedules().get(0).getGraphId(),
					rs1.getGraphId());
			assertEquals(rm1_1.getSchedules().get(1).getGraphId(),
					rs2.getGraphId());
			RouteScheduleBean rs1_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_1);
			assertNotNull(rs1_1.getRouteMain());
			assertEquals(rs1_1.getRouteMain().getGraphId(), rm1_1.getGraphId());
			RouteScheduleBean rs2_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs2.getGraphId());
			assertNotNull(rs2_1);
			assertNotNull(rs2_1.getRouteMain());
			assertEquals(rs2_1.getRouteMain().getGraphId(), rm1_1.getGraphId());

			rm1_1.getSchedules().remove(1);
			rm1_1.getSchedules().add(rs3);
			rm1_1.getSchedules().add(rs4);
			repo.saveRelationsOnly(rm1_1);
			assertEquals(repo.count(RouteMainBean.class), 1);
			assertEquals(repo.count(RouteScheduleBean.class), 4);
			RouteMainBean rm1_2 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1_1.getGraphId());
			assertNotNull(rm1_2);
			assertNotNull(rm1_2.getSchedules());
			assertEquals(rm1_2.getSchedules().size(), 3);
			assertEquals(rm1_2.getSchedules().get(0).getGraphId(),
					rs1.getGraphId());
			assertEquals(rm1_2.getSchedules().get(1).getGraphId(),
					rs3.getGraphId());
			assertEquals(rm1_2.getSchedules().get(2).getGraphId(),
					rs4.getGraphId());
			RouteScheduleBean rs1_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_2);
			assertNotNull(rs1_2.getRouteMain());
			assertEquals(rs1_2.getRouteMain().getGraphId(), rm1_1.getGraphId());
			RouteScheduleBean rs2_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs2.getGraphId());
			assertNotNull(rs2_2);
			assertNull(rs2_2.getRouteMain());
			RouteScheduleBean rs3_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs3.getGraphId());
			assertNotNull(rs3_2);
			assertNotNull(rs3_2.getRouteMain());
			assertEquals(rs3_2.getRouteMain().getGraphId(), rm1_1.getGraphId());
			RouteScheduleBean rs4_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs4.getGraphId());
			assertNotNull(rs4_2);
			assertNotNull(rs4_2.getRouteMain());
			assertEquals(rs4_2.getRouteMain().getGraphId(), rm1_1.getGraphId());

			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationProvisions() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);
		try {
			repo.delete(RouteProvisionBean.class);
			assertEquals(repo.count(RouteProvisionBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);

			RouteProvisionBean rp1 = new RouteProvisionBean();
			repo.save(rp1, "john");
			RouteProvisionBean rp2 = new RouteProvisionBean();
			repo.save(rp2, "john");
			RouteProvisionBean rp3 = new RouteProvisionBean();
			repo.save(rp3, "john");
			RouteProvisionBean rp4 = new RouteProvisionBean();
			repo.save(rp4, "john");
			assertEquals(repo.count(RouteProvisionBean.class), 4);

			RouteMainBean rm1 = new RouteMainBean();
			rm1.setName("route 1");
			rm1.getProvisions().add(rp1);
			rm1.getProvisions().add(rp2);
			repo.save(rm1, "john");
			assertEquals(repo.count(RouteMainBean.class), 1);
			RouteMainBean rm1_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_1);
			assertNotNull(rm1_1.getProvisions());
			assertEquals(rm1_1.getProvisions().size(), 2);
			assertEquals(rm1_1.getProvisions().get(0).getGraphId(),
					rp1.getGraphId());
			assertEquals(rm1_1.getProvisions().get(1).getGraphId(),
					rp2.getGraphId());
			RouteScheduleBean rp1_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rp1.getGraphId());
			assertNotNull(rp1_1);
			assertNotNull(rp1_1.getRouteMain());
			assertEquals(rp1_1.getRouteMain().getGraphId(), rm1_1.getGraphId());
			RouteProvisionBean rp2_1 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp2.getGraphId());
			assertNotNull(rp2_1);
			assertNotNull(rp2_1.getRouteMain());
			assertEquals(rp2_1.getRouteMain().getGraphId(), rm1_1.getGraphId());

			rm1_1.getProvisions().remove(1);
			rm1_1.getProvisions().add(rp3);
			rm1_1.getProvisions().add(rp4);
			repo.saveRelationsOnly(rm1_1);
			assertEquals(repo.count(RouteMainBean.class), 1);
			assertEquals(repo.count(RouteProvisionBean.class), 4);
			RouteMainBean rm1_2 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1_1.getGraphId());
			assertNotNull(rm1_2);
			assertNotNull(rm1_2.getProvisions());
			assertEquals(rm1_2.getProvisions().size(), 3);
			assertEquals(rm1_2.getProvisions().get(0).getGraphId(),
					rp1.getGraphId());
			assertEquals(rm1_2.getProvisions().get(1).getGraphId(),
					rp3.getGraphId());
			assertEquals(rm1_2.getProvisions().get(2).getGraphId(),
					rp4.getGraphId());
			RouteProvisionBean rp1_2 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp1.getGraphId());
			assertNotNull(rp1_2);
			assertNotNull(rp1_2.getRouteMain());
			assertEquals(rp1_2.getRouteMain().getGraphId(), rm1_1.getGraphId());
			RouteProvisionBean rp2_2 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp2.getGraphId());
			assertNotNull(rp2_2);
			assertNull(rp2_2.getRouteMain());
			RouteProvisionBean rp3_2 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp3.getGraphId());
			assertNotNull(rp3_2);
			assertNotNull(rp3_2.getRouteMain());
			assertEquals(rp3_2.getRouteMain().getGraphId(), rm1_1.getGraphId());
			RouteProvisionBean rp4_2 = (RouteProvisionBean) repo.get(
					RouteProvisionBean.class, rp4.getGraphId());
			assertNotNull(rp4_2);
			assertNotNull(rp4_2.getRouteMain());
			assertEquals(rp4_2.getRouteMain().getGraphId(), rm1_1.getGraphId());

			repo.delete(RouteProvisionBean.class);
			assertEquals(repo.count(RouteProvisionBean.class), 0);
			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
