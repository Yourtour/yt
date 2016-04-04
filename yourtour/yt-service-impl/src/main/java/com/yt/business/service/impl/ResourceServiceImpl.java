package com.yt.business.service.impl;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.repository.neo4j.ResourceBeanRepository;
import com.yt.business.repository.query.IResourceQuery;
import com.yt.business.service.IResourceService;
import com.yt.core.utils.BeanUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

	@Autowired
	private IResourceQuery  resourceQuery;

	@Override
	public void saveResource(ResourceBean resource, Long userId)
			throws Exception {
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
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Logical delete a resource successfully, resource(%d), type(%s), user(%d).",
							resourceId, resourceType.name(), userId));
		}
		return resource;
	}

	@Override
	public PagingDataBean<List<? extends ResourceBean>> getResources(
			ResourceType resourceType, PagingConditionBean pagingCondition)
			throws Exception {
		int total = pagingCondition.getTotal();
		if (total <= 0) {
			total = repository.count(resourceType.name());
		}
		List<ResourceBean> resources = repository.getResources(
				resourceType.name(), pagingCondition.getNextCursor(),
				pagingCondition.getLimit());
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Fetch resources data successfully, type(%s), total(%d), condition(%s).",
							resourceType.name(), resources.size(),
							pagingCondition.toString()));
		}
		return new PagingDataBean<List<? extends ResourceBean>>(total,
				resources);
	}

	@Override
	public PagingDataBean<List<? extends ResourceBean>> getPlaceResources(
			Long placeId, PagingConditionBean pagingCondition) throws Exception {
		int total = pagingCondition.getTotal();
		if (total <= 0) {
			total = repository.count(placeId);
		}
		List<ResourceBean> resources = repository.getPlaceResources(placeId,
				pagingCondition.getNextCursor(), pagingCondition.getLimit());
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Fetch resources at the place data successfully, place(%d), total(%d), condition(%s).",
							placeId, resources.size(),
							pagingCondition.toString()));
		}
		return new PagingDataBean<List<? extends ResourceBean>>(total,
				resources);
	}

	@Override
	public PagingDataBean<List<? extends ResourceBean>> getPlaceResources(
			Long placeId, ResourceType resourceType,
			PagingConditionBean pagingCondition) throws Exception {
		int total = pagingCondition.getTotal();
		if (total <= 0) {
			total = repository.count(placeId, resourceType.name());
		}
		List<ResourceBean> resources = repository.getPlaceResources(placeId,
				resourceType.name(), pagingCondition.getNextCursor(),
				pagingCondition.getLimit());
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Fetch resources at the place data successfully, place(%d), type(%s), total(%d), condition(%s).",
							placeId, resourceType.name(), resources.size(),
							pagingCondition.toString()));
		}
		return new PagingDataBean<List<? extends ResourceBean>>(total,
				resources);
	}

	@Override
	public PagingDataBean<List<? extends ResourceBean>> getResources(PagingConditionBean pagingCondition, Map<String, Object> params) throws Exception {
		/*String type = params.get("type").toString().toString().toUpperCase();
		switch(ResourceBean.ResourceType.valueOf(type)){
			case FOOD:
				resources = this.restaurantOperator.query(String.format(sb.toString(),RestaurantResourceBean.class.getSimpleName()), params);
				break;
			case HOTEL:
				resources = this.hotelOperator.query(String.format(sb.toString(), HotelResourceBean.class.getSimpleName()), params);
				break;
			default:
				resources = this.sceneOperator.query(String.format(sb.toString(), SceneResourceBean.class.getSimpleName()), params);
				break;
		}
*/
		//return resourceQuery.getResources(pagingCondition, params);

		return new PagingDataBean<>(10, resourceCrudOperate.get(false));
	}
}
