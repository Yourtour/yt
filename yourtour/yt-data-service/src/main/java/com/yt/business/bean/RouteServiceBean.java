package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@HbaseTable(name = "T_ROUTE_SERVICE")
@NodeEntity
public class RouteServiceBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	private long 	fromDate;
	private long 	endDate;
	private int 	adultNum;
	private int 	oldNum;
	private int 	childNum;
	private int		fee;
	private String	place;
	private String	memo;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ExpertServiceBean.class, direction = Direction.OUTGOING)
	private transient ExpertServiceBean service = null; // 行程活动关联的行程日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean route = null; // 行程活动关联的行程日程

	public RouteServiceBean() {
		super();
	}

	public RouteServiceBean(Long serviceId) {
		super(serviceId);
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}

	public ExpertServiceBean getService() {
		return service;
	}

	public void setService(ExpertServiceBean service) {
		this.service = service;
	}

	public long getFromDate() {
		return fromDate;
	}

	public void setFromDate(long fromDate) {
		this.fromDate = fromDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public int getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(int adultNum) {
		this.adultNum = adultNum;
	}

	public int getOldNum() {
		return oldNum;
	}

	public void setOldNum(int oldNum) {
		this.oldNum = oldNum;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
