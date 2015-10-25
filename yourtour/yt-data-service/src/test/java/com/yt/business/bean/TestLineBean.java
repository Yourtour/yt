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
import com.yt.business.repository.LineRepository;
import com.yt.neo4j.repository.CrudOperate;

public class TestLineBean {
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
			crud.delete(LineBean.class);
			assertEquals(crud.count(LineBean.class), 0);

			// TEST INSERT
			LineBean line = new LineBean();
			line.setArriveNum(99);
			line.setCode("line1");
			line.setCommentIndex(9.8);
			line.setCommentNum(100);
			line.setFavoriteNum(1001);
			line.setFeature("feature 1");
			line.setImageUrl("image url 1");
			line.setIntro("introduction 1");
			line.setName("name 1");
			line.setReason("reson 1");
			line.setRecommendIndex(9.9);
			line.setRowKey("rowkey 1");
			line.setShareNum(101);
			line.setStatus(Status.ACTIVED);
			line.setTags("tag 1");
			line.setThumbupNum(99);
			assertNull(line.getGraphId());
			crud.save(line, "john");
			assertEquals(crud.count(LineBean.class), 1);
			LineBean l1 = (LineBean) crud
					.get(LineBean.class, line.getGraphId());
			assertNotNull(l1);
			assertNotNull(l1.getGraphId());
			LineBean l2 = (LineBean) crud.get(LineBean.class, "code",
					line.getCode());
			assertNotNull(l2);
			assertNotNull(l2.getGraphId());
			assertEquals(line.getArriveNum(), l2.getArriveNum());
			assertEquals(line.getCode(), l2.getCode());
			assertEquals(line.getCommentIndex(), l2.getCommentIndex(),
					0.0000001);
			assertEquals(line.getCommentNum(), l2.getCommentNum());
			assertEquals(line.getCommentScore(), l2.getCommentScore(), 0.000001);
			assertEquals(line.getCreatedTime(), l2.getCreatedTime());
			assertTrue(l2.getCreatedTime() > 0);
			assertEquals(line.getCreatedUserId(), l2.getCreatedUserId());
			assertEquals(l2.getCreatedUserId(), "john");
			assertEquals(line.getFavoriteNum(), l2.getFavoriteNum());
			assertEquals(line.getFeature(), l2.getFeature());
			assertEquals(line.getGraphId(), l2.getGraphId());
			assertEquals(line.getImageUrl(), l2.getImageUrl());
			assertEquals(line.getIntro(), l2.getIntro());
			assertEquals(line.getName(), l2.getName());
			assertEquals(line.getReason(), l2.getReason());
			assertEquals(line.getRecommendIndex(), l2.getRecommendIndex(),
					0.0000001);
			assertEquals(line.getRowKey(), l2.getRowKey());
			assertEquals(line.getShareNum(), l2.getShareNum());
			assertEquals(line.getStatus(), l2.getStatus());
			assertEquals(line.getTags(), l2.getTags());
			assertEquals(line.getThumbupNum(), l2.getThumbupNum());
			assertEquals(line.getUpdatedTime(), l2.getUpdatedTime());
			assertFalse(l2.getUpdatedTime() > 0);
			assertEquals(line.getUpdatedUserId(), l2.getUpdatedUserId());
			assertNull(l2.getPlace());
			assertEquals(l2.getHotels().size(), 0);
			assertEquals(l2.getRestaurants().size(), 0);
			assertEquals(l2.getScenes().size(), 0);

			// TEST GRAPHID = NULL AND SAME CODE
			l2.setGraphId(null);
			crud.save(l2, "john");
			assertEquals(crud.count(LineBean.class), 1);
			LineBean l2_1 = (LineBean) crud
					.get(LineBean.class, l2.getGraphId());
			assertNotNull(l2_1);
			LineBean l2_11 = (LineBean) crud.get(LineBean.class, "code",
					l2.getCode());
			assertNotNull(l2_11);
			assertEquals(l2_1.getGraphId(), l2_11.getGraphId());
			assertEquals(l2_1.getCode(), l2_11.getCode());
			assertEquals(l2_11.getGraphId(), line.getGraphId());
			assertEquals(l2_11.getRowKey(), line.getRowKey());
			assertEquals(l2_11.getCode(), line.getCode());
			assertEquals(l2_11.getIntro(), line.getIntro());

