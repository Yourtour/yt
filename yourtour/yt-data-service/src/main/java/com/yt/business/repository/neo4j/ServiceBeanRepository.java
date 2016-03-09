package com.yt.business.repository.neo4j;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteServiceBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ServiceBeanRepository extends GraphRepository<RouteServiceBean> {
    /**
     *
     * @param placeIds
     * @return
     */
    @Query("START places=node({0}) MATCH places<-[:AT]-user-[:HAS]->(service:ExpertServiceBean) RETURN service, user")
    public List<ExpertServiceTuple> getPlaceServices(Long[] placeIds,Long nextCursor, int limit);

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
    public List<RouteServiceTuple> getRouteServices(Long routeId);


}
