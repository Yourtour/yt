package com.yt.business.neo4j.repository;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@QueryResult
public class RouteTuple {
	@ResultColumn("route")
	private RouteMainBean route;

	@ResultColumn("owner")
	private UserProfileBean owner;

	@ResultColumn("expert")
	private ExpertBean expert;

	public RouteTuple(){
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}

	public UserProfileBean getOwner() {
		return owner;
	}

	public void setOwner(UserProfileBean owner) {
		this.owner = owner;
	}

	public ExpertBean getExpert() {
		return expert;
	}

	public void setExpert(ExpertBean expert) {
		this.expert = expert;
	}
}