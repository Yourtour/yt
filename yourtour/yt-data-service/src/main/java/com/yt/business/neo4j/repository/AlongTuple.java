package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.RouteMainBean;

@QueryResult
public class AlongTuple {
	@ResultColumn("along")
	private AlongBean along;

	@ResultColumn("route")
	private RouteMainBean route;
	
	public AlongTuple(){
	}

	public AlongBean getAlong() {
		return along;
	}

	public void setAlong(AlongBean along) {
		this.along = along;
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}
}