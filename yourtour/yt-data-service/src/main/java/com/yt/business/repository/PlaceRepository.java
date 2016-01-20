package com.yt.business.repository;

import java.util.List;
import java.util.Map;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.PlaceBean;

public interface PlaceRepository extends CrudAllInOneOperate {
	public List<PlaceBean> getAllRootPlaces() throws Exception;

	public List<PlaceBean> getAllSubPlaces(Long graphId) throws Exception;
	
	public List<PlaceBean> getPlaces(Long graphId);
	
	public List<PlaceBean> getPlaces(String parentCode);

	public List<PlaceBean> getRouteRecommendPlaces(Long userId);

	public List<PlaceBean> getRelatedPlaces(Long placeId);

	public void save(PlaceBean place, String operator) throws Exception;
}
