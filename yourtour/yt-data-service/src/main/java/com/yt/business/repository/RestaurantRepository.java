package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.RestaurantResourceBean;

public interface RestaurantRepository extends CrudAllInOneOperate {
	public RestaurantResourceBean getRestaurantByGraphId(Long graphId)
			throws Exception;

	public List<RestaurantResourceBean> getRestaurantsByPage(int start,
			int limit) throws Exception;
}