			// TEST GRAPHID = NULL AND NOT SAME ROWKEY
			l2_1.setGraphId(null);
			l2_1.setCode(l2_1.getCode() + " modified");
			crud.save(l2_1, "john");
			assertEquals(crud.count(LineBean.class), 2);
			LineBean l2_2 = (LineBean) crud.get(LineBean.class,
					l2_1.getGraphId());
			assertEquals(l2_2.getGraphId(), l2_1.getGraphId());
			assertEquals(l2_2.getRowKey(), l2_1.getRowKey());
			assertEquals(l2_2.getCode(), l2_1.getCode());
			assertEquals(l2_2.getIntro(), l2_1.getIntro());
			LineBean l2_22 = (LineBean) crud.get(LineBean.class, "code",
					l2_1.getCode());
			assertNotNull(l2_22);
			assertEquals(l2_2.getGraphId(), l2_22.getGraphId());
			assertFalse(l2_2.getGraphId().longValue() == line.getGraphId()
					.longValue());

			// TEST UPDATE
			l2.setName(l2.getName() + " modified.");
			l2.setIntro(l2.getIntro() + " modified.");
			l2.setReason(l2.getReason() + " modified.");
			l2.setFavoriteNum(999);
			crud.save(l2, "john peng");
			assertEquals(crud.count(LineBean.class), 2);
			LineBean l3 = (LineBean) crud.get(LineBean.class, l2.getGraphId());
			assertNotNull(l3);
			assertEquals(l3.getArriveNum(), l2.getArriveNum());
			assertEquals(l3.getCode(), l2.getCode());
			assertEquals(l3.getCommentIndex(), l2.getCommentIndex(), 0.0000001);
			assertEquals(l3.getCommentNum(), l2.getCommentNum());
			assertEquals(l3.getCommentScore(), l2.getCommentScore(), 0.000001);
			assertEquals(l3.getCreatedTime(), l2.getCreatedTime());
			assertTrue(l2.getCreatedTime() > 0);
			assertEquals(l3.getCreatedUserId(), l2.getCreatedUserId());
			assertEquals(l3.getCreatedUserId(), "john");
			assertEquals(l3.getFavoriteNum(), l2.getFavoriteNum());
			assertEquals(l3.getFeature(), l2.getFeature());
			assertEquals(l3.getGraphId(), l2.getGraphId());
			assertEquals(l3.getImageUrl(), l2.getImageUrl());
			assertEquals(l3.getIntro(), l2.getIntro());
			assertEquals(l3.getName(), l2.getName());
			assertEquals(l3.getReason(), l2.getReason());
			assertEquals(l3.getRecommendIndex(), l2.getRecommendIndex(),
					0.0000001);
			assertEquals(l3.getRowKey(), l2.getRowKey());
			assertEquals(l3.getShareNum(), l2.getShareNum());
			assertEquals(l3.getStatus(), l2.getStatus());
			assertEquals(l3.getTags(), l2.getTags());
			assertEquals(l3.getThumbupNum(), l2.getThumbupNum());
			assertEquals(l3.getUpdatedTime(), l2.getUpdatedTime());
			assertTrue(l3.getUpdatedTime() > 0);
			assertEquals(l3.getUpdatedUserId(), l2.getUpdatedUserId());
			assertEquals(l3.getUpdatedUserId(), "john peng");
			assertNull(l3.getPlace());
			assertEquals(l3.getHotels().size(), 0);
			assertEquals(l3.getRestaurants().size(), 0);
			assertEquals(l3.getScenes().size(), 0);

			crud.delete(LineBean.class);
			assertEquals(crud.count(LineBean.class), 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationPlace() {
		LineRepository repo = context.getBean(LineRepository.class);
		assertNotNull(repo);

		try {
			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);

			LineBean l1 = new LineBean();
			l1.setCode("line1");
			repo.save(l1, "john");
			assertEquals(repo.count(LineBean.class), 1);
			PlaceBean p1 = new PlaceBean();
			p1.setCode("place1");
			repo.save(p1, "john");
			LineBean l1_1 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_1);
			assertNull(l1_1.getPlace());

			assertEquals(repo.count(PlaceBean.class), 1);
			l1.setPlace(p1);
			repo.saveRelationsOnly(l1);
			assertEquals(repo.count(PlaceBean.class), 1);
			assertEquals(repo.count(LineBean.class), 1);
			LineBean l1_2 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_2);
			assertNotNull(l1_2.getPlace());
			assertEquals(l1_2.getPlace().getCode(), p1.getCode());

