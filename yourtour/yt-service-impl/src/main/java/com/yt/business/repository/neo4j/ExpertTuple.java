package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.UserProfileBean;

@QueryResult
public class ExpertTuple {
	@ResultColumn("profile")
	private UserProfileBean profile;

	public ExpertTuple(){
	}

	public UserProfileBean getProfile() {
		return profile;
	}

	public void setProfile(UserProfileBean profile) {
		this.profile = profile;
	}
}