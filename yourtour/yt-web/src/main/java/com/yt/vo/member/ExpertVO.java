package com.yt.vo.member;

import com.yt.business.bean.*;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;
import com.yt.vo.CommentVO;
import com.yt.vo.route.RouteVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpertVO extends UserVO{
	private static final long serialVersionUID = 7565498288049730405L;

	private String 	places;

	public static ExpertVO transform(ExpertBean bean){
		ExpertVO expert = new ExpertVO();

		UserProfileBean profile = bean.getProfile();
		expert.setId(bean.getGraphId());
		expert.setNickName(profile.getNickName());
		expert.setImageUrl(profile.getImageUrl());
		expert.setIdentity(profile.getIdentity());
		expert.setAge(profile.getAge());
		expert.setIdAuthenticate(profile.getIdAuthenticate());
		expert.setMobileAuthenticate(profile.getMobileAuthenticate());
		expert.setSnsAuthenticate(profile.getSnsAuthenticate());
		expert.setMemo(bean.getMemo());
		expert.setTags(bean.getTags());

		return expert;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}
}
