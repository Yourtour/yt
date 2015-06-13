package com.yt.rsal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNeo4jConnection {
	private ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {
				"hbaseApplicationContext.xml", "neo4jApplicationContext.xml" });
	}

	@After
	public void tearDown() throws Exception {
		// Do nothing
	}

	@Test
	public void test() {
		assertNotNull(context);
		GraphDatabaseService db = null;
		assertNull(db);
		try {
			db = (GraphDatabaseService) context.getBean("graphDatabaseService");
			assertNotNull(db);
		} catch (Exception ex) {
			fail("Not yet implemented");
		}
	}
}
