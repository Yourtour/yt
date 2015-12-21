package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;

public interface RouteBeanRepository extends GraphRepository<RouteMainBean> {
	/**
	 * 根据指定的用户，返回该用户拥有的行程
	 * 
	 * @param userId
	 *            用户Id
	 * @return 指定用户拥有的行程列表
	 */
	@Query("START owner=node({0}) MATCH owner<-[:HAS]-(route:RouteMainBean) RETURN route")
	public List<RouteMainBean> getRoutesByOwner(Long userId);

	/**
	 * 根据指定的行程和成员角色，返回该行程中的用户。
	 * 
	 * @param routeId
	 *            行程ID
	 * @param groupRole
	 *            成员角色
	 * @return 用户列表
	 */
	@Query("START route=node({0}) MATCH route -- (user:UserProfileBean) RETURN user")
	public List<UserProfileBean> getRouteMember(Long routeId);
}
