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

public class TestRouteScheduleBean {
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
			crud.delete(RouteScheduleBean.class);
			assertEquals(crud.count(RouteScheduleBean.class), 0);

			// TEST INSERT
			RouteScheduleBean rm = new RouteScheduleBean();
			rm.setIndex(3);
			rm.setRowKey("rowkey 1");
			rm.setDate(1000);
			rm.setDescription("description 1");
			assertNull(rm.getGraphId());
			crud.save(rm, "john");
			assertEquals(crud.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rm1 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, rm.getGraphId());
			assertNotNull(rm1);
			assertNotNull(rm1.getGraphId());
			RouteScheduleBean rm2 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, "rowKey", rm.getRowKey());
			assertNotNull(rm2);
			assertNotNull(rm2.getGraphId());
			assertEquals(rm2.getRowKey(), rm.getRowKey());
			assertEquals(rm2.getCreatedTime(), rm.getCreatedTime());
			assertTrue(rm2.getCreatedTime() > 0);
			assertEquals(rm2.getCreatedUserId(), rm.getCreatedUserId());
			assertEquals(rm2.getCreatedUserId(), "john");
			assertEquals(rm2.getDescription(), rm.getDescription());
			assertEquals(rm2.getDate(), rm.getDate());
			assertEquals(rm2.getIndex(), rm.getIndex());
			assertEquals(rm2.getUpdatedTime(), rm.getUpdatedTime());
			assertFalse(rm2.getUpdatedTime() > 0);
			assertEquals(rm2.getUpdatedUserId(), rm.getUpdatedUserId());
			assertNull(rm2.getPlace());
			assertNull(rm2.getRouteMain());
			assertNotNull(rm2.getActivities());
			assertEquals(rm2.getActivities().size(), 0);

			// TEST GRAPHID = NULL AND SAME ROWKEY
			rm2.setGraphId(null);
			crud.save(rm2, "john");
			assertEquals(crud.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rm2_1 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, rm2.getGraphId());
			assertNotNull(rm2_1);
			RouteScheduleBean rm2_11 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, "rowKey", rm2.getRowKey());
			assertNotNull(rm2_11);
			assertEquals(rm2_1.getGraphId(), rm2_11.getGraphId());
			assertEquals(rm2_1.getRowKey(), rm2_11.getRowKey());
			assertEquals(rm2_11.getGraphId(), rm.getGraphId());
			assertEquals(rm2_11.getDescription(), rm.getDescription());
			assertNull(rm2_11.getPlace());
			assertNull(rm2_11.getRouteMain());
			assertNotNull(rm2_11.getActivities());
			assertEquals(rm2_11.getActivities().size(), 0);

			// TEST GRAPHID = NULL AND NOT SAME ROWKEY
			rm2_1.setGraphId(null);
			rm2_1.setRowKey(rm2_1.getRowKey() + " modified");
			crud.save(rm2_1, "john");
			assertEquals(crud.count(RouteScheduleBean.class), 2);
			RouteScheduleBean rm2_2 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, rm2_1.getGraphId());
			assertEquals(rm2_2.getGraphId(), rm2_1.getGraphId());
			assertEquals(rm2_2.getRowKey(), rm2_1.getRowKey());
			assertEquals(rm2_2.getDate(), rm2_1.getDate());
			RouteScheduleBean rm2_22 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, "rowKey", rm2_1.getRowKey());
			assertNotNull(rm2_22);
			assertEquals(rm2_2.getGraphId(), rm2_22.getGraphId());
			assertFalse(rm2_2.getGraphId().longValue() == rm.getGraphId()
					.longValue());
			assertNull(rm2_22.getPlace());
			assertNull(rm2_22.getRouteMain());
			assertNotNull(rm2_22.getActivities());
			assertEquals(rm2_22.getActivities().size(), 0);

			// TEST UPDATE
			rm2.setDescription(rm2.getDescription() + " modified.");
			crud.save(rm2, "john peng");
			assertEquals(crud.count(RouteScheduleBean.class), 2);
			RouteScheduleBean rm3 = (RouteScheduleBean) crud.get(
					RouteScheduleBean.class, rm2.getGraphId());
			assertNotNull(rm3);
			assertNotNull(rm2.getGraphId());
			assertEquals(rm2.getDate(), rm3.getDate());
			assertEquals(rm2.getDescription(), rm3.getDescription());
			assertEquals(rm2.getCreatedTime(), rm3.getCreatedTime());
			assertTrue(rm3.getCreatedTime() > 0);
			assertEquals(rm2.getCreatedUserId(), rm3.getCreatedUserId());
			assertEquals(rm3.getCreatedUserId(), "john");
			assertEquals(rm2.getRowKey(), rm3.getRowKey());
			assertEquals(rm2.getUpdatedTime(), rm3.getUpdatedTime());
			assertTrue(rm3.getUpdatedTime() > 0);
			assertEquals(rm2.getUpdatedUserId(), rm3.getUpdatedUserId());
			assertNull(rm3.getPlace());
			assertNull(rm3.getRouteMain());
			assertNotNull(rm3.getActivities());
			assertEquals(rm3.getActivities().size(), 0);

			rm3.setDate(999);
			crud.save(rm3, "john");
			assertEquals(crud.count(RouteScheduleBean.class), 2);

			crud.delete(RouteScheduleBean.class);
			assertEquals(crud.count(RouteScheduleBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationPlace() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);

			PlaceBean p1 = new PlaceBean();
			p1.setCode("place 1");
			repo.save(p1, "john");
			PlaceBean p2 = new PlaceBean();
			p2.setCode("place 2");
			repo.save(p2, "john");
			assertEquals(repo.count(PlaceBean.class), 2);

			RouteScheduleBean rs1 = new RouteScheduleBean();
			rs1.setDate(1000);
			repo.save(rs1, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rs1_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_1);
			assertNull(rs1_1.getPlace());
			rs1_1.setPlace(p1);
			repo.saveRelationsOnly(rs1_1);
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rs1_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_2);
			assertNotNull(rs1_2.getPlace());
			assertEquals(rs1_2.getPlace().getGraphId(), p1.getGraphId());

			RouteScheduleBean rs2 = new RouteScheduleBean();
			rs2.setDate(2000);
			rs2.setPlace(p2);
			repo.save(rs2, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 2);
			RouteScheduleBean rs2_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs2.getGraphId());
			assertNotNull(rs2_1);
			assertNotNull(rs2_1.getPlace());
			assertEquals(rs2_1.getPlace().getGraphId(), p2.getGraphId());

			rs1_2.setPlace(p2);
			repo.saveRelationsOnly(rs1_2);
			assertEquals(repo.count(RouteScheduleBean.class), 2);
			assertEquals(repo.count(PlaceBean.class), 2);
			RouteScheduleBean rs1_3 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_3);
			assertNotNull(rs1_3.getPlace());
			assertEquals(rs1_3.getPlace().getGraphId(), p2.getGraphId());

			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
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
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);

			RouteMainBean rm1 = new RouteMainBean();
			rm1.setName("route main 1");
			repo.save(rm1, "john");
			RouteMainBean rm2 = new RouteMainBean();
			rm2.setName("route main 2");
			repo.save(rm2, "john");
			assertEquals(repo.count(RouteMainBean.class), 2);

			RouteScheduleBean rs1 = new RouteScheduleBean();
			rs1.setDate(1000);
			rs1.setDescription("route schedule 1");
			repo.save(rs1, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rs1_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_1);
			assertNull(rs1_1.getRouteMain());

			// TEST SAVE RELATIONS ONLY
			rs1_1.setRouteMain(rm1);
			repo.saveRelationsOnly(rs1_1);
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rs1_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_2);
			assertNotNull(rs1_2.getRouteMain());
			assertEquals(rs1_2.getRouteMain().getGraphId(), rm1.getGraphId());
			RouteMainBean rm1_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_1);
			assertNotNull(rm1_1.getSchedules());
			assertEquals(rm1_1.getSchedules().size(), 1);
			assertEquals(rm1_1.getSchedules().get(0).getGraphId(),
					rs1_1.getGraphId());
			rs1_2.setRouteMain(rm2);
			repo.saveRelationsOnly(rs1_2);
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rs1_3 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1_2.getGraphId());
			assertNotNull(rs1_3);
			assertNotNull(rs1_3.getRouteMain());
			assertEquals(rs1_3.getRouteMain().getGraphId(), rm2.getGraphId());
			RouteMainBean rm1_2 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm1_2);
			assertNotNull(rm1_2.getSchedules());
			assertEquals(rm1_2.getSchedules().size(), 0);
			RouteMainBean rm2_1 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm2_1);
			assertNotNull(rm2_1.getSchedules());
			assertEquals(rm2_1.getSchedules().size(), 1);
			assertEquals(rm2_1.getSchedules().get(0).getGraphId(),
					rs1_1.getGraphId());

			// TEST SAVE ENTITY AND RELATIONS
			RouteScheduleBean rs2 = new RouteScheduleBean();
			rs2.setDate(2000);
			rs2.setRouteMain(rm2);
			repo.save(rs2, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 2);
			RouteScheduleBean rs2_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs2.getGraphId());
			assertNotNull(rs2_1);
			assertEquals(rs2_1.getDate(), rs2.getDate());
			assertNotNull(rs2_1.getRouteMain());
			assertEquals(rs2_1.getRouteMain().getGraphId(), rm2.getGraphId());
			RouteMainBean rm2_2 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm1.getGraphId());
			assertNotNull(rm2_2);
			assertNotNull(rm2_2.getSchedules());
			assertEquals(rm2_2.getSchedules().size(), 0);
			RouteMainBean rm2_3 = (RouteMainBean) repo.get(RouteMainBean.class,
					rm2.getGraphId());
			assertNotNull(rm2_3);
			assertNotNull(rm2_3.getSchedules());
			assertEquals(rm2_3.getSchedules().size(), 2);
			assertEquals(rm2_3.getSchedules().get(0).getGraphId(),
					rs1_1.getGraphId());
			assertEquals(rm2_3.getSchedules().get(1).getGraphId(),
					rs2_1.getGraphId());

			repo.delete(RouteMainBean.class);
			assertEquals(repo.count(RouteMainBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationActivity() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(RouteActivityBean.class);
			assertEquals(repo.count(RouteActivityBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
			
			RouteActivityBean ra1 = new RouteActivityBean();
			ra1.setName("route activity 1");
			repo.save(ra1, "john");
			RouteActivityBean ra2 = new RouteActivityBean();
			ra2.setName("activity 2");
			repo.save(ra2, "john");
			RouteActivityBean ra3 = new RouteActivityBean();
			ra3.setName("activity 3");
			repo.save(ra3, "john");
			RouteActivityBean ra4 = new RouteActivityBean();
			ra4.setName("activity 4");
			repo.save(ra4, "john");
			assertEquals(repo.count(RouteActivityBean.class), 4);
			
			RouteScheduleBean rs1 = new RouteScheduleBean();
			rs1.setDate(1000);
			rs1.setDescription("route schedule 1");
			rs1.getActivities().add(ra1);
			rs1.getActivities().add(ra2);
			repo.save(rs1, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			RouteScheduleBean rs1_1 = (RouteScheduleBean)repo.get(RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_1);
			assertNotNull(rs1_1.getActivities());
			assertEquals(rs1_1.getActivities().size(), 2);
			assertEquals(rs1_1.getActivities().get(0).getGraphId(), ra1.getGraphId());
			assertEquals(rs1_1.getActivities().get(1).getGraphId(), ra2.getGraphId());
			RouteActivityBean ra1_1 = (RouteActivityBean)repo.get(RouteActivityBean.class, ra1.getGraphId());
			assertNotNull(ra1_1);
			assertNotNull(ra1_1.getSchedule());
			assertEquals(ra1_1.getSchedule().getGraphId(), rs1.getGraphId());
			RouteActivityBean ra2_1 = (RouteActivityBean)repo.get(RouteActivityBean.class, ra2.getGraphId());
			assertNotNull(ra2_1);
			assertNotNull(ra2_1.getSchedule());
			assertEquals(ra2_1.getSchedule().getGraphId(), rs1.getGraphId());
			
			rs1_1.getActivities().remove(1);
			rs1_1.getActivities().add(ra3);
			rs1_1.getActivities().add(ra4);
			repo.saveRelationsOnly(rs1_1);
			assertEquals(repo.count(RouteScheduleBean.class), 1);
			assertEquals(repo.count(RouteActivityBean.class), 4);
			RouteScheduleBean rs2_1 = (RouteScheduleBean)repo.get(RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs2_1);
			assertNotNull(rs2_1.getActivities());
			assertEquals(rs2_1.getActivities().size(), 3);
			assertEquals(rs2_1.getActivities().get(0).getGraphId(), ra1.getGraphId());
			assertEquals(rs2_1.getActivities().get(1).getGraphId(), ra3.getGraphId());
			assertEquals(rs2_1.getActivities().get(2).getGraphId(), ra4.getGraphId());
			RouteActivityBean ra1_2 = (RouteActivityBean)repo.get(RouteActivityBean.class, ra1.getGraphId());
			assertNotNull(ra1_2);
			assertNotNull(ra1_2.getSchedule());
			assertEquals(ra1_2.getSchedule().getGraphId(), rs1.getGraphId());
			RouteActivityBean ra2_2 = (RouteActivityBean)repo.get(RouteActivityBean.class, ra2.getGraphId());
			assertNotNull(ra2_2);
			assertNull(ra2_2.getSchedule());
			RouteActivityBean ra3_1 = (RouteActivityBean)repo.get(RouteActivityBean.class, ra3.getGraphId());
			assertNotNull(ra3_1);
			assertNotNull(ra3_1.getSchedule());
			assertEquals(ra3_1.getSchedule().getGraphId(), rs1.getGraphId());
			RouteActivityBean ra4_1 = (RouteActivityBean)repo.get(RouteActivityBean.class, ra4.getGraphId());
			assertNotNull(ra4_1);
			assertNotNull(ra4_1.getSchedule());
			assertEquals(ra4_1.getSchedule().getGraphId(), rs1.getGraphId());
			
			repo.delete(RouteActivityBean.class);
			assertEquals(repo.count(RouteActivityBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
