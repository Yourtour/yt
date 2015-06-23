package com.yt.vo;

import com.yt.bean.RouteBean;

public class RouteVO extends RouteBean {
	private String feeling;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8728699166400902336L;

	public RouteVO(){
		
	}

	public String getFeeling() {
		return feeling;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
}
