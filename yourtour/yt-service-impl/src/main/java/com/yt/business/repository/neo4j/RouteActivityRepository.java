package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.RouteActivityBean;

public interface RouteActivityRepository extends GraphRepository<RouteActivityBean> {
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@Query("START activity=node({0}) MATCH activity-[:RELATED]->(resource:ResourceBean) RETURN activity, resource")
	public RouteActivityTuple getRouteActivity(Long activityId);
}
