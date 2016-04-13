package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 服务预订信息
 */
@HbaseTable(name = "T_ORDER_INFO")
@NodeEntity
public class OrderBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	public static final String RELATION_TYPE_BOOK = "BOOK";

	public static final String RELATION_TYPE_SERVICE = "SERVICE";

	public enum Status{ //订单状态
		SUBMITTED,  //提交
		CANCELLED,  //取消
		PAYED,    //已支付
		SCHEDULE, //已安排
		USED,     //已服务
		CONFIRMED, //使用确认
		COMMENTED  //已评价
	}

	private Long	routeId; //关联行程ID

	private String title; // 订单名称
	private String type; // 订单分类，来自于字典表
	private String content; // 订单内容
	private String fee;
	private String currency;
	private Long 	fromTime;  //预计服务开始时间
	private Long 	endTime;   //预计服务结束时间
	private	Long	usedTime;   //实际结束时间
	private Long	payTime;   //支付时间
	private String  specialties; //每类订单扩展属性
	private String  memo;      //备注
	private Status	status;	   //订单状态

	@Neo4jRelationship(relationship = RELATION_TYPE_SERVICE, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean expert = null; // 提供服务达人

	@Neo4jRelationship(relationship = RELATION_TYPE_BOOK, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean user = null; // 订购服务用户

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ExpertServiceBean.class, direction = Direction.OUTGOING)
	private transient ExpertServiceBean service = null; // 预订的达人服务

	public OrderBean() {
		super();
	}

	public OrderBean(Long serviceId) {
		super(serviceId);
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public Long getFromTime() {
		return fromTime;
	}

	public void setFromTime(Long fromTime) {
		this.fromTime = fromTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Long usedTime) {
		this.usedTime = usedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ExpertServiceBean getService() {
		return service;
	}

	public void setService(ExpertServiceBean service) {
		this.service = service;
	}

	public Long getPayTime() {
		return payTime;
	}

	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpecialties() {
		return specialties;
	}

	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public UserProfileBean getExpert() {
		return expert;
	}

	public void setExpert(UserProfileBean expert) {
		this.expert = expert;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}