			PlaceBean p2 = new PlaceBean();
			p2.setCode("place2");
			repo.save(p2, "john");
			assertEquals(repo.count(PlaceBean.class), 2);
			l1.setPlace(p2);
			repo.saveRelationsOnly(l1);
			assertEquals(repo.count(LineBean.class), 1);
			LineBean l1_3 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_3);
			assertNotNull(l1_3.getPlace());
			assertEquals(l1_3.getPlace().getCode(), p2.getCode());
			assertFalse(l1_3.getPlace().getCode().equals(p1.getCode()));

			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(PlaceBean.class);
			assertEquals(repo.count(PlaceBean.class), 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationScene() {
		LineRepository repo = context.getBean(LineRepository.class);
		assertNotNull(repo);

		try {
			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(SceneResourceBean.class);
			assertEquals(repo.count(SceneResourceBean.class), 0);

			SceneResourceBean s1 = new SceneResourceBean();
			s1.setCode("scene1");
			repo.save(s1, "john");
			SceneResourceBean s2 = new SceneResourceBean();
			s2.setCode("scene2");
			repo.save(s2, "john");
			SceneResourceBean s3 = new SceneResourceBean();
			s3.setCode("scene3");
			repo.save(s3, "john");
			SceneResourceBean s4 = new SceneResourceBean();
			s4.setCode("scene4");
			repo.save(s4, "john");
			SceneResourceBean s5 = new SceneResourceBean();
			s5.setCode("scene5");
			repo.save(s5, "john");
			assertEquals(repo.count(SceneResourceBean.class), 5);

			LineBean l1 = new LineBean();
			l1.setCode("line1");
			l1.getScenes().add(s1);
			l1.getScenes().add(s2);
			l1.getScenes().add(s3);
			repo.save(l1, "john");
			assertEquals(repo.count(LineBean.class), 1);
			assertEquals(repo.count(SceneResourceBean.class), 5);
			LineBean l1_1 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_1);
			assertEquals(l1_1.getScenes().size(), 3);
			assertEquals(l1_1.getScenes().get(0).getCode(), s1.getCode());
			assertEquals(l1_1.getScenes().get(1).getCode(), s2.getCode());
			assertEquals(l1_1.getScenes().get(2).getCode(), s3.getCode());

			l1.getScenes().remove(1);
			l1.getScenes().remove(0);
			l1.getScenes().add(s4);
			repo.saveRelationsOnly(l1);
			LineBean l1_2 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_2);
			assertEquals(l1_2.getScenes().size(), 2);
			assertEquals(l1_2.getScenes().get(0).getCode(), s3.getCode());
			assertEquals(l1_2.getScenes().get(1).getCode(), s4.getCode());

			LineBean l2 = new LineBean();
			l2.setCode("line2");
			l2.getScenes().add(s1);
			l2.getScenes().add(s2);
			l2.getScenes().add(s5);
			repo.save(l2, "john");
			assertEquals(repo.count(LineBean.class), 2);
			LineBean l2_1 = (LineBean) repo
					.get(LineBean.class, l2.getGraphId());
			assertNotNull(l2_1);
			assertEquals(l2_1.getScenes().size(), 3);
			assertEquals(l2_1.getScenes().get(0).getCode(), s1.getCode());
			assertEquals(l2_1.getScenes().get(1).getCode(), s2.getCode());
			assertEquals(l2_1.getScenes().get(2).getCode(), s5.getCode());

			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(SceneResourceBean.class);
			assertEquals(repo.count(SceneResourceBean.class), 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationHotel() {
		LineRepository repo = context.getBean(LineRepository.class);
		assertNotNull(repo);

		try {
			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(HotelResourceBean.class);
			assertEquals(repo.count(HotelResourceBean.class), 0);

			HotelResourceBean h1 = new HotelResourceBean();
			h1.setCode("hotel1");
			repo.save(h1, "john");
			HotelResourceBean h2 = new HotelResourceBean();
			h2.setCode("hotel2");
			repo.save(h2, "john");
			HotelResourceBean h3 = new HotelResourceBean();
			h3.setCode("hotel3");
			repo.save(h3, "john");
			HotelResourceBean h4 = new HotelResourceBean();
			h4.setCode("hotel4");
			repo.save(h4, "john");
			HotelResourceBean h5 = new HotelResourceBean();
			h5.setCode("hotel5");
			repo.save(h5, "john");
			assertEquals(repo.count(HotelResourceBean.class), 5);

			LineBean l1 = new LineBean();
			l1.setCode("line1");
			l1.getHotels().add(h1);
			l1.getHotels().add(h2);
			l1.getHotels().add(h3);
			repo.save(l1, "john");
			assertEquals(repo.count(LineBean.class), 1);
			assertEquals(repo.count(HotelResourceBean.class), 5);
			LineBean l1_1 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_1);
			assertEquals(l1_1.getHotels().size(), 3);
			assertEquals(l1_1.getHotels().get(0).getCode(), h1.getCode());
			assertEquals(l1_1.getHotels().get(1).getCode(), h2.getCode());
			assertEquals(l1_1.getHotels().get(2).getCode(), h3.getCode());

			l1.getHotels().remove(1);
			l1.getHotels().remove(0);
			l1.getHotels().add(h4);
			repo.saveRelationsOnly(l1, new String[] {"hotels"});
			LineBean l1_2 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_2);
			assertEquals(l1_2.getHotels().size(), 2);
			assertEquals(l1_2.getHotels().get(0).getCode(), h3.getCode());
			assertEquals(l1_2.getHotels().get(1).getCode(), h4.getCode());

			LineBean l2 = new LineBean();
			l2.setCode("line2");
			l2.getHotels().add(h1);
			l2.getHotels().add(h2);
			l2.getHotels().add(h5);
			repo.save(l2, "john");
			assertEquals(repo.count(LineBean.class), 2);
			LineBean l2_1 = (LineBean) repo
					.get(LineBean.class, l2.getGraphId());
			assertNotNull(l2_1);
			assertEquals(l2_1.getHotels().size(), 3);
			assertEquals(l2_1.getHotels().get(0).getCode(), h1.getCode());
			assertEquals(l2_1.getHotels().get(1).getCode(), h2.getCode());
			assertEquals(l2_1.getHotels().get(2).getCode(), h5.getCode());

			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(HotelResourceBean.class);
			assertEquals(repo.count(HotelResourceBean.class), 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationRestaurant() {
		LineRepository repo = context.getBean(LineRepository.class);
		assertNotNull(repo);

		try {
			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(RestaurantResourceBean.class);
			assertEquals(repo.count(RestaurantResourceBean.class), 0);

			RestaurantResourceBean r1 = new RestaurantResourceBean();
			r1.setCode("restaurant1");
			repo.save(r1, "john");
			RestaurantResourceBean r2 = new RestaurantResourceBean();
			r2.setCode("restaurant2");
			repo.save(r2, "john");
			RestaurantResourceBean r3 = new RestaurantResourceBean();
			r3.setCode("restaurant3");
			repo.save(r3, "john");
			RestaurantResourceBean r4 = new RestaurantResourceBean();
			r4.setCode("restaurant4");
			repo.save(r4, "john");
			RestaurantResourceBean r5 = new RestaurantResourceBean();
			r5.setCode("restaurant5");
			repo.save(r5, "john");
			assertEquals(repo.count(RestaurantResourceBean.class), 5);

			LineBean l1 = new LineBean();
			l1.setCode("line1");
			l1.getRestaurants().add(r1);
			l1.getRestaurants().add(r2);
			l1.getRestaurants().add(r3);
			repo.save(l1, "john");
			assertEquals(repo.count(LineBean.class), 1);
			assertEquals(repo.count(RestaurantResourceBean.class), 5);
			LineBean l1_1 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_1);
			assertEquals(l1_1.getRestaurants().size(), 3);
			assertEquals(l1_1.getRestaurants().get(0).getCode(), r1.getCode());
			assertEquals(l1_1.getRestaurants().get(1).getCode(), r2.getCode());
			assertEquals(l1_1.getRestaurants().get(2).getCode(), r3.getCode());

			l1.getRestaurants().remove(1);
			l1.getRestaurants().remove(0);
			l1.getRestaurants().add(r4);
			repo.saveRelationsOnly(l1);
			LineBean l1_2 = (LineBean) repo
					.get(LineBean.class, l1.getGraphId());
			assertNotNull(l1_2);
			assertEquals(l1_2.getRestaurants().size(), 2);
			assertEquals(l1_2.getRestaurants().get(0).getCode(), r3.getCode());
			assertEquals(l1_2.getRestaurants().get(1).getCode(), r4.getCode());

			LineBean l2 = new LineBean();
			l2.setCode("line2");
			l2.getRestaurants().add(r1);
			l2.getRestaurants().add(r2);
			l2.getRestaurants().add(r5);
			repo.save(l2, "john");
			assertEquals(repo.count(LineBean.class), 2);
			LineBean l2_1 = (LineBean) repo
					.get(LineBean.class, l2.getGraphId());
			assertNotNull(l2_1);
			assertEquals(l2_1.getRestaurants().size(), 3);
			assertEquals(l2_1.getRestaurants().get(0).getCode(), r1.getCode());
			assertEquals(l2_1.getRestaurants().get(1).getCode(), r2.getCode());
			assertEquals(l2_1.getRestaurants().get(2).getCode(), r5.getCode());

			repo.delete(LineBean.class);
			assertEquals(repo.count(LineBean.class), 0);
			repo.delete(RestaurantResourceBean.class);
			assertEquals(repo.count(RestaurantResourceBean.class), 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
