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
			Date now = new Date();

			ExpertServiceBean service = new ExpertServiceBean();
			service.setTitle("包车");
			service.setImageUrl("resources/icons/icon_service.png");
			service.setFee("1280元/天");
			service.setFeeIncluding("接送机没有固定服务时间，根据您的航班时间和上下车地址来决定|小费油费，停车费，过路费，高速费，车辆使用费");
			service.setFeeExcluding("个人消费及其他未提及的费用，因提前或延后接送产生的费用");
			service.setWithdraw("到达日之前3天取消预订，退全款");

			UserProfileBean user = new UserProfileBean();
			user.setGraphId(282l);
			service.setUser(user);

			repository.save(service, "tester");
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
