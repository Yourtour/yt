package com.yt.business.service;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;

import java.util.List;

/**
 * 和目的地相关服务接口定义
 */
public interface IPlaceService {
	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<PlaceBean> getAllRootPlaces() throws Exception;

	/**
	 * 保存目的地
	 * @param place
	 * @throws Exception
	 */
	public void savePlace(PlaceBean place, Long userId) throws Exception;

	/**
	 * 删除目的地
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void deletePlace(Long id, Long userId) throws Exception;

	/**
	 * 获取目的地
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlaceBean getPlace(Long id) throws Exception;

	/**
	 * 获取某个目的地的下属目的地
	 * @param parentCode
	 * @return
	 */
	public List<PlaceBean> getPlaces(String parentCode);

	/**
	 * 获取和指定目的地相关的目的地
	 * @param placeId
	 * @return
	 */
	public List<PlaceBean> getRelatedPlaces(Long placeId);

	/**
	 * 获取目的地达人
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(Long placeId, Long nextCursor, int limit) throws Exception;

	/**
	 * 获取目的地推荐行程
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor, int limit) throws Exception;


}
