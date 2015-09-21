package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.RestaurantResourceBeanRepository;
import com.yt.business.neo4j.repository.RestaurantResourcePlaceTuple;
import com.yt.business.utils.Neo4jUtils;
import com.yt.rsal.neo4j.bean.INeo4JBaseBean;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;

@Component
public class RestaurantRepositoryImpl extends CrudGeneralOperate implements
		RestaurantRepository {
	private static final Log LOG = LogFactory
			.getLog(RestaurantRepositoryImpl.class);

	@Autowired
	private RestaurantResourceBeanRepository restaurantRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RestaurantRepository#getRestaurantByGraphId
	 * (java.lang.Long)
	 */
	@Override
	public RestaurantResourceBean getRestaurantByGraphId(Long graphId)
			throws Exception {
		RestaurantResourcePlaceTuple tuple = restaurantRepo
				.getRestaurantByGraphId(graphId);
		RestaurantResourceBean restaurant = null;
		if (tuple != null) {
			restaurant = tuple.getRestaurant();
			restaurant.setPlace(tuple.getPlace());
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Get restaurant resource by graph ID success, the resource %s.",
							restaurant != null ? "Found" : "Not Existed"));
		}
		return restaurant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RestaurantRepository#getRestaurantsByPage(int,
	 * int)
	 */
	@Override
	public List<RestaurantResourceBean> getRestaurantsByPage(int start,
			int limit) throws Exception {
		List<RestaurantResourcePlaceTuple> tuples = restaurantRepo
				.getRestaurantsByPage(start, limit);
		List<RestaurantResourceBean> list = new Vector<RestaurantResourceBean>(
				tuples.size());
		for (RestaurantResourcePlaceTuple tuple : tuples) {
			RestaurantResourceBean restaurant = tuple.getRestaurant();
			restaurant.setPlace(tuple.getPlace());
			list.add(restaurant);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Get restaurant resource success, request: start(%d), limit(%d); total: %d.",
							start, limit, list.size()));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.CrudGeneralOperate#save(com.yt.rsal.neo4j
	 * .bean.INeo4JBaseBean, java.lang.String, boolean)
	 */
	@Override
	public INeo4JBaseBean save(INeo4JBaseBean neo4jBean, String operator,
			boolean saveFail2Hbase) throws Exception {
		RestaurantResourceBean bean = (RestaurantResourceBean) super.save(
				neo4jBean, operator, saveFail2Hbase);
		RestaurantResourceBean hotel = (RestaurantResourceBean) neo4jBean;
		// 建立饭店和目的地的关联关系
		if (hotel.getPlace() != null && hotel.getPlace().getGraphId() != null) {
			// 建立饭店到目的地的关系
			PlaceBean place = super.template.findOne(hotel.getPlace()
					.getGraphId(), PlaceBean.class);
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.AT, bean, place, null, true, false);
		}
		return bean;
	}
}
