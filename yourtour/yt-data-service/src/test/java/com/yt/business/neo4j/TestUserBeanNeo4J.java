package com.yt.business.neo4j;

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

import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants.GenderType;
import com.yt.business.common.Constants.Role;
import com.yt.business.common.Constants.Status;
import com.yt.neo4j.repository.CrudGeneralOperate;
import com.yt.neo4j.repository.CrudOperate;

public class TestUserBeanNeo4J {

	private ApplicationContext context;
	private CrudOperate neo4jCRUD;

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

	@Test
	public void testCRUDUserBean() {
		try {
			neo4jCRUD.delete(UserProfileBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(UserProfileBean.class), 0l);

			UserProfileBean user = new UserProfileBean();
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
			user.setRole(Role.EXPERT);
			user.setName("real name");
			user.setResidence("residence");
			user.setGender(GenderType.FEMALE);
			user.setStatus(Status.ACTIVED);
			user.setUpdatedTime(System.currentTimeMillis());
			user.setUpdatedUserId("user id");
			user.setCode("user name");
			neo4jCRUD.save(user, "tester");
			assertNotNull(user.getGraphId());
			assertEquals("Assert the count when save.",
					neo4jCRUD.count(UserProfileBean.class), 1l);
			UserProfileBean user1 = (UserProfileBean) neo4jCRUD.get(UserProfileBean.class, "rowKey",
					user.getRowKey());
			assertNotNull(user1);
			assertEquals("Assert the rowKey of the UserBean.",
					user.getRowKey(), user1.getRowKey());
			assertEquals("Assert the userName of the UserBean.",
					user1.getCode(), user1.getCode());
			assertEquals("Assert the realName of the UserBean.",
					user.getName(), user1.getName());
			assertEquals("Assert the nickName of the UserBean.",
					user.getNickName(), user1.getNickName());
			assertEquals("Assert the email of the UserBean.", user.getEmail(),
					user1.getEmail());
			assertNull(user1.getPwd());

			neo4jCRUD.delete(UserProfileBean.class);
			assertTrue(neo4jCRUD.count(UserProfileBean.class) == 0l);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
