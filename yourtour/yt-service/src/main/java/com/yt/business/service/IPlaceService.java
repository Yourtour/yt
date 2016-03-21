package com.yt.business.service;

import com.yt.business.bean.*;
import com.yt.business.bean.pack.PlaceBeanPack;

import java.util.List;

/**
 * 和目的地相关服务接口定义
 */
public interface IPlaceService {
	/**
	 * 保存目的地
	 * 
	 * @param place
	 * @param place
	 * @throws Exception
	 */
	public void savePlace(PlaceBean place, Long userId) throws Exception;

	/**
	 * 删除目的地
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void deletePlace(Long id, Long userId) throws Exception;

	/**
	 * 获取目的地首页显示信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlaceBeanPack getPlacePack(Long id, Long lastModifiedTime)
			throws Exception;

	/**
	 * 获取目的地
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlaceBean getPlace(Long id) throws Exception;

	/**
	 * 获取所有目的地信息，并且按照树状结构返回
	 * 
	 * @return 目的地列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<PlaceBean> getAllPlaces() throws Exception;

	/**
	 * 获取所有的推荐目的地
	 * 
	 * @return 目的地列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<PlaceBean> getRecommendPlaces() throws Exception;

	/**
	 * 获取目的地的咨询信息
	 * 
	 * @param placeId
	 * @return
	 * @throws Exception
	 */
	public List<InfoBean> getInfoes(Long placeId, Long nextCursor, int limit)
			throws Exception;

	/**
	 * 获取目的地达人
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(Long placeId, Long nextCursor, int limit)
			throws Exception;

	/**
	 * 获取目的地游玩资源
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ResourceBean> getResources(Long placeId, Long nextCursor,
			int limit) throws Exception;

	/**
	 * 获取目的地推荐行程
	 * 
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor,
			int limit) throws Exception;
}
