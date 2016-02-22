package com.yt.business.neo4j.repository;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RestaurantResourceBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by 林平 on 2016/2/21.
 */
public interface ResourceBeanRepository  extends
        GraphRepository<ResourceBean> {

    @Query("START place=node({0}) MATCH place<-[:AT]-(resource:ResourceBean) RETURN resource")
    public List<ResourceBean> getResources(Long placeId);
}
