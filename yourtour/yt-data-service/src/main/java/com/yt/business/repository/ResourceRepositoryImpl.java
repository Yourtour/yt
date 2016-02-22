package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.ResourceBean;
import com.yt.business.neo4j.repository.ResourceBeanRepository;
import com.yt.business.neo4j.repository.RestaurantResourceBeanRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 林平 on 2016/2/21.
 */
@Component
public class ResourceRepositoryImpl  extends CrudAllInOneOperateImpl implements
        ResourceRepository  {
    private static final Log LOG = LogFactory
            .getLog(RestaurantRepositoryImpl.class);

    @Autowired
    private ResourceBeanRepository repository;

    @Override
    public List<ResourceBean> getResources(Long placeId, Long nextCursor, int limit, int distance) throws Exception {
        return repository.getResources(placeId);
    }
}
