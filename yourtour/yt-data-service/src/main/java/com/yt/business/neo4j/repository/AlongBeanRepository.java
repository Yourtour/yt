package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.AlongBean;

public interface AlongBeanRepository extends GraphRepository<AlongBean> {
	/**
	 * 获取结伴详情信息
	 * @param graphId
	 * @param startIndex
	 * @param size
	 * @return
	 */
	@Query("START along=node({0}) RETURN n")
	public AlongBean getAlongByGraphId(Long graphId);
	
	/**
	 * 根据目的地翻页查询结伴信息,查询结果根据结伴的INDEX属性进行排序
	 * @param placeId
	 * @param startIndex
	 * @param size
	 * @return
	 */
	@Query("START place=node({0}) MATCH (place:PlaceBean)<-[:TO]-(route:RouteMainBean)<-[:BELONG]-(along:AlongBean)-[:BELONG]->(user:UserProfileBean) return along, route, user")
	public List<AlongTuple> getAlongByPlace(Long placeId, Long startIndex, int size);

	/**
	 *
	 * @param routeId
	 * @return
	 */
	@Query("START route=node({0}) MATCH route<-[:BELONG]-(along:AlongBean)-[:BELONG]->(user:UserProfileBean) return along, route, user")
	public List<AlongTuple> getAlongByRoute(Long routeId);
}
