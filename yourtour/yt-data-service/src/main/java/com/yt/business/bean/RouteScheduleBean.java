package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.common.Constants.ScheduleType;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 行程安排bean，定义了行程中的每个安排信息
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年6月30日</td>
 * <td>John Peng/td>
 * <td>根据定稿后的hbase和neo4j操作进行了修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_ROUTE_SCHEDULE_INFO")
@NodeEntity
public class RouteScheduleBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	private static final String INDEX_NAME = "route-schedule";

	private @HbaseColumn(name = "rid")
	String routeId; // 行程ID
	private @HbaseColumn(name = "pid")
	@RelatedTo(type = "parent", direction = Direction.OUTGOING)
	RouteScheduleBean parent; // 父级ID, 根节点的话，该值为0
	private @HbaseColumn(name = "prid")
	@RelatedTo(type = "prev", direction = Direction.OUTGOING)
	RouteScheduleBean prior; // 前一个行程安排ID， 同层节点第一个，那么该值为0，
	private @HbaseColumn(name = "nid")
	@RelatedTo(type = "next", direction = Direction.OUTGOING)
	RouteScheduleBean next; // 后一个行程安排ID， 同层节点最后一个，那么该值为0

	private @HbaseColumn(name = "name")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String name; // 安排名称
	private @HbaseColumn(name = "addr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String Address; // 地址信息
	private @HbaseColumn(name = "pos")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String position; // 位置信息
	private @HbaseColumn(name = "type")
	ScheduleType type; // 安排类型
	private @HbaseColumn(name = "img")
	transient String imageUrl; // 行程图片
	private @HbaseColumn(name = "intr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String intro; // 概述， 可以针对行程安排中具体某天或者某个景点进行描述
	private @HbaseColumn(name = "feat")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String feature; // 特点， 可以针对行程安排中具体某天或者某个景点进行特点描述
	private @HbaseColumn(name = "reas")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String reason; // 推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述
	private @HbaseColumn(name = "reid")
	String resId; // 关联资源ID
	private @HbaseColumn(name = "resn")
	String resName; // 关联资源名称
	private @HbaseColumn(name = "opt")
	String optional; // 安排是否可选择。 0：可选 1：必须
	private @HbaseColumn(name = "grp")
	String group; // 安排类型，集体还是自由。集体/自由
	private @HbaseColumn(name = "st")
	long startTime; // 安排开始时间，以秒为单位
	private @HbaseColumn(name = "et")
	long endTime; // 安排结束时间，以秒为单位
	private @HbaseColumn(name = "peri")
	int period; // 安排持续时间， 以秒为单位

	// 交通设置
	private @HbaseColumn(name = "sp")
	String startPlace; // 出发地，针对交通类型的行程安排有效
	private @HbaseColumn(name = "ap")
	String arrivePlace; // 目的地，针对交通类型的行程安排有效
	private @HbaseColumn(name = "tm")
	String trafficMode; // 飞机，火车，大巴
	private @HbaseColumn(name = "tn")
	String trafficNo; // 班次

	// 住宿设置
	private @HbaseColumn(name = "rn")
	String roomNo; // 房间号

	// 提醒设置
	private @HbaseColumn(name = "alta")
	int alertable; // 是否提醒
	private @HbaseColumn(name = "altt")
	long alertTime; // 提醒时间

	private @HbaseColumn(name = "cuid")
	String createdUserId = "";
	private @HbaseColumn(name = "ct")
	long createdTime;
	private @HbaseColumn(name = "uuid")
	String updatedUserId = "";
	private @HbaseColumn(name = "ut")
	long updatedTime;
	private @HbaseColumn(name = "stat")
	Status status;

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

	public ScheduleType getType() {
		return type;
	}

	public void setType(ScheduleType type) {
		this.type = type;
	}

	public RouteScheduleBean getParent() {
		return this.parent;
	}

	public void setParent(RouteScheduleBean parent) {
		this.parent = parent;
	}

	public RouteScheduleBean getPrior() {
		return this.prior;
	}

	public void setPrior(RouteScheduleBean prior) {
		this.prior = prior;
	}

	public RouteScheduleBean getNext() {
		return this.next;
	}

	public void setNext(RouteScheduleBean next) {
		this.next = next;
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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getAlertable() {
		return alertable;
	}

	public void setAlertable(int alertable) {
		this.alertable = alertable;
	}

	public long getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(long alertTime) {
		this.alertTime = alertTime;
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

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
