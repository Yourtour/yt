package com.yt.business.neo4j;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import com.yt.business.bean.*;
import com.yt.business.neo4j.repository.UserAccountBeanRepository;
import com.yt.business.repository.RouteRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.common.Constants.AlongIntentionType;
import com.yt.neo4j.repository.CrudGeneralOperate;
import com.yt.neo4j.repository.CrudOperate;

public class DataGenerator {

	private ApplicationContext context;
	private RouteRepository repository;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {
				"application-hbase-context.xml", "application-neo4j-context.xml" });
		repository = context.getBean(RouteRepository.class);
	}

	@After
	public void tearDown() throws Exception {
		// Do nothing
	}

	@Test
	public void testContext() {
		assertNotNull(context);
		assertNotNull(repository);
	}

	@Test
	public void testCRUDRouteBean() {
		try {
			ActivityItemBean bean = new ActivityItemBean();
			bean.setTitle("玉屏景区");
			bean.setImageUrl("resources/icons/icon_service.png");
			bean.setMemo("黄山玉屏景区以玉屏楼为中心，莲花峰和天都峰为主体，前山就是指这一景区。沿途有“蓬莱三岛”、“百步云梯”、“一线天”、“新一线天”、“鳌鱼洞”等景观。。");

			SceneResourceBean resource  = new SceneResourceBean();
			resource.setGraphId(8l);
			bean.setResource(resource);

			repository.save(bean, "tester");
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
