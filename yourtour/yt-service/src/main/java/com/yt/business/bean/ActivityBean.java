package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;

public class ActivityBean extends BaseBeanImpl {
	private static final long serialVersionUID = -5019182758425160992L;
	
	private String imageUrl;

	public ActivityBean() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
