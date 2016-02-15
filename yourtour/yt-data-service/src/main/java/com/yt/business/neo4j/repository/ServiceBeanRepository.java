package com.yt.business.neo4j.repository;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ServiceBeanRepository extends GraphRepository<RouteServiceBean> {
    /**
     *
     * @param expertId
     * @return
     */
    @Query("START user=node({0}) MATCH user-[:HAS]->(services:ExpertServiceBean) RETURN services")
    public List<ExpertServiceBean> getExpertServices(Long expertId);

    /**
     *
     * @param routeId
     * @return
     */
    @Query("START route=node({0}) MATCH route-[:HAS]->(routeService:RouteServiceBean)-[]-(expertService:ExpertServiceBean)<-[:HAS]-(user:UserProfileBean) RETURN routeService, expertService, user")
    public List<ServiceTuple> getRouteServices(Long routeId);
}
