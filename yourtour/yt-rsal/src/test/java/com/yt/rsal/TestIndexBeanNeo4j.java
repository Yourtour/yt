package com.yt.rsal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.rsal.neo4j.bean.Neo4JBaseBean;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate.QueryTerm;
import com.yt.test.neo4j.bean.TestIndexBean;

public class TestIndexBeanNeo4j {
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
	}

	@Test
	public void test() {
		try {
			neo4jCRUD.delete(TestIndexBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(TestIndexBean.class), 0l);

			TestIndexBean bean = new TestIndexBean();
			bean.setRowKey("test-001");
			bean.setCode("john");
			bean.setName("彭明喜");
			bean.setAge(41);
			bean.setDescription("All the people like john.");
			bean.setMarried(true);
			neo4jCRUD.save(bean);
			TestIndexBean bean1 = (TestIndexBean) neo4jCRUD.get(
					TestIndexBean.class, bean.getRowKey());
			assertNotNull(bean1);

			Neo4jTemplate template = context.getBean(Neo4jTemplate.class);
			assertNotNull(template);

			IFullTextSearchOperate search = context
					.getBean(IFullTextSearchOperate.class);
			List<Neo4JBaseBean> list = search.query(TestIndexBean.class,
					new QueryTerm("description", "people"));
			assertTrue(list.size() == 1);
			TestIndexBean test = (TestIndexBean) list.get(0);
			assertNotNull(test);
			assertEquals("Assert the name.", bean.getName(), test.getName());
			assertTrue(test.getDescription().indexOf("people") >= 0);

			GraphRepository<TestIndexBean> repository = template
					.repositoryFor(TestIndexBean.class);
			assertNotNull(repository);
			Result<TestIndexBean> rs = repository.findAllByRange("age", 40, 50);
			assertNotNull(rs.single());
			for (TestIndexBean tib : rs) {
				assertNotNull(tib);
				assertEquals("Assert the name", tib.getName(), bean.getName());
			}
			rs = repository.findAllByRange("age", 45, 50);
			assertFalse(rs.iterator().hasNext());

			neo4jCRUD.delete(TestIndexBean.class);
			assertEquals("Assert the count when clean.",
					neo4jCRUD.count(TestIndexBean.class), 0l);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
