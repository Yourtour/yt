package com.yt.business.repository.neo4j;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@QueryResult
public class ExpertTuple {
	@ResultColumn("expert")
	private ExpertBean expert;

	@ResultColumn("profile")
	private UserProfileBean profile;

	public ExpertTuple(){
	}

	public ExpertBean getExpert() {
		expert.setProfile(profile);
		return expert;
	}

	public void setExpert(ExpertBean expert) {
		this.expert = expert;
	}

	public UserProfileBean getProfile() {
		return profile;
	}

	public void setProfile(UserProfileBean profile) {
		this.profile = profile;
	}
}