package com.yt.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * @author Tony.Zhang
 * 
 * 行程安排bean，定义了行程中的每个安排信息
 *
 */
@HbaseTable(name = "T_ROUTE_SCHEDULE_INFO")
public class RouteScheduleBean extends BaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	
	public static enum TYPE{MEMO, SCENE, FOOD, HOTEL, TRAFFIC}
	
	private 	@HbaseColumn(name = "rid")		String 	routeId; 	//行程ID
	private 	@HbaseColumn(name = "name")		String 	name; 	//安排名称
	private 	@HbaseColumn(name = "type")		TYPE 	type; 	//安排类型
	private 	@HbaseColumn(name = "pid")		String 	parentId; 	//父级ID, 根节点的话，该值为0 
	private 	@HbaseColumn(name = "prid")		String 	priorId; 	//前一个行程安排ID， 同层节点第一个，那么该值为0，
	private 	@HbaseColumn(name = "nid")		String 	nextId; 	//后一个行程安排ID， 同层节点最后一个，那么该值为0
	private 	@HbaseColumn(name = "iu")		String 	imageUrl; 	//行程图片
	private 	@HbaseColumn(name = "intr")		String 	intro; 	//概述， 可以针对行程安排中具体某天或者某个景点进行描述 
	private 	@HbaseColumn(name = "feat")		String 	feature; 	//特点， 可以针对行程安排中具体某天或者某个景点进行特点描述
	private 	@HbaseColumn(name = "reas")		String 	reason;  	//推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述
	private	@HbaseColumn(name = "reid")		String 	resId; 	//关联资源ID
	private	@HbaseColumn(name = "resn")		String	resName; 	//关联资源名称
	private	@HbaseColumn(name = "opt")		String	optional; 	//安排是否可选择。 0：可选  1：必须
	private	@HbaseColumn(name = "grp")		String	group;		//安排类型，集体还是自由。集体/自由
	private 	@HbaseColumn(name = "st")		long	startTime; 	//安排开始时间，以秒为单位
	private 	@HbaseColumn(name = "et")		long	endTime;		//安排结束时间，以秒为单位
	private	@HbaseColumn(name = "peri")		int		period;		//安排持续时间， 以秒为单位
	private 	@HbaseColumn(name = "sp")		String 	startPlace; 	//出发地，针对交通类型的行程安排有效
	private 	@HbaseColumn(name = "ap") 	String 	arrivePlace;	 //目的地，针对交通类型的行程安排有效
	private	@HbaseColumn(name = "tm") 	String	trafficMode;  // 飞机，火车，大巴
	private	@HbaseColumn(name = "tn")		String	trafficNo;		//班次
	private	@HbaseColumn(name = "rn")		String	roomNo;		//房间号
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		int	status;
	
	public RouteScheduleBean() {
		super();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPriorId() {
		return priorId;
	}

	public void setPriorId(String priorId) {
		this.priorId = priorId;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
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

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getArrivePlace() {
		return arrivePlace;
	}

	public void setArrivePlace(String arrivePlace) {
		this.arrivePlace = arrivePlace;
	}

	public String getTrafficMode() {
		return trafficMode;
	}

	public void setTrafficMode(String trafficMode) {
		this.trafficMode = trafficMode;
	}

	public String getTrafficNo() {
		return trafficNo;
	}

	public void setTrafficNo(String trafficNo) {
		this.trafficNo = trafficNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
