package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.RouteBean;

@QueryResult
public class AlongTuple {
	@ResultColumn("along")
	private AlongBean along;

	@ResultColumn("route")
	private RouteBean route;
	
	public AlongTuple(){
	}

	public AlongBean getAlong() {
		return along;
	}

	public void setAlong(AlongBean along) {
		this.along = along;
	}

	public RouteBean getRoute() {
		return route;
	}

	public void setRoute(RouteBean route) {
		this.route = route;
	}
}