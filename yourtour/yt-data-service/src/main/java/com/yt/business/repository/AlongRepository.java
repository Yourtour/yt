package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.AlongBean;

public interface AlongRepository extends CrudAllInOneOperate {
	public AlongBean getAlongByGraphId(Long graphId, int startIndex, int size) throws Exception;

	public List<AlongBean> getAlongsByPlace(Long placeGraphId, int start, int limit) throws Exception;
}
