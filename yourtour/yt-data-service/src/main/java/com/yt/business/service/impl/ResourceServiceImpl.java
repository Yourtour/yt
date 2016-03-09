package com.yt.business.service.impl;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.ResourceBean;
import com.yt.business.repository.neo4j.ResourceBeanRepository;
import com.yt.business.service.IResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 林平 on 2016/2/21.
 */
@Component
public class ResourceServiceImpl extends CrudAllInOneOperateImpl implements
        IResourceService {
    private static final Log LOG = LogFactory
            .getLog(IRestaurantServiceImpl.class);

    @Autowired
    private ResourceBeanRepository repository;

    @Override
    public List<ResourceBean> getResources(Long placeId, Long nextCursor, int limit, int distance) throws Exception {
        return repository.getResources(placeId);
    }
}
