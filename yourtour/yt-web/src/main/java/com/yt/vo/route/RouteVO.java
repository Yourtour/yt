package com.yt.vo.route;

import com.yt.business.bean.RouteBean;
import com.yt.business.common.Constants.Status;
import com.yt.vo.BaseVO;

public class RouteVO extends BaseVO {
	private static final long HALF_DAY = 3600 * 12;
	private String name; // 安排名称
	private String imageUrl; // 行程图片
	private String lineName; // 线路名称
	private int step; // 规划步骤
	private String intro; // 概述， 可以针对行程安排中具体某天或者某个景点进行描述
	private String feature; // 特点， 可以针对行程安排中具体某天或者某个景点进行特点描述
	private String reason; // 推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述
	private String place; // 目的地
	private long startTime; // 安排开始时间，以秒为单位
	private long endTime; // 安排结束时间，以秒为单位
	private float period; // 安排持续时间， 以天为单位，精确到半天
	private Status status;

	public static float periodSecond2HalfDay(long period) {
		return ((long) period / HALF_DAY) / 2;
	}

	public static long periodHalfDay2Second(float period) {
		return ((long) (period * 2)) * HALF_DAY;
	}

	public static RouteVO transform(RouteBean bean) {
		if (bean == null) {
			return null;
		}
		RouteVO vo = new RouteVO();
		vo.setCreatedTime(bean.getCreatedTime());
		vo.setCreatedUserId(bean.getCreatedUserId());
		vo.setEndTime(bean.getEndTime());
		vo.setFeature(bean.getFeature());
		vo.setId(bean.getGraphId());
		vo.setImageUrl(bean.getImageUrl());
		vo.setIntro(bean.getIntro());
		vo.setLineName(bean.getLineName());
		vo.setName(bean.getName());
		// 将秒转换为天，并精确到半天
		vo.setPeriod(periodSecond2HalfDay(bean.getPeriod()));
		vo.setPlace(bean.getPlace());
		vo.setReason(bean.getReason());
		vo.setRowKey(bean.getRowKey());
		vo.setStartTime(bean.getStartTime());
		vo.setStatus(bean.getStatus());
		vo.setStep(bean.getStep());
		vo.setUpdatedTime(bean.getUpdatedTime());
		vo.setUpdatedUserId(bean.getUpdatedUserId());
		return vo;
	}

	public static RouteBean transform(RouteVO vo) {
		if (vo == null) {
			return null;
		}
		RouteBean bean = new RouteBean();
		bean.setCreatedUserId(vo.getCreatedUserId());
		bean.setEndTime(vo.getEndTime());
		bean.setFeature(vo.getFeature());
		bean.setGraphId(vo.getId());
		bean.setImageUrl(vo.getImageUrl());
		bean.setIntro(vo.getIntro());
		bean.setLineName(vo.getLineName());
		bean.setName(vo.getName());
		// 将天转换为秒，最小单位为半天
		bean.setPeriod((long) (vo.getPeriod() * HALF_DAY));
		bean.setPlace(vo.getPlace());
		bean.setReason(vo.getReason());
		bean.setRowKey(vo.getRowKey());
		bean.setStartTime(vo.getStartTime());
		bean.setStatus(vo.getStatus());
		bean.setStep(vo.getStep());
		bean.setUpdatedUserId(vo.getUpdatedUserId());
		return bean;
	}

	public RouteVO() {
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

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public float getPeriod() {
		return period;
	}

	public void setPeriod(float period) {
		this.period = period;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
