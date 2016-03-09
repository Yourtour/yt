package com.yt.business.service;

import java.util.List;
import java.util.Map;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;

public interface IPlaceService extends CrudAllInOneOperate {
	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<PlaceBean> getAllRootPlaces() throws Exception;

	/**
	 *
	 * @param graphId
	 * @return
	 * @throws Exception
	 */
	public List<PlaceBean> getAllSubPlaces(Long graphId) throws Exception;

	/**
	 *
	 * @param graphId
	 * @return
	 */
	public List<PlaceBean> getPlaces(Long graphId);

	/**
	 *
	 * @param parentCode
	 * @return
	 */
	public List<PlaceBean> getPlaces(String parentCode);

	/**
	 *
	 * @param userId
	 * @return
	 */
	public List<PlaceBean> getRouteRecommendPlaces(Long userId);

	/**
	 *
	 * @param placeId
	 * @return
	 */
	public List<PlaceBean> getRelatedPlaces(Long placeId);

	/**
	 *
	 * @param place
	 * @param operator
	 * @throws Exception
	 */
	public void save(PlaceBean place, String operator) throws Exception;

	/**
	 *
	 * @param placeId
	 * @return
	 */
	public PlaceBean getPlace4Home(Long placeId) throws Exception;

	/**
	 *
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(Long placeId, Long nextCursor, int limit) throws Exception;

	/**
	 *
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor, int limit) throws Exception;

	/**
	 *
	 * @param placeId
	 * @param nextCursor
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<SceneResourceBean> getSceneResources(Long placeId, Long nextCursor, int limit) throws Exception;

}
