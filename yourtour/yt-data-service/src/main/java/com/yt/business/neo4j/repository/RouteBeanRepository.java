package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.RouteMainBean;

public interface RouteBeanRepository extends GraphRepository<RouteMainBean> {
	/**
	 * 根据指定的用户，返回该用户拥有的行程
	 * 
	 * @param userId
	 *            用户Id
	 * @return 指定用户拥有的行程列表
	 */
	@Query("START owner=node({0}) MATCH owner<-[:HAS]-(route:RouteMainBean) return route")
	public List<RouteMainBean> getRoutesByOwner(Long userId);
}
