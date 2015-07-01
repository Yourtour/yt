package com.yt.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yt.bean.RouteBean;
import com.yt.utils.DateUtils;

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
	
	@JsonProperty("startTime")
	public String getFormattedStartTime(){
			Date d = new Date(super.getStartTime());
			
			return DateUtils.format(d);
	}
	
	@JsonProperty("endTime")
	public String getFormattedEndTime(){
		Date d = new Date(super.getEndTime());
		
		return DateUtils.format(d);
}
}
