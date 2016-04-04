package com.yt.business.service;

import java.util.List;
import java.util.Map;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
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
	public void saveResource(ResourceBean resource, Long userId)
			throws Exception;

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
	 * 获取资源
	 * 
	 * @param resourceType
	 * @param pagingCondition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<? extends ResourceBean>> getResources(
			ResourceType resourceType, PagingConditionBean pagingCondition)
			throws Exception;

	/**
	 * 获取资源
	 *
	 * @param pagingCondition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<? extends ResourceBean>> getResources(PagingConditionBean pagingCondition, Map<String, Object> params)
			throws Exception;


	/**
	 * 分页获取目的地资源
	 * 
	 * @param placeId
	 * @param pagingCondition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<? extends ResourceBean>> getPlaceResources(
			Long placeId, PagingConditionBean pagingCondition) throws Exception;

	/**
	 * 分页获取目的地指定类型的资源
	 * 
	 * @param placeId
	 * @param resourceType
	 * @param pagingCondition
	 * @return
	 * @throws Exception
	 */
	public PagingDataBean<List<? extends ResourceBean>> getPlaceResources(
			Long placeId, ResourceType resourceType,
			PagingConditionBean pagingCondition) throws Exception;
}
