package com.yt.business.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.repository.neo4j.ResourceBeanRepository;
import com.yt.business.service.IResourceService;
import com.yt.core.utils.BeanUtils;
import com.yt.neo4j.repository.CrudOperate;

/**
 * Created by 林平 on 2016/2/21.
 */
@Service
public class ResourceServiceImpl extends ServiceBase implements
		IResourceService {
	private static final Log LOG = LogFactory.getLog(ResourceServiceImpl.class);

	@Autowired
	private ResourceBeanRepository repository;

	@Autowired
	private CrudOperate<ResourceBean> resourceCrudOperate;

	@Override
	public void saveResource(ResourceBean resource, ResourceType resourceType,
			Long userId) throws Exception {
		ResourceBean existed = resource;
		if (existed.isNew()) {
			resourceCrudOperate.save(resource);
		} else {
			existed = resourceCrudOperate.get(resource.getId(), false);
			BeanUtils.merge(resource, existed);
		}

		super.updateBaseInfo(existed, userId);
		resourceCrudOperate.save(existed);
	}

	@Override
	public ResourceBean getResource(Long resourceId, ResourceType resourceType)
			throws Exception {
		ResourceBean resource = this.resourceCrudOperate.get(resourceId);

		return resource;
	}

	@Override
	public ResourceBean deleteResource(Long resourceId,
			ResourceType resourceType, Long userId) throws Exception {
		ResourceBean resource = this.resourceCrudOperate.get(resourceId);
		if (resource == null) {
			throw new Exception(String.format(
					"The resource not exist, id(%d), type(%s).", resourceId,
					resourceType.name()));
		}
		resource.setDeleted(true);
		super.updateBaseInfo(resource, userId);
		resourceCrudOperate.save(resource);
		return resource;
	}

	@Override
	public List<? extends ResourceBean> getResources(Long placeId,
			Long nextCursor, int limit, ResourceType resourceType)
			throws Exception {
		// repository.getResources(placeId, nextCursor, limit, resType);
		return null;
	}
}
