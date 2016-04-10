package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.List;

@HbaseTable(name = "T_ROUTE_CUSTOMIZE")
@NodeEntity
public class RouteCustomizeBean extends BaseBeanImpl {
	private static final long serialVersionUID = -2071225440268179136L;

	public static final String RELATION_TYPE_SUBMIT = "SUBMIT";
	public static final String RELATION_TYPE_DRAWER = "DRAWER";

	public static enum Status{
		SUBMIT,  //提交
		DRAWING, //制定
		ACCEPT, //接受
		REJECT  //拒绝
	}

	private String  name;
	private Long	startDate; //出发日期
	private	int 	dayNum;  //行程天数
	private int		budget;  //预算
	private String	request; //行程要求
	private String	startPlace; //出发地
	private String	toPlaces;   //目的地
	private Status  status;

	@Neo4jRelationship(relationship = RELATION_TYPE_SUBMIT, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean submit = null; //提出人

	@Neo4jRelationship(relationship = RELATION_TYPE_DRAWER, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient  UserProfileBean drawer = null; //定制人

	public RouteCustomizeBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getToPlaces() {
		return toPlaces;
	}

	public void setToPlaces(String toPlaces) {
		this.toPlaces = toPlaces;
	}

	public UserProfileBean getSubmit() {
		return submit;
	}

	public void setSubmit(UserProfileBean submit) {
		this.submit = submit;
	}

	public UserProfileBean getDrawer() {
		return drawer;
	}

	public void setDrawer(UserProfileBean drawer) {
		this.drawer = drawer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
