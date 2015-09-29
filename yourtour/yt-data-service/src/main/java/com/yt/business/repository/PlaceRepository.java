package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.PlaceBean;

public interface PlaceRepository extends CrudAllInOneOperate {
	public List<PlaceBean> getAllRootPlaces() throws Exception;

	public List<PlaceBean> getAllSubPlaces(Long graphId) throws Exception;

	public void save(Long parentId, PlaceBean place, String operator)
			throws Exception;
}
