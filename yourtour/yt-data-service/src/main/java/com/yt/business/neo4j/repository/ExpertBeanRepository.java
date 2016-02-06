package com.yt.business.neo4j.repository;

import com.yt.business.bean.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ExpertBeanRepository extends GraphRepository<ExpertServiceBean> {
	@Query("START N=NODE({0}) MATCH (profile:UserProfileBean)-[:IS]->expert return expert, profile")
	public ExpertTuple getExpert(Long expertId);

	@Query("MATCH (expert:ExpertBean)<-[:IS]-(profile:UserProfileBean) return expert, profile")
	public List<ExpertTuple> getExperts(Long[] places, Integer duration);

	@Query("START routeService=node({0}) MATCH routeService-[:RELATED]->(expertService:ExpertServiceBean) RETURN expertService")
	public List<ExpertServiceBean> getServices(Long[] routeServiceIds);

	@Query("START user=node({0}) MATCH user-[:HAS]->(application:ExpertApplicationBean) RETURN application")
	public ExpertApplicationBean getApplication(Long userId);

	/**
	 * 获取达人提供的服务
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:HAS]->(services:ExpertServiceBean) RETURN services")
	public List<ExpertServiceBean> getServices(Long expertId);

	/**
	 * 获取达人参与的行程
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[p:PARTICIPATE]->(routes:RouteMainBean) where p.role='EXPERT' RETURN routes")
	public List<RouteMainBean> getParticipatedRoutes(Long expertId, int startIndex, int limits);

	/**
	 * 获取达人推荐行程
	 * @param expertId
	 * @return
	 */
	@Query("START expert=node({0}) MATCH expert-[:RECOMMEND]->(routes:RouteMainBean) RETURN routes")
	public List<RouteMainBean> getRecommendRoutes(Long expertId);
}
