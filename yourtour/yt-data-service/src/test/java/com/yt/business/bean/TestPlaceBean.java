package com.yt.business.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.common.Constants.Status;
import com.yt.business.repository.PlaceRepository;
import com.yt.neo4j.repository.CrudOperate;

public class TestPlaceBean {
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] {
				"application-hbase-context.xml",
				"application-neo4j-context.xml" });
		assertNotNull(context);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
		context = null;
	}

	@Test
	public void testBeanDefined() {
		CrudAllInOneOperate crud = context.getBean("crudAllInOneOperate",
				CrudAllInOneOperate.class);
		assertNotNull(crud);
	}

	@Test
	public void testBaseCrud() {
		CrudOperate crud = context.getBean("crudAllInOneOperate",
				CrudOperate.class);
		assertNotNull(crud);

		try {
			crud.delete(PlaceBean.class);
			assertEquals(crud.count(PlaceBean.class), 0);

			// TEST INSERT
			PlaceBean p = new PlaceBean();
			p.setCode("place1");
			p.setLeaf(false);
			p.setMemo("memo 1");
			p.setName("name 1");
			p.setRecommended(1001);
			p.setRoot(true);
			p.setRowKey("rowkey 1");
			p.setShorter("shorter 1");
			p.setStatus(Status.ACTIVED);
			assertNull(p.getGraphId());
			crud.save(p, "john");
			assertEquals(crud.count(PlaceBean.class), 1);
			PlaceBean p1 = (PlaceBean) crud
					.get(PlaceBean.class, p.getGraphId());
			assertNotNull(p1);
			assertNotNull(p1.getGraphId());
			PlaceBean p2 = (PlaceBean) crud.get(PlaceBean.class, "code",
					p.getCode());
			assertNotNull(p2);
			assertNotNull(p2.getGraphId());
			assertEquals(p2.getCode(), p.getCode());
			assertEquals(p2.getCreatedTime(), p.getCreatedTime());
			assertTrue(p2.getCreatedTime() > 0);
			assertEquals(p2.getCreatedUserId(), p.getCreatedUserId());
			assertEquals(p2.getCreatedUserId(), "john");
			assertEquals(p2.getMemo(), p.getMemo());
			assertEquals(p2.getName(), p.getName());
			assertEquals(p2.getRecommended(), p.getRecommended());
			assertEquals(p2.getRowKey(), p.getRowKey());
			assertEquals(p2.getShorter(), p.getShorter());
			assertEquals(p2.getStatus(), p.getStatus());
			assertEquals(p2.getUpdatedTime(), p.getUpdatedTime());
			assertFalse(p2.getUpdatedTime() > 0);
			assertEquals(p2.getUpdatedUserId(), p.getUpdatedUserId());
			assertNull(p2.getParent());

			// TEST GRAPHID = NULL AND SAME CODE
			p2.setGraphId(null);
			crud.save(p2, "john");
			assertEquals(crud.count(PlaceBean.class), 1);
			PlaceBean p2_1 = (PlaceBean) crud.get(PlaceBean.class,
					p2.getGraphId());
			assertNotNull(p2_1);
			PlaceBean p2_11 = (PlaceBean) crud.get(PlaceBean.class, "code",
					p2.getCode());
			assertNotNull(p2_11);
			assertEquals(p2_1.getGraphId(), p2_11.getGraphId());
			assertEquals(p2_1.getCode(), p2_11.getCode());
			assertEquals(p2_11.getGraphId(), p.getGraphId());
			assertEquals(p2_11.getRowKey(), p.getRowKey());
			assertEquals(p2_11.getCode(), p.getCode());
			assertEquals(p2_11.getRecommended(), p.getRecommended());

			// TEST GRAPHID = NULL AND NOT SAME ROWKEY
			p2_1.setGraphId(null);
			p2_1.setCode(p2_1.getCode() + " modified");
			crud.save(p2_1, "john");
			assertEquals(crud.count(PlaceBean.class), 2);
			PlaceBean p2_2 = (PlaceBean) crud.get(PlaceBean.class,
					p2_1.getGraphId());
			assertEquals(p2_2.getGraphId(), p2_1.getGraphId());
			assertEquals(p2_2.getRowKey(), p2_1.getRowKey());
			assertEquals(p2_2.getCode(), p2_1.getCode());
			PlaceBean p2_22 = (PlaceBean) crud.get(PlaceBean.class, "code",
					p2_1.getCode());
			assertNotNull(p2_22);
			assertEquals(p2_2.getGraphId(), p2_22.getGraphId());
			assertFalse(p2_2.getGraphId().longValue() == p.getGraphId()
					.longValue());

			// TEST UPDATE
			p2.setName(p2.getName() + " modified.");
			p2.setMemo(p2.getMemo() + " modified");
			p2.setRecommended(9999);
			crud.save(p2, "john peng");
			assertEquals(crud.count(PlaceBean.class), 2);
			PlaceBean p3 = (PlaceBean) crud.get(PlaceBean.class,
					p2.getGraphId());
			assertNotNull(p3);
			assertNotNull(p2.getGraphId());
			assertEquals(p2.getCode(), p3.getCode());
			assertEquals(p2.getCreatedTime(), p3.getCreatedTime());
			assertTrue(p3.getCreatedTime() > 0);
			assertEquals(p2.getCreatedUserId(), p3.getCreatedUserId());
			assertEquals(p3.getCreatedUserId(), "john");
			assertEquals(p2.getMemo(), p3.getMemo());
			assertEquals(p2.getName(), p3.getName());
			assertEquals(p2.getRecommended(), p3.getRecommended());
			assertEquals(p2.getRowKey(), p3.getRowKey());
			assertEquals(p2.getShorter(), p3.getShorter());
			assertEquals(p2.getStatus(), p3.getStatus());
			assertEquals(p2.getUpdatedTime(), p3.getUpdatedTime());
			assertTrue(p3.getUpdatedTime() > 0);
			assertEquals(p2.getUpdatedUserId(), p3.getUpdatedUserId());
			assertNull(p3.getParent());

			crud.delete(PlaceBean.class);
			assertEquals(crud.count(PlaceBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationPlace() {
		PlaceRepository repo = context.getBean(PlaceRepository.class);
		assertNotNull(repo);

		try {
			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
			
			PlaceBean asia = new PlaceBean();
			asia.setCode("asia");
			asia.setName("亚洲");
			repo.save(asia, "john");
			assertEquals(repo.count(PlaceBean.class), 1);
			PlaceBean china = new PlaceBean();
			china.setCode("china");
			china.setName("中国");
			repo.save(china, "john");
			assertEquals(repo.count(PlaceBean.class), 2);
			PlaceBean china1 = (PlaceBean)repo.get(PlaceBean.class, china.getGraphId());
			assertNotNull(china1);
			assertNull(china1.getParent());
			
			// TEST SAVE RELATIONS ONLY
			china.setParent(asia);
			repo.saveRelationsOnly(china);
			assertEquals(repo.count(PlaceBean.class), 2);
			PlaceBean china2 = (PlaceBean)repo.get(PlaceBean.class, china.getGraphId());
			assertNotNull(china2);
			assertNotNull(china2.getParent());
			assertEquals(china2.getParent().getGraphId(), asia.getGraphId());
			assertEquals(china2.getParent().getCode(), asia.getCode());
			
			// TEST SAVE ENTITY AND RELATIONS
			PlaceBean taiwan = new PlaceBean();
			taiwan.setCode("taiwan");
			taiwan.setName("台湾");
			taiwan.setParent(asia);
			repo.save(taiwan, "john");
			assertEquals(repo.count(PlaceBean.class), 3);
			PlaceBean taiwan1 = (PlaceBean)repo.get(PlaceBean.class, taiwan.getGraphId());
			assertNotNull(taiwan1);
			assertNotNull(taiwan1.getParent());
			assertEquals(taiwan1.getParent().getGraphId(), asia.getGraphId());
			assertEquals(taiwan1.getParent().getCode(), asia.getCode());
			
			PlaceBean zhejiang = new PlaceBean();
			zhejiang.setCode("zhejiang");
			zhejiang.setName("浙江");
			zhejiang.setParent(china);
			repo.save(zhejiang, "john");
			assertEquals(repo.count(PlaceBean.class), 4);
			PlaceBean zhejiang1 = (PlaceBean)repo.get(PlaceBean.class, zhejiang.getGraphId());
			assertNotNull(zhejiang1);
			assertNotNull(zhejiang1.getParent());
			assertEquals(zhejiang1.getParent().getCode(), china.getCode());
			
			PlaceBean anhui = new PlaceBean();
			anhui.setCode("anhui");
			anhui.setName("安徽");
			anhui.setParent(china);
			repo.save(anhui, "john");
			assertEquals(repo.count(PlaceBean.class), 5);
			PlaceBean anhui1 = (PlaceBean)repo.get(PlaceBean.class, anhui.getGraphId());
			assertNotNull(anhui1);
			assertNotNull(anhui1.getParent());
			assertEquals(anhui1.getParent().getCode(), china.getCode());

			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
