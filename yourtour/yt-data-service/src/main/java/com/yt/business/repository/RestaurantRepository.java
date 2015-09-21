package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.RestaurantResourceBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface RestaurantRepository extends ICrudOperate {
	public RestaurantResourceBean getRestaurantByGraphId(Long graphId)
			throws Exception;

	public List<RestaurantResourceBean> getRestaurantsByPage(int start,
			int limit) throws Exception;
}
