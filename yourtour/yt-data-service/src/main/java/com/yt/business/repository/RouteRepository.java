package com.yt.business.repository;

public interface RouteRepository {
	public void relateRoute2Line(String routeId, String lineId, boolean isAdd)
			throws Exception;

	public void relateRoute2Scene(String routeId, String sceneId, boolean isAdd)
			throws Exception;
}
