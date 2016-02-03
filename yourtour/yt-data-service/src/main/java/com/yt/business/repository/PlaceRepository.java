package com.yt.business.repository;

import java.util.List;
import java.util.Map;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;

public interface PlaceRepository extends CrudAllInOneOperate {
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
	 * @param startIndex
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ExpertBean> getExperts(Long placeId, int startIndex, int limit) throws Exception;

	/**
	 *
	 * @param placeId
	 * @param startIndex
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<RouteMainBean> getRoutes(Long placeId, int startIndex, int limit) throws Exception;
}
