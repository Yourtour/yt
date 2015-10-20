package com.yt.business.neo4j;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.bean.AlongBean;
import com.yt.business.common.Constants.AlongIntentionType;
import com.yt.neo4j.repository.CrudGeneralOperate;
import com.yt.neo4j.repository.CrudOperate;

public class DataGenerator {

	private ApplicationContext context;
	private CrudOperate neo4jCRUD;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {
				"neo4jApplicationContext.xml" });
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
	public void testCRUDRouteBean() {
		try {
			Date now = new Date();
			
			AlongBean along = new AlongBean();
			
			along.setAddress("浙江杭州西湖景区");
			along.setAlongDesc("机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。");
			along.setCommentNum(13);
			along.setCreatedTime(now.getTime());
			along.setCreatedUserId("111");
			along.setGroupDesc("团队计划10人，目前有4个妹子，4个爷们，再希望有2个妹子");
			along.setImageUrl("resources/images/scene_64.jpg");
			along.setIntention(AlongIntentionType.TOGETHER_CAR);
			along.setName("十一上海休闲游4");
			along.setReadNum(200);
			along.setRequestDesc("妹子，性格温和");
			
			neo4jCRUD.save(along, "tester");
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
