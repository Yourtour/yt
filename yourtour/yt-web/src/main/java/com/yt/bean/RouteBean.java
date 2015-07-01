package com.yt.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yt.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * @author Tony.Zhang
 * 
 * 行程bean，定义了行程基本信息
 *
 */
@HbaseTable(name = "T_ROUTE_INFO")
public class RouteBean extends BaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	
	private 	@HbaseColumn(name = "name")	String 	name; 	//安排名称
	private 	@HbaseColumn(name = "iu")			String 	imageUrl; 	//行程图片
	private	@HbaseColumn(name = "line")		String 	lineName; 	//线路名称
	private	@HbaseColumn(name = "step")		int 	 	step; 	//规划步骤
	private 	@HbaseColumn(name = "intr")		String 	intro; 	//概述， 可以针对行程安排中具体某天或者某个景点进行描述 
	private 	@HbaseColumn(name = "feat")		String 	feature; 	//特点， 可以针对行程安排中具体某天或者某个景点进行特点描述
	private 	@HbaseColumn(name = "reas")		String 	reason;  	//推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述
	private	@HbaseColumn(name = "plac")		String 	place; 	//目的地
	private 	@HbaseColumn(name = "st")		long	startTime; 	//安排开始时间，以秒为单位
	private 	@HbaseColumn(name = "et")		long	endTime;		//安排结束时间，以秒为单位
	private	@HbaseColumn(name = "peri")		int		period;		//安排持续时间， 以秒为单位
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		Status	status;
	
	public RouteBean() {
		super();
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	@JsonIgnore
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	@JsonIgnore
	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
