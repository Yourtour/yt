package com.yt.business.neo4j.repository;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteMainBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ExpertServiceRepository extends GraphRepository<ExpertServiceBean> {
	@Query("START expert=node({0}) MATCH (services:ExpertServiceBean)-[:BELONG]->expert RETURN services")
	public List<ExpertServiceBean> getServices(Long expertId);

	@Query("START routeService=node({0}) MATCH routeService-[:RELATED]->(expertService:ExpertServiceBean) RETURN expertService")
	public List<ExpertServiceBean> getServices(Long[] routeServiceIds);

	@Query("START user=node({0}) MATCH user-[:HAS]->(application:ExpertApplicationBean) RETURN application")
	public ExpertApplicationBean getApplication(Long userId);

	@Query("START user=node({0}) MATCH user-[p:PARTICIPATE]->(routes:RouteMainBean) where p.role='EXPERT' RETURN routes")
	public List<RouteMainBean> getRoutes(Long userId);
}
