package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.HotelResourceBean;

public interface HotelRepository extends CrudAllInOneOperate {
	public HotelResourceBean getHotelByGraphId(Long graphId) throws Exception;

	public List<HotelResourceBean> getHotelsByPage(int start, int limit)
			throws Exception;
}
