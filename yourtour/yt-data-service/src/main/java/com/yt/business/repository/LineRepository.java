package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;

public interface LineRepository extends CrudAllInOneOperate {

	public List<SceneResourceBean> getScenesByPlace(Long placeId)
			throws Exception;

	public List<HotelResourceBean> getHotelsByPlace(Long placeId)
			throws Exception;

	public List<RestaurantResourceBean> getRestaurantsByPlace(Long placeId)
			throws Exception;
	
	/**
	 * 根据目的地查询线路信息
	 * 
	 * @param placeId
	 *            指定的目的地ID
	 * @return 该目的地关联的线路
	 * @throws Exception
	 *             查询过程中发生的异常
	 */
	public List<LineBean> getLinesByPlace(long placeId) throws Exception;
}