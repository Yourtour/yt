package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.HotelResourceBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface HotelRepository extends ICrudOperate {
	public HotelResourceBean getHotelByGraphId(Long graphId) throws Exception;

	public List<HotelResourceBean> getHotelsByPage(int start, int limit)
			throws Exception;
}
