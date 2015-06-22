package com.yt.rsal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.rsal.neo4j.repository.CrudGeneralOperate;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.test.neo4j.bean.Constants.Status;
import com.yt.test.neo4j.bean.RouteBean;
import com.yt.test.neo4j.bean.UserBean;
import com.yt.test.neo4j.bean.UserBean.RATE;

public class TestUserBeanNeo4J {

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

	// @Test
	// public void testMovie() {
	// try {
	// MovieRepository repository = context.getBean(MovieRepository.class);
	// assertNotNull(repository);
	//
	// repository.deleteAll();
	// assertTrue(repository.count() == 0l);
	// assertTrue(template.count(Person.class) == 0l);
	//
	// Movie m = new Movie();
	// m.setTitle("matrix");
	// Person director = new Person();
	// director.setName("john");
	// m.setDirector(director);
	// m.addActor(director);
	// assertNull(m.getId());
	// repository.save(m);
	// assertNotNull(m.getId());
	// Movie m1 = repository.findOne(m.getId());
	// //Movie m1 = repository.findBySchemaPropertyValue("title", "matrix");
	// assertNotNull(m1);
	// assertNotNull(m1.getDirector());
	// assertTrue(m1.getActors().size() == 1);
	//
	// m.getActors().remove(director);
	// m.setDirector(null);
	// repository.save(m);
	//
	// repository.deleteAll();
	// template.delete(director);
	//
	// assertTrue(repository.count() == 0l);
	// assertTrue(template.count(Person.class) == 0l);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// fail(ex.getMessage());
	// }
	// }

	@Test
	public void testCRUDUserBean() {
		try {
			neo4jCRUD.delete(UserBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(UserBean.class), 0l);

			UserBean user = new UserBean();
			assertNull(user.getGraphId());
			user.setRowKey("user001");
			user.setBirthday(System.currentTimeMillis());
			user.setCharacter("character");
			user.setConstellation("constellation");
			user.setCreatedTime(System.currentTimeMillis());
			user.setEmail("email");
			user.setImageUrl("image url");
			user.setMobileNo("mobile");
			user.setNativePlace("native place");
			user.setNickName("nick name");
			user.setPwd("password");
			user.setRate(RATE.EXPERT);
			user.setRealName("real name");
			user.setResidence("residence");
			user.setRowKey("rowkey");
			user.setSex("F");
			user.setStatus(Status.ACTIVED);
			user.setUpdatedTime(System.currentTimeMillis());
			user.setUpdatedUserId("user id");
			user.setUserName("user name");
			neo4jCRUD.save(user);
			assertNotNull(user.getGraphId());
			assertEquals("Assert the count when save.",
					neo4jCRUD.count(UserBean.class), 1l);
			UserBean user1 = (UserBean) neo4jCRUD.get(UserBean.class,
					user.getRowKey());
			assertNotNull(user1);
			assertEquals("Assert the rowKey of the UserBean.",
					user.getRowKey(), user1.getRowKey());
			assertEquals("Assert the userName of the UserBean.",
					user1.getUserName(), user1.getUserName());
			assertEquals("Assert the realName of the UserBean.",
					user.getRealName(), user1.getRealName());
			assertEquals("Assert the nickName of the UserBean.",
					user.getNickName(), user1.getNickName());
			assertNull(user1.getEmail());
			assertNull(user1.getPwd());

			neo4jCRUD.delete(UserBean.class);
			assertTrue(neo4jCRUD.count(UserBean.class) == 0l);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testUserBeanRelations() {
		try {
			// clean
			neo4jCRUD.delete(UserBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(UserBean.class), 0l);
			neo4jCRUD.delete(RouteBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(RouteBean.class), 0l);

			// ready
			RouteBean route = new RouteBean();
			route.setRowKey("route001");
			route.setName("五台山四日游");
			route.setPlace("五台山");
			route.setStatus(Status.ACTIVED);
			UserBean john = new UserBean();
			john.setRowKey("user001");
			john.setUserName("John Peng");
			john.setNickName("john");
			john.setRealName("彭明喜");
			john.watchRoute(route);
			UserBean tony = new UserBean();
			tony.setRowKey("user002");
			tony.setUserName("Tony Zhang");
			tony.setRealName("张林平");
			tony.setNickName("tony");
			tony.playWith(john);
			john.followMe(tony);
			tony.watchRoute(route);
			UserBean nan = new UserBean();
			nan.setRowKey("user003");
			nan.setUserName("Nan Fang");
			nan.setNickName("nan");
			nan.setRealName("方楠");
			john.playWith(nan);
			nan.followMe(john);
			nan.watchRoute(route);
			neo4jCRUD.save(route);
			neo4jCRUD.save(john);
			neo4jCRUD.save(tony);
			neo4jCRUD.save(nan);
			assertEquals("Assert the count of UserBean after save.",
					neo4jCRUD.count(UserBean.class), 3l);
			assertEquals("Assert the count of RouteBean after save.",
					neo4jCRUD.count(RouteBean.class), 1l);

			// check relationships (playmates)
			RouteBean route1 = (RouteBean) neo4jCRUD.get(RouteBean.class,
					route.getRowKey());
			assertNotNull(route1);
			UserBean john1 = (UserBean) neo4jCRUD.get(UserBean.class,
					john.getRowKey());
			assertNotNull(john1);
			assertEquals("Assert the userName of UserBean after save.",
					john.getUserName(), john1.getUserName());
			assertEquals("Assert the playmates of UserBean after save.", john1
					.getPlaymates().size(), 2);
			UserBean tony1 = (UserBean) neo4jCRUD.get(UserBean.class,
					tony.getRowKey());
			assertNotNull(tony1);
			assertEquals("Assert the userName of UserBean after save.",
					tony.getUserName(), tony1.getUserName());
			assertEquals("Assert the playmates of UserBean after save.", tony1
					.getPlaymates().size(), 1);
			UserBean nan1 = (UserBean) neo4jCRUD.get(UserBean.class,
					nan.getRowKey());
			assertNotNull(nan1);
			assertEquals("Assert the userName of UserBean after save.",
					nan.getUserName(), nan1.getUserName());
			assertEquals("Assert the playmates of UserBean after save.", nan1
					.getPlaymates().size(), 1);
			// check relationships (follows)
			assertEquals("Assert the follow of UserBean after save.", nan1
					.getFollows().size(), 1);
			assertEquals("Assert the follow of UserBean after save.", john1
					.getFollows().size(), 1);
			assertEquals("Assert the follow of UserBean after save.", tony1
					.getFollows().size(), 0);
			// chech relationships (WatchRoute)
			assertEquals("Assert the watched routeBean of UserBean after save.", nan1
					.getWatchRoutes().size(), 1);
			assertEquals("Assert the watched routeBean of UserBean after save.", john1
					.getWatchRoutes().size(), 1);
			assertEquals("Assert the watched routeBean of UserBean after save.", tony1
					.getWatchRoutes().size(), 1);
			
			// TODO test relationship query

			// clean
			neo4jCRUD.delete(UserBean.class);
			assertEquals("Assert the count of UserBean when clean.",
					neo4jCRUD.count(UserBean.class), 0l);
			neo4jCRUD.delete(RouteBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(RouteBean.class), 0l);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
