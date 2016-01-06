package com.yt.business.neo4j;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteActivityItemBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.neo4j.repository.UserAccountBeanRepository;
import com.yt.business.repository.RouteRepository;
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
			Date now = new Date();

			RouteServiceBean service = new RouteServiceBean();
			service.setTitle("买票服务");
			service.setMemo("买票服务");

			RouteActivityBean activity = new RouteActivityBean();
			activity.setGraphId(526l);
			service.setActivity(activity);

			repository.save(service, "tester");
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
