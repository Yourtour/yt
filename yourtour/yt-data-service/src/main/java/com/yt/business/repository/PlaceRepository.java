package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.PlaceBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface PlaceRepository extends ICrudOperate {
	public List<PlaceBean> getAllRootPlaces() throws Exception;

	public List<PlaceBean> getAllSubPlaces(Long graphId) throws Exception;

	public void save(Long parentId, PlaceBean place, String operator)
			throws Exception;
}
