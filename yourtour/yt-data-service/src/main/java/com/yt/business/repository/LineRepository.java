package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;

public interface LineRepository extends CrudAllInOneOperate {

	public LineBean getLineByGraphId(Long graphId) throws Exception;

	public List<SceneResourceBean> getScenesByPlace(Long placeId)
			throws Exception;

	public List<HotelResourceBean> getHotelsByPlace(Long placeId)
			throws Exception;

	public List<RestaurantResourceBean> getRestaurantsByPlace(Long placeId)
			throws Exception;

	public void containScene(String lineId, String sceneId) throws Exception;

	public void uncontainScene(String lineId, String sceneId) throws Exception;

	public List<LineBean> getLinesByPage(int start, int limit) throws Exception;

	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception;

	public List<LineBean> queryRecommandLine2(String[] places, int dayNum,
			String[] scenes) throws Exception;
	
	/**
	 * 根据目的地查询线路信息
	 * @param placeId
	 * @return
	 * @throws Exception
	 */
	public List<LineBean> queryLinesByPlace(long placeId) throws Exception;
}