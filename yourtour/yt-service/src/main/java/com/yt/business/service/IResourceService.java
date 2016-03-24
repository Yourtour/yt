package com.yt.business.service;

import java.util.List;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;

public interface IResourceService {
	/**
	 * 保存资源信息
	 * 
	 * @param resource
	 * @param userId
	 * @throws Exception
	 */
	public void saveResource(ResourceBean resource, ResourceType resourceType,
			Long userId) throws Exception;

	/**
	 * 获取资源信息
	 * 
	 * @param resourceId
	 * @return
	 * @throws Exception
	 */
	public ResourceBean getResource(Long resourceId, ResourceType resourceType)
			throws Exception;

	/**
	 * 删除资源信息
	 * 
	 * @param resourceId
	 * @param userId
	 * @throws Exception
	 */
	public ResourceBean deleteResource(Long resourceId,
			ResourceType resourceType, Long userId) throws Exception;

	/**
	 * 获取目的地资源
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @param resType
	 * @return
	 * @throws Exception
	 */
	public List<? extends ResourceBean> getResources(Long placeId,
			Long nextCursor, int limit, ResourceType resourceType)
			throws Exception;
}
