package com.yt.vo.member;

import com.yt.business.bean.UserProfileBean;
import com.yt.vo.BaseVO;

public class ExpertVO extends BaseVO {
	private String places;
	private UserVO profile;

	public static ExpertVO transform(UserProfileBean bean) {
		ExpertVO expert = new ExpertVO();

		expert.fromBean(bean);

		expert.setProfile(UserVO.transform(bean));

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
