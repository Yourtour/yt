package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.HotelResourceBeanRepository;
import com.yt.business.neo4j.repository.HotelResourcePlaceTuple;
import com.yt.business.neo4j.repository.LineBeanRepository;
import com.yt.business.neo4j.repository.LinePlaceTuple;
import com.yt.business.neo4j.repository.RestaurantResourceBeanRepository;
import com.yt.business.neo4j.repository.RestaurantResourcePlaceTuple;
import com.yt.business.neo4j.repository.RouteBeanRepository;
import com.yt.business.neo4j.repository.SceneResourceBeanRepository;
import com.yt.business.neo4j.repository.SceneResourcePlaceTuple;
import com.yt.business.utils.Neo4jUtils;
import com.yt.neo4j.bean.Neo4jBaseBean;
import com.yt.neo4j.repository.FullTextSearchOperate;
import com.yt.neo4j.repository.FullTextSearchOperate.QueryTerm;

@Component
public class LineRepositoryImpl extends CrudAllInOneOperateImpl implements
		LineRepository {
	private static final Log LOG = LogFactory.getLog(LineRepositoryImpl.class);

	@Autowired
	private SceneResourceBeanRepository sceneRepo;

	@Autowired
	private HotelResourceBeanRepository hotelRepo;

	@Autowired
	private RestaurantResourceBeanRepository restaurantRepo;

	@Autowired
	private FullTextSearchOperate ftsOperate;

	@Autowired
	private RouteBeanRepository routeRepo;

	@Autowired
	private LineBeanRepository lineRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#getLineByGraphId(java.lang.
	 * Long)
	 */
	@Override
	public LineBean getLineByGraphId(Long graphId) throws Exception {
		LinePlaceTuple tuple = lineRepo.getLineByGraphId(graphId);
		return transfromLineBean(tuple);
	}

	private LineBean transfromLineBean(LinePlaceTuple tuple) throws Exception {
		LineBean line = null;
		if (tuple != null) {
			line = tuple.getLine();
			line.setPlace(tuple.getPlace());

			// 根据关系将关联的景点、宾馆、饭店查询出来，并填充到line对象中
			List<SceneResourceBean> scenes = lineRepo.getScenesByLine(line
					.getGraphId());
			if (scenes.size() > 0) {
				line.getScenes().addAll(scenes);
			}
			List<HotelResourceBean> hotels = lineRepo.getHotelsByLine(line
					.getGraphId());
			if (hotels.size() > 0) {
				line.getHotels().addAll(hotels);
			}
			List<RestaurantResourceBean> restaurants = lineRepo
					.getRestaurantsByLine(line.getGraphId());
			if (restaurants.size() > 0) {
				line.getRestaurants().addAll(restaurants);
			}
		}
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#getScenesByPlace(java.lang.
	 * Long)
	 */
	@Override
	public List<SceneResourceBean> getScenesByPlace(Long placeId)
			throws Exception {
		List<SceneResourcePlaceTuple> tuples = sceneRepo
				.getSceneByPlace(placeId);
		List<SceneResourceBean> list = new Vector<SceneResourceBean>();
		for (SceneResourcePlaceTuple tuple : tuples) {
			SceneResourceBean scene = tuple.getScene();
			scene.setPlace(tuple.getPlace());
			list.add(scene);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Get all the scenes at place[%d] success, total: %d.",
					placeId, list.size()));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#getHotelsByPlace(java.lang.
	 * Long)
	 */
	@Override
	public List<HotelResourceBean> getHotelsByPlace(Long placeId)
			throws Exception {
		List<HotelResourcePlaceTuple> tuples = hotelRepo
				.getHotelByPlace(placeId);
		List<HotelResourceBean> list = new Vector<HotelResourceBean>();
		for (HotelResourcePlaceTuple tuple : tuples) {
			HotelResourceBean hotel = tuple.getHotel();
			hotel.setPlace(tuple.getPlace());
			list.add(hotel);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Get all the hotels at place[%d] success, total: %d.",
					placeId, list.size()));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#getRestaurantsByPlace(java.lang
	 * .Long)
	 */
	@Override
	public List<RestaurantResourceBean> getRestaurantsByPlace(Long placeId)
			throws Exception {
		List<RestaurantResourcePlaceTuple> tuples = restaurantRepo
				.getRestaurantByPlace(placeId);
		List<RestaurantResourceBean> list = new Vector<RestaurantResourceBean>();
		for (RestaurantResourcePlaceTuple tuple : tuples) {
			RestaurantResourceBean restaurant = tuple.getRestaurant();
			restaurant.setPlace(tuple.getPlace());
			list.add(restaurant);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Get all the restaurants at place[%d] success, total: %d.",
					placeId, list.size()));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.CrudAllInOneOperateImpl#save(com.yt.neo4j.bean.Neo4jBaseBean
	 * , java.lang.String)
	 */
	@Override
	public void save(Neo4jBaseBean neo4jBean, String operator) throws Exception {
		super.save(neo4jBean, operator);
		LineBean line = (LineBean) neo4jBean;
		if (line.getPlace() != null && line.getPlace().getGraphId() != null) {
			// 建立线路到目的地的关系
			PlaceBean place = super.template.findOne(line.getPlace()
					.getGraphId(), PlaceBean.class);
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.AT, line, place, null, true, false);
		}
		// 删除线路跟景点的所有关系，待后续重建
		lineRepo.deleteLine2SceneRelationship(line.getGraphId());
		for (SceneResourceBean scene : line.getScenes()) {
			// 建立线路到景点的关系
			if (scene == null) {
				continue;
			}
			SceneResourceBean sceneGet = super.template.findOne(
					scene.getGraphId(), SceneResourceBean.class);
			if (sceneGet == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The scene not exist, the relation: Line[%d]-[:CONTAIN]->Scene[%d] can not be created.",
									line.getGraphId(), scene.getGraphId()));
				}
				continue;
			}
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.CONTAIN, line, sceneGet, null, true,
					false);
		}
		// 删除线路跟宾馆的所有关系，待后续重建
		lineRepo.deleteLine2HotelRelationship(line.getGraphId());
		for (HotelResourceBean hotel : line.getHotels()) {
			// 建立线路到宾馆的关系
			if (hotel == null) {
				continue;
			}
			HotelResourceBean hotelGet = super.template.findOne(
					hotel.getGraphId(), HotelResourceBean.class);
			if (hotelGet == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The hotel not exist, the relation: Line[%d]-[:CONTAIN]->Hotel[%d] can not be created.",
									line.getGraphId(), hotel.getGraphId()));
				}
				continue;
			}
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.CONTAIN, line, hotelGet, null, true,
					false);
		}

		// 删除线路跟饭店的所有关系，待后续重建
		lineRepo.deleteLine2RestaurantRelationship(line.getGraphId());
		for (RestaurantResourceBean restaurant : line.getRestaurants()) {
			// 建立线路到饭店的关系
			if (restaurant == null) {
				continue;
			}
			RestaurantResourceBean restaurantGet = super.template.findOne(
					restaurant.getGraphId(), RestaurantResourceBean.class);
			if (restaurantGet == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String
							.format("The hotel not exist, the relation: Line[%d]-[:CONTAIN]->Restaurant[%d] can not be created.",
									line.getGraphId(), restaurant.getGraphId()));
				}
				continue;
			}
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.CONTAIN, line, restaurantGet, null,
					true, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#containScene(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void containScene(String lineId, String sceneId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.CONTAIN;
		Neo4jUtils.maintainRelateion(super.template, this, relationship,
				lineId, LineBean.class, sceneId, SceneResourceBean.class, null,
				true, false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create a relationship: LineBean[%s] =%s=> SceneResourceBean[%s].",
							lineId, relationship.name(), sceneId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#uncontainScene(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public void uncontainScene(String lineId, String sceneId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.CONTAIN;
		Neo4jUtils.maintainRelateion(super.template, this, relationship,
				lineId, LineBean.class, sceneId, SceneResourceBean.class, null,
				false, false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Remove a relationship: LineBean[%s] =%s=> SceneResourceBean[%s].",
							lineId, relationship.name(), sceneId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.repository.LineRepository#getLinesByPage(int, int)
	 */
	@Override
	public List<LineBean> getLinesByPage(int start, int limit) throws Exception {
		List<LinePlaceTuple> result = lineRepo.getLinesByPage(start, limit);
		List<LineBean> list = new Vector<LineBean>(result.size());
		for (LinePlaceTuple tuple : result) {
			if (tuple == null) {
				continue;
			}
			list.add(transfromLineBean(tuple));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#queryRecommandLine(java.lang
	 * .String[], int, java.lang.String[])
	 */
	@Override
	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception {
		// 不支持全文检索的查询
		int min = dayNum - 1, max = dayNum + 1;
		min = min >= 0 ? min : 0;
		min *= 24 * 3600;
		max *= 24 * 3600; // 换算为秒

		List<LineBean> recommendLines = routeRepo.query(places, min, max,
				scenes);
		return recommendLines;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.LineRepository#queryRecommandLine2(java.lang
	 * .String[], int, java.lang.String[])
	 */
	public List<LineBean> queryRecommandLine2(String[] places, int dayNum,
			String[] scenes) throws Exception {
		// 首先对景点进行全文检索
		List<QueryTerm> terms = new Vector<QueryTerm>();
		for (String scene : scenes) {
			terms.add(new QueryTerm("name", scene));
		}
		List<Neo4jBaseBean> sceneBeans = ftsOperate.query(
				SceneResourceBean.class, terms, false);
		long[] ids = new long[sceneBeans.size()];
		for (int index = 0; index < ids.length; index++) {
			ids[index] = sceneBeans.get(index).getGraphId();
		}

		// 支持景点全文检索
		int min = dayNum - 1, max = dayNum + 1;
		min = min >= 0 ? min : 0;
		min *= 24 * 3600;
		max *= 24 * 3600; // 换算为秒

		List<LineBean> recommendLines = routeRepo.query(places, min, max, ids);
		return recommendLines;
	}

	@Override
	public List<LineBean> queryLinesByPlace(long placeId) throws Exception {
		return lineRepo.getLinesByPlace(placeId);
	}
}