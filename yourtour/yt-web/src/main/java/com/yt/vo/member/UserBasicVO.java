package com.yt.vo.member;

import com.yt.business.bean.UserProfileBean;

public class UserBasicVO {
	private long id;
	private String mobile, name, imageUrl;

	public UserBasicVO() {
		super();
	}

	public static UserBasicVO transform(UserProfileBean user) {
		if (user == null) {
			return null;
		}
		UserBasicVO vo = new UserBasicVO();
		vo.setId(user.getId());
		vo.setMobile(user.getMobileNo());
		vo.setName(user.getNickName());
		vo.setImageUrl(user.getImageUrl());
		return vo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
