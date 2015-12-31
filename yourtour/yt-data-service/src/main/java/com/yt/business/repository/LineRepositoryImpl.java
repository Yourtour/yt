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
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.neo4j.repository.HotelResourceBeanRepository;
import com.yt.business.neo4j.repository.HotelResourcePlaceTuple;
import com.yt.business.neo4j.repository.LineBeanRepository;
import com.yt.business.neo4j.repository.RestaurantResourceBeanRepository;
import com.yt.business.neo4j.repository.RestaurantResourcePlaceTuple;
import com.yt.business.neo4j.repository.RouteBeanRepository;
import com.yt.business.neo4j.repository.SceneResourceBeanRepository;
import com.yt.business.neo4j.repository.SceneResourcePlaceTuple;
import com.yt.neo4j.repository.FullTextSearchOperate;

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
	 * @see com.yt.business.repository.LineRepository#getLinesByPlace(long)
	 */
	@Override
	public List<LineBean> getLinesByPlace(long[] placeIds) throws Exception {
		return (List<LineBean>) this.get(LineBean.class);
	}
}