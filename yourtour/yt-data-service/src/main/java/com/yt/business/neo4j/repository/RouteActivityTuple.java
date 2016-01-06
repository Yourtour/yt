package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteActivityBean;

@QueryResult
public class RouteActivityTuple {
	@ResultColumn("activity")
	private RouteActivityBean activity;

	@ResultColumn("resource")
	private ResourceBean resource;
	
	public RouteActivityTuple(){
	}

	public RouteActivityBean getActivity() {
		return activity;
	}

	public void setActivity(RouteActivityBean activity) {
		this.activity = activity;
	}

	public ResourceBean getResource() {
		return resource;
	}

	public void setResource(ResourceBean resource) {
		this.resource = resource;
	}
}