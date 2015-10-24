package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.RestaurantResourceBean;

public interface RestaurantResourceBeanRepository extends
		GraphRepository<RestaurantResourceBean> {
	/**
	 * 根据指定的目的地ID，获取该目的地下的所有饭店
	 * 
	 * @param placeId
	 *            目的地ID
	 * @return 已经关联了目的地信息的饭店列表
	 */
	@Query("START place=node({0}) MATCH place<-[:AT]-(restaurant:RestaurantResourceBean) RETURN restaurant, place")
	public List<RestaurantResourcePlaceTuple> getRestaurantByPlace(Long placeId);
}
