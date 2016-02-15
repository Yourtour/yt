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

	private long 	useDate;
	private int 	memberNum;
	private String	address;
	private String	memo;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ExpertServiceBean.class, direction = Direction.OUTGOING)
	private transient ExpertServiceBean service = null; // 行程活动关联的行程日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean route = null; // 行程活动关联的行程日程

	public RouteServiceBean() {
		super();
	}

	public RouteServiceBean(String userId) {
		super(userId);
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

	public long getUseDate() {
		return useDate;
	}

	public void setUseDate(long useDate) {
		this.useDate = useDate;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
