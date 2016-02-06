package com.yt.vo.member;

import com.yt.business.bean.*;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;
import com.yt.vo.BaseVO;
import com.yt.vo.CommentVO;
import com.yt.vo.route.RouteVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpertVO extends BaseVO {
	private static final long serialVersionUID = 7565498288049730405L;

	private String 	places;
	private UserVO profile;

	public static ExpertVO transform(ExpertBean bean){
		ExpertVO expert = new ExpertVO();

		expert.fromBean(bean);

		expert.setProfile(UserVO.transform(bean.getProfile()));

		return expert;
	}

	public UserVO getProfile() {
		return profile;
	}

	public void setProfile(UserVO profile) {
		this.profile = profile;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}
}
