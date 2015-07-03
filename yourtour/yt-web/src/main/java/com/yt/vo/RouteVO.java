package com.yt.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yt.business.bean.RouteBean;
import com.yt.utils.DateUtils;

public class RouteVO extends RouteBean {
	private String feeling;
	
	private String formattedStartTime;
	private String formattedEndTime;
	
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
	
	@JsonProperty("startTime")
	public String getFormattedStartTime(){
			return this.formattedStartTime;
	}
	
	@JsonProperty("endTime")
	public String getFormattedEndTime(){
		return this.formattedEndTime;
}

	public void setFormattedStartTime(String formattedStartTime) {
		this.formattedStartTime = formattedStartTime;
	}

	public void setFormattedEndTime(String formattedEndTime) {
		this.formattedEndTime = formattedEndTime;
	}
}
