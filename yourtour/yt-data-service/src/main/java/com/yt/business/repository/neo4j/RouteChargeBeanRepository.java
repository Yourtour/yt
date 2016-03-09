package com.yt.business.repository.neo4j;

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

	/**
	 *
	 * @param chargeId
	 * @return
	 */
	@Query("START root=node({0}) MATCH root-[:DIVIDED]->(charge:RouteChargeBean)-[:BELONG]->(owner:UserProfileBean) RETURN charge, owner")
	public List<ChargeTuple> getChargeDivisions(Long chargeId);
}
