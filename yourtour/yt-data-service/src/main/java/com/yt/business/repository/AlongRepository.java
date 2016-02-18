package com.yt.business.repository;

import java.util.List;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.AlongBean;

public interface AlongRepository extends CrudAllInOneOperate {
	/**
	 *
	 * @param placeGraphId
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<AlongBean> getAlongsByPlace(Long placeGraphId, Long start, int limit) throws Exception;

	/**
	 *
	 * @param routeId
	 * @return
	 * @throws Exception
	 */
	public List<AlongBean> getAlongsByRoute(Long routeId) throws Exception;
}
