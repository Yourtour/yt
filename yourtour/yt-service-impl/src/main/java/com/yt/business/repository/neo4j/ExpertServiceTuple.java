package com.yt.business.repository.neo4j;

import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@QueryResult
public class ExpertServiceTuple {
	@ResultColumn("service")
	private ExpertServiceBean service;

	@ResultColumn("user")
	private UserProfileBean  user;

	public ExpertServiceTuple(){
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public ExpertServiceBean getService() {
		service.setUser(user);
		return service;
	}

	public void setService(ExpertServiceBean service) {
		this.service = service;
	}
}