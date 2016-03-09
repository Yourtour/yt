package com.yt.business.service.impl;

import com.yt.business.bean.ResourceBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.neo4j.ResourceBeanRepository;
import com.yt.business.service.IResourceService;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 林平 on 2016/2/21.
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl implements IResourceService {
    private static final Log LOG = LogFactory.getLog(ResourceServiceImpl.class);

    @Autowired
    private ResourceBeanRepository repository;

    @Autowired
    private CrudOperate<ResourceBean> resourceCrudOperate;


    @Override
    public void saveResource(ResourceBean resource, Constants.ResType resType, Long userId) throws Exception {
        resourceCrudOperate.save(resource);
    }

    @Override
    public ResourceBean getResource(Long resourceId,Constants.ResType resType) throws Exception {
        ResourceBean resource = this.resourceCrudOperate.get(resourceId);

        return resource;
    }

    @Override
    public void deleteResource(Long resourceId, Constants.ResType resType, Long userId) throws Exception {
        ResourceBean resource = this.resourceCrudOperate.get(resourceId);

        this.resourceCrudOperate.delete(resource);
    }

    @Override
    public List<? extends ResourceBean> getResources(Long placeId, Long nextCursor, int limit, Constants.ResType resType) throws Exception {
        return null;
    }
}
