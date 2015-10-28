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

public class TestRouteActivityBean {
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
			crud.delete(RouteActivityBean.class);
			assertEquals(crud.count(RouteActivityBean.class), 0);

			// TEST INSERT
			RouteActivityBean ra = new RouteActivityBean();
			ra.setEndTime(1001);
			ra.setIndex(5);
			ra.setMemo("memo 1");
			ra.setName("name 1");
			ra.setRowKey("rowkey 1");
			ra.setStartTime(1000);
			assertNull(ra.getGraphId());
			crud.save(ra, "john");
			assertEquals(crud.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, ra.getGraphId());
			assertNotNull(ra1);
			assertNotNull(ra1.getGraphId());
			RouteActivityBean ra2 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, "rowKey", ra.getRowKey());
			assertNotNull(ra2);
			assertNotNull(ra2.getGraphId());
			assertEquals(ra2.getRowKey(), ra.getRowKey());
			assertEquals(ra2.getCreatedTime(), ra.getCreatedTime());
			assertTrue(ra2.getCreatedTime() > 0);
			assertEquals(ra2.getCreatedUserId(), ra.getCreatedUserId());
			assertEquals(ra2.getCreatedUserId(), "john");
			assertEquals(ra2.getIndex(), ra.getIndex());
			assertEquals(ra2.getMemo(), ra.getMemo());
			assertEquals(ra2.getName(), ra.getName());
			assertEquals(ra2.getEndTime(), ra.getEndTime());
			assertEquals(ra2.getStartTime(), ra.getStartTime());
			assertEquals(ra2.getUpdatedTime(), ra.getUpdatedTime());
			assertFalse(ra2.getUpdatedTime() > 0);
			assertEquals(ra2.getUpdatedUserId(), ra.getUpdatedUserId());
			assertNull(ra2.getResource());
			assertNull(ra2.getSchedule());

			// TEST GRAPHID = NULL AND SAME ROWKEY
			ra2.setGraphId(null);
			crud.save(ra2, "john");
			assertEquals(crud.count(RouteActivityBean.class), 1);
			RouteActivityBean ra2_1 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, ra2.getGraphId());
			assertNotNull(ra2_1);
			RouteActivityBean ra2_11 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, "rowKey", ra2.getRowKey());
			assertNotNull(ra2_11);
			assertEquals(ra2_1.getGraphId(), ra2_11.getGraphId());
			assertEquals(ra2_1.getRowKey(), ra2_11.getRowKey());
			assertEquals(ra2_11.getGraphId(), ra.getGraphId());
			assertEquals(ra2_11.getName(), ra.getName());

			// TEST GRAPHID = NULL AND NOT SAME ROWKEY
			ra2_1.setGraphId(null);
			ra2_1.setRowKey(ra2_1.getRowKey() + " modified");
			crud.save(ra2_1, "john");
			assertEquals(crud.count(RouteActivityBean.class), 2);
			RouteActivityBean ra2_2 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, ra2_1.getGraphId());
			assertEquals(ra2_2.getGraphId(), ra2_1.getGraphId());
			assertEquals(ra2_2.getRowKey(), ra2_1.getRowKey());
			assertEquals(ra2_2.getName(), ra2_1.getName());
			RouteActivityBean ra2_22 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, "rowKey", ra2_1.getRowKey());
			assertNotNull(ra2_22);
			assertEquals(ra2_2.getGraphId(), ra2_22.getGraphId());
			assertFalse(ra2_2.getGraphId().longValue() == ra.getGraphId()
					.longValue());

			// TEST UPDATE
			ra2.setMemo(ra2.getMemo() + " modified");
			ra2.setEndTime(9999);
			crud.save(ra2, "john peng");
			assertEquals(crud.count(RouteActivityBean.class), 2);
			RouteActivityBean ra3 = (RouteActivityBean) crud.get(
					RouteActivityBean.class, ra2.getGraphId());
			assertNotNull(ra3);
			assertNotNull(ra2.getGraphId());
			assertEquals(ra2.getIndex(), ra3.getIndex());
			assertEquals(ra2.getCreatedTime(), ra3.getCreatedTime());
			assertTrue(ra3.getCreatedTime() > 0);
			assertEquals(ra2.getCreatedUserId(), ra3.getCreatedUserId());
			assertEquals(ra3.getCreatedUserId(), "john");
			assertEquals(ra2.getMemo(), ra3.getMemo());
			assertEquals(ra2.getName(), ra3.getName());
			assertEquals(ra2.getStartTime(), ra3.getStartTime());
			assertEquals(ra2.getEndTime(), ra3.getEndTime());
			assertEquals(ra2.getRowKey(), ra3.getRowKey());
			assertEquals(ra2.getUpdatedTime(), ra3.getUpdatedTime());
			assertTrue(ra3.getUpdatedTime() > 0);
			assertEquals(ra2.getUpdatedUserId(), ra3.getUpdatedUserId());
			assertNull(ra3.getResource());
			assertNull(ra3.getSchedule());

			ra3.setName(ra3.getName() + " modified.");
			crud.save(ra3, "john peng");
			assertEquals(crud.count(RouteActivityBean.class), 2);

			crud.delete(RouteActivityBean.class);
			assertEquals(crud.count(RouteActivityBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationResource() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(RouteActivityBean.class);
			assertEquals(repo.count(RouteActivityBean.class), 0);
			repo.delete(SceneResourceBean.class);
			assertEquals(repo.count(SceneResourceBean.class), 0);
			repo.delete(HotelResourceBean.class);
			assertEquals(repo.count(HotelResourceBean.class), 0);
			repo.delete(RestaurantResourceBean.class);
			assertEquals(repo.count(RestaurantResourceBean.class), 0);

			SceneResourceBean s1 = new SceneResourceBean();
			s1.setCode("scene 1");
			repo.save(s1, "john");
			SceneResourceBean s2 = new SceneResourceBean();
			s2.setCode("scene 2");
			repo.save(s2, "john");
			assertEquals(repo.count(SceneResourceBean.class), 2);
			HotelResourceBean h1 = new HotelResourceBean();
			h1.setCode("hotel 1");
			repo.save(h1, "john");
			HotelResourceBean h2 = new HotelResourceBean();
			h2.setCode("hotel 2");
			repo.save(h2, "john");
			assertEquals(repo.count(HotelResourceBean.class), 2);
			RestaurantResourceBean r1 = new RestaurantResourceBean();
			r1.setCode("restaurant 1");
			repo.save(r1, "john");
			RestaurantResourceBean r2 = new RestaurantResourceBean();
			r2.setCode("restaurant 2");
			repo.save(r2, "john");
			assertEquals(repo.count(RestaurantResourceBean.class), 2);

			RouteActivityBean ra = new RouteActivityBean();
			ra.setName("route activity 1");
			ra.setRowKey(ra.getName());
			repo.save(ra, "john");
			assertEquals(repo.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra.getGraphId());
			assertNotNull(ra1);
			assertNull(ra1.getResource());

			// TEST SAVE RELATIONS ONLY
			ra1.setResource(s1);
			repo.saveRelationsOnly(ra1);
			assertEquals(repo.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1_1 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra.getGraphId());
			assertNotNull(ra1_1);
			assertNotNull(ra1_1.getResource());
			assertEquals(ra1_1.getResource().getGraphId(), s1.getGraphId());
			ra1_1.setResource(r2);
			repo.saveRelationsOnly(ra1_1);
			assertEquals(repo.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1_2 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra1_1.getGraphId());
			assertNotNull(ra1_2);
			assertNotNull(ra1_2.getResource());
			assertEquals(ra1_2.getResource().getGraphId(), r2.getGraphId());

			// TEST SAVE ENTITY AND RELATIONS
			RouteActivityBean ra2 = new RouteActivityBean();
			ra2.setName("activity 2");
			ra2.setResource(h1);
			repo.save(ra2, "john");
			assertEquals(repo.count(RouteActivityBean.class), 2);
			RouteActivityBean ra2_1 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra2.getGraphId());
			assertNotNull(ra2_1);
			assertEquals(ra2_1.getName(), ra2.getName());
			assertNotNull(ra2_1.getResource());
			assertEquals(ra2_1.getResource().getGraphId(), h1.getGraphId());

			ra2_1.setResource(r1);
			repo.saveRelationsOnly(ra2_1);
			assertEquals(repo.count(RouteActivityBean.class), 2);
			RouteActivityBean ra2_2 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra2_1.getGraphId());
			assertNotNull(ra2_2);
			assertNotNull(ra2_2.getResource());
			assertEquals(ra2_2.getResource().getGraphId(), r1.getGraphId());

			repo.delete(RouteActivityBean.class);
			assertEquals(repo.count(RouteActivityBean.class), 0);
			repo.delete(SceneResourceBean.class);
			assertEquals(repo.count(SceneResourceBean.class), 0);
			repo.delete(HotelResourceBean.class);
			assertEquals(repo.count(HotelResourceBean.class), 0);
			repo.delete(RestaurantResourceBean.class);
			assertEquals(repo.count(RestaurantResourceBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationSchedule() {
		CrudOperate repo = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(repo);

		try {
			repo.delete(RouteActivityBean.class);
			assertEquals(repo.count(RouteActivityBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);

			RouteScheduleBean rs1 = new RouteScheduleBean();
			rs1.setDate(1000);
			rs1.setDescription("route schedule 1");
			repo.save(rs1, "john");
			RouteScheduleBean rs2 = new RouteScheduleBean();
			rs2.setDate(2000);
			rs2.setDescription("route schedule 2");
			repo.save(rs2, "john");
			assertEquals(repo.count(RouteScheduleBean.class), 2);

			RouteActivityBean ra = new RouteActivityBean();
			ra.setName("route activity 1");
			repo.save(ra, "john");
			assertEquals(repo.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra.getGraphId());
			assertNotNull(ra1);
			assertNull(ra1.getSchedule());

			// TEST SAVE RELATIONS ONLY
			ra1.setSchedule(rs1);
			repo.saveRelationsOnly(ra1);
			assertEquals(repo.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1_1 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra.getGraphId());
			assertNotNull(ra1_1);
			assertNotNull(ra1_1.getSchedule());
			assertEquals(ra1_1.getSchedule().getGraphId(), rs1.getGraphId());
			RouteScheduleBean rs1_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_1);
			assertNotNull(rs1_1.getActivities());
			assertEquals(rs1_1.getActivities().size(), 1);
			assertEquals(rs1_1.getActivities().get(0).getGraphId(),
					ra1_1.getGraphId());

			ra1_1.setSchedule(rs2);
			repo.saveRelationsOnly(ra1_1);
			assertEquals(repo.count(RouteActivityBean.class), 1);
			RouteActivityBean ra1_2 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra1_1.getGraphId());
			assertNotNull(ra1_2);
			assertNotNull(ra1_2.getSchedule());
			assertEquals(ra1_2.getSchedule().getGraphId(), rs2.getGraphId());
			RouteScheduleBean rs1_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_2);
			assertNotNull(rs1_2.getActivities());
			assertEquals(rs1_2.getActivities().size(), 0);
			RouteScheduleBean rs2_1 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs2.getGraphId());
			assertNotNull(rs2_1);
			assertNotNull(rs2_1.getActivities());
			assertEquals(rs2_1.getActivities().size(), 1);
			assertEquals(rs2_1.getActivities().get(0).getGraphId(),
					ra1_2.getGraphId());

			// TEST SAVE ENTITY AND RELATIONS
			RouteActivityBean ra2 = new RouteActivityBean();
			ra2.setName("activity 2");
			ra2.setSchedule(rs1);
			repo.save(ra2, "john");
			assertEquals(repo.count(RouteActivityBean.class), 2);
			RouteActivityBean ra2_1 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra2.getGraphId());
			assertNotNull(ra2_1);
			assertEquals(ra2_1.getName(), ra2.getName());
			assertNotNull(ra2_1.getSchedule());
			assertEquals(ra2_1.getSchedule().getGraphId(), rs1.getGraphId());
			RouteScheduleBean rs1_3 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs1.getGraphId());
			assertNotNull(rs1_3);
			assertNotNull(rs1_3.getActivities());
			assertEquals(rs1_3.getActivities().size(), 1);
			assertEquals(rs1_3.getActivities().get(0).getGraphId(),
					ra2_1.getGraphId());

			ra2_1.setSchedule(rs2);
			repo.saveRelationsOnly(ra2_1);
			assertEquals(repo.count(RouteActivityBean.class), 2);
			RouteActivityBean ra2_2 = (RouteActivityBean) repo.get(
					RouteActivityBean.class, ra2_1.getGraphId());
			assertNotNull(ra2_2);
			assertNotNull(ra2_2.getSchedule());
			assertEquals(ra2_2.getSchedule().getGraphId(), rs2.getGraphId());
			RouteScheduleBean rs2_2 = (RouteScheduleBean) repo.get(
					RouteScheduleBean.class, rs2.getGraphId());
			assertNotNull(rs2_2);
			assertNotNull(rs2_2.getActivities());
			assertEquals(rs2_2.getActivities().size(), 2);
			assertEquals(rs2_2.getActivities().get(0).getGraphId(),
					ra1.getGraphId());
			assertEquals(rs2_2.getActivities().get(1).getGraphId(),
					ra2.getGraphId());

			repo.delete(RouteActivityBean.class);
			assertEquals(repo.count(RouteActivityBean.class), 0);
			repo.delete(RouteScheduleBean.class);
			assertEquals(repo.count(RouteScheduleBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
