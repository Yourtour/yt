package com.yt.vo.member;

import com.yt.business.bean.UserProfileBean;
import com.yt.business.bean.UserProfileBean.GenderType;


public class RegisterProfileVO extends RegisterVO {
	private String nickName, tags;
	private GenderType generType;
	
	public static void transform(RegisterProfileVO vo, UserProfileBean profile) {
		profile.setNickName(vo.getNickName());
		profile.setTags(vo.getTags());
		profile.setGender(vo.getGener());
	}

	public RegisterProfileVO() {
		super();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public GenderType getGener() {
		return generType;
	}

	public void setGener(GenderType generType) {
		this.generType = generType;
	}

}
