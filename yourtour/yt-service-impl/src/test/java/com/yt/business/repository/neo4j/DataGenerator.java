package com.yt.business.repository.neo4j;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataGenerator {
	protected ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {"application-rest-context.xml",
				"application-hbase-context.xml", "application-neo4j-context.xml" });
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContext() {
		assertNotNull(context);
	}
}
