package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;

public interface RouteRepository extends CrudAllInOneOperate {
	public void relateLine(String routeId, String lineId) throws Exception;

	public void unrelateLine(String routeId, String lineId) throws Exception;

	public void containScene(String routeId, String sceneId) throws Exception;

	public void uncontainScene(String routeId, String sceneId) throws Exception;
}
