package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.RestaurantResourceBean;

public interface RestaurantResourceBeanRepository extends
		GraphRepository<RestaurantResourceBean> {
	/**
	 * 根据GraphId获取指定的饭店，并关联查询对应的目的地
	 * 
	 * @param graphId
	 *            图ID
	 * @return 已经关联了目的地信息的饭店
	 */
	@Query("START restaurant=node({0}) MATCH restaurant-[:AT]->place RETURN restaurant, place")
	public RestaurantResourcePlaceTuple getRestaurantByGraphId(Long graphId);

	/**
	 * 根据指定的目的地ID，获取该目的地下的所有饭店
	 * 
	 * @param placeId
	 *            目的地ID
	 * @return 已经关联了目的地信息的饭店列表
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(restaurant:RestaurantResourceBean) RETURN restaurant, place")
	public List<RestaurantResourcePlaceTuple> getRestaurantByPlace(Long placeId);

	/**
	 * 根据分页要求查询所有的饭店
	 * 
	 * @param skip
	 *            分页起始记录数
	 * @param limit
	 *            本页最大记录数
	 * @return 该页的饭店
	 */
	@Query("MATCH (restaurant:RestaurantResourceBean)-[:AT]->place RETURN restaurant, place SKIP {0} LIMIT {1}")
	public List<RestaurantResourcePlaceTuple> getRestaurantsByPage(long skip,
			long limit);
}
