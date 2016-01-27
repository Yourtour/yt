package com.yt.business.neo4j.repository;

import com.yt.business.bean.RouteChargeBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface RouteChargeBeanRepository extends GraphRepository<RouteChargeBean> {
	/**
	 *
	 * @param routeId
	 * @param userId
	 * @return
	 */
	@Query("START route=node({0}), user=node({1}) MATCH route<-[:BELONG]-(charge:RouteChargeBean)-[:BELONG]->user RETURN charge")
	public List<RouteChargeBean> getCharges(Long routeId, Long userId);
}
