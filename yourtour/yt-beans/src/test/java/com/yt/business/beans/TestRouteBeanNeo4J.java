package com.yt.business.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.business.bean.RouteBean;
import com.yt.business.common.Constants.Status;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate.QueryTerm;

public class TestRouteBeanNeo4J {

	private ApplicationContext context;
	private ICrudOperate neo4jCRUD;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {
				"hbaseApplicationContext.xml", "neo4jApplicationContext.xml" });
		neo4jCRUD = context.getBean(CrudGeneralOperate.class);
	}

	@After
	public void tearDown() throws Exception {
		// Do nothing
	}

	@Test
	public void testContext() {
		assertNotNull(context);
		assertNotNull(neo4jCRUD);
	}

	public RouteBean createRoute() {
		RouteBean route = new RouteBean();
		route.setRowKey("route-001");
		route.setName("五台山三日游");
		route.setFeature("五台山是中国佛教寺庙建筑最早的建造地之一。自五台山中的寺院五台山中的寺院东汉永平（公元58年～75年）年间起，历代修造的寺庙鳞次栉比，佛塔摩天，殿宇巍峨，金碧辉煌，是中国历代建筑荟萃之地。雕塑、石刻、壁画、书法遍及各寺，均具有很高的艺术价值。");
		route.setIntro("五台山（Mount Wutai）位于山西省忻州市五台县境内，位列中国佛教四大名山之首。五台山位于山西省东北部，隶属忻州市五台县，西南距省会太原市230公里，与浙江普陀山、安徽九华山、四川峨眉山、共称“中国佛教四大名山”。五台山与尼泊尔蓝毗尼花园、印度鹿野苑、菩提伽耶、拘尸那迦并称为世界五大佛教圣地。");
		route.setPeriod(3 * 24 * 3600);
		route.setPlace("山西省忻州市五台县");
		route.setReason("五台山位居中国四大佛教名山之首，称为“金五台”，为文殊菩萨的道场。五台山并非一座山，它是坐落于“华北屋脊”之上的一系列山峰群，景区总面积达2837平方公里，最高海拔3061米。五座山峰（东台望海峰、南台锦绣峰、中台翠岩峰、西台挂月峰、北台叶斗峰）环抱整片区域，顶无林木而平坦宽阔，犹如垒土之台，故而得名。五台山是中国唯一一个青庙（汉传佛教）黄庙（藏传佛教）交相辉映的佛教道场，因此汉蒙藏等民族在此和谐共处。五台山据传拥有寺庙128座，现存寺院共47处，台内39处，台外8处，其中多敕建寺院，多朝皇帝前来参拜。著名的有：显通寺、塔院寺、菩萨顶、南山寺、黛螺顶、广济寺、万佛阁等。");
		route.setStatus(Status.ACTIVED);

		route.setCreatedTime(System.currentTimeMillis());
		route.setCreatedUserId("John Peng");
		route.setStartTime(System.currentTimeMillis());
		route.setEndTime(System.currentTimeMillis());
		route.setImageUrl("/img/route-001.png");
		route.setUpdatedTime(System.currentTimeMillis());
		route.setUpdatedUserId("john");
		return route;
	}

	@Test
	public void testCRUDRouteBean() {
		try {
			neo4jCRUD.delete(RouteBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(RouteBean.class), 0l);

			RouteBean route = createRoute();
			assertNull(route.getGraphId());
			neo4jCRUD.save(route);

			assertNotNull(route.getGraphId());
			assertEquals("Assert the count when save.",
					neo4jCRUD.count(RouteBean.class), 1l);

			RouteBean route1 = (RouteBean) neo4jCRUD.get(RouteBean.class,
					route.getRowKey());
			assertNotNull(route1);
			assertEquals("Assert the rowKey", route.getRowKey(),
					route1.getRowKey());
			assertEquals("Assert the name", route.getName(), route1.getName());
			assertEquals("Assert the feature", route.getFeature(),
					route1.getFeature());
			assertEquals("Assert the Introduction", route.getIntro(),
					route1.getIntro());
			assertEquals("Assert the traval period", route.getPeriod(),
					route1.getPeriod());
			assertEquals("Assert the place", route.getPlace(),
					route1.getPlace());
			assertEquals("Assert the reason", route.getReason(),
					route1.getReason());
			assertEquals("assert the status", route.getStatus().name(), route1
					.getStatus().name());
			assertTrue(route.getStartTime() == route1.getStartTime());
			assertTrue(route.getEndTime() == route1.getEndTime());

			assertFalse(route.getCreatedUserId().equals(
					route1.getCreatedUserId()));
			assertFalse(route.getUpdatedUserId().equals(
					route1.getUpdatedUserId()));
			assertFalse(route.getImageUrl().equals(route1.getImageUrl()));
			assertFalse(route.getCreatedTime() == route1.getCreatedTime());
			assertFalse(route.getUpdatedTime() == route1.getUpdatedTime());

			neo4jCRUD.delete(RouteBean.class);
			assertTrue(neo4jCRUD.count(RouteBean.class) == 0l);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testRouteBeanIndex() {
		try {
			Neo4jTemplate template = context.getBean(Neo4jTemplate.class);
			assertNotNull(template);

			neo4jCRUD.delete(RouteBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(RouteBean.class), 0l);

			RouteBean route = createRoute();
			assertNull(route.getGraphId());
			neo4jCRUD.save(route);

			// test fulltext search
			IFullTextSearchOperate search = context
					.getBean(IFullTextSearchOperate.class);
			List<Neo4JBaseBean> list = search.query(RouteBean.class,
					new QueryTerm("intro", "五台山"));
			assertTrue(list.size() == 1);
			RouteBean route1 = (RouteBean) list.get(0);
			assertNotNull(route1);
			assertEquals("Assert the name.", route.getName(), route1.getName());
			assertTrue(route1.getIntro().indexOf("五台山") >= 0);

			Vector<QueryTerm> query = new Vector<QueryTerm>();
			query.add(new QueryTerm("intro", "五台山"));
			query.add(new QueryTerm("feature", "东汉永平"));
			list = search.query(RouteBean.class, query);
			assertTrue(list.size() == 1);
			RouteBean route2 = (RouteBean) list.get(0);
			assertNotNull(route2);
			assertEquals("Assert the name.", route.getName(), route2.getName());
			assertTrue(route2.getFeature().indexOf("东汉永平") >= 0);

			RouteBean r1 = createRoute();
			r1.setRowKey("route-002");
			r1.setFeature("中华人民共和国");
			neo4jCRUD.save(r1);

			list = search.query(RouteBean.class, new QueryTerm("intro", "五台山"));
			assertTrue(list.size() == 2);
			RouteBean r11 = (RouteBean) list.get(0);
			assertNotNull(r11);
			assertEquals("Assert the name.", route.getName(), r11.getName());
			assertTrue(r11.getIntro().indexOf("五台山") >= 0);
			RouteBean r12 = (RouteBean) list.get(1);
			assertNotNull(r12);
			assertEquals("Assert the name.", r1.getName(), r12.getName());
			assertTrue(r12.getIntro().indexOf("五台山") >= 0);

			query = new Vector<QueryTerm>();
			query.add(new QueryTerm("intro", "五台山"));
			query.add(new QueryTerm("feature", "东汉永平"));
			list = search.query(RouteBean.class, query);
			assertTrue(list.size() == 1);
			RouteBean r21 = (RouteBean) list.get(0);
			assertNotNull(r21);
			assertEquals("Assert the name.", route.getName(), r21.getName());
			assertTrue(r21.getFeature().indexOf("东汉永平") >= 0);

			query = new Vector<QueryTerm>();
			query.add(new QueryTerm("intro", "五台山"));
			query.add(new QueryTerm("feature", "共和国"));
			list = search.query(RouteBean.class, query);
			assertTrue(list.size() == 1);
			RouteBean r22 = (RouteBean) list.get(0);
			assertNotNull(r22);
			assertEquals("Assert the name.", r1.getName(), r22.getName());
			assertTrue(r22.getFeature().indexOf("共和国") >= 0);

			neo4jCRUD.delete(RouteBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(RouteBean.class), 0l);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

}
