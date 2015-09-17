package com.yt.vo.route;

public class PersonalRouteVO {
	private Long graphid;
	private String rowKey;
	private String name; // 安排名称
	private String imageUrl; // 行程图片
	private String lineName; // 线路名称
	private String reason; // 推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述
	private String place; // 目的地
	private String startTime; // 安排开始时间，YYYY-MM-DD
	private String endTime; // 安排结束时间，YYYY-MM-DD
	private float period; // 安排持续时间， 天，精确到半天

	public PersonalRouteVO() {
		super();
	}

	public PersonalRouteVO(Long graphid, String rowKey, String name) {
		this();
		this.graphid = graphid;
		this.rowKey = rowKey;
		this.name = name;
	}

	public Long getGraphid() {
		return graphid;
	}

	public void setGraphid(Long graphid) {
		this.graphid = graphid;
	}

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public float getPeriod() {
		return period;
	}

	public void setPeriod(float period) {
		this.period = period;
	}
}
