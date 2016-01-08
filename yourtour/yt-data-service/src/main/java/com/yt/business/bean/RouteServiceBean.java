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
public class RouteServiceBean extends ExpertServiceBean {
	private static final long serialVersionUID = 6259294378320824143L;

	private Long  expertServiceId;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ExpertServiceBean.class, direction = Direction.OUTGOING)
	private transient ExpertServiceBean service = null; // 行程活动关联的行程日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean route = null; // 行程活动关联的行程日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.INCOMING)
	private transient RouteScheduleBean schedule = null; // 行程活动关联的行程日程
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteActivityBean.class, direction = Direction.INCOMING)
	private transient RouteActivityBean activity = null; // 行程活动关联的行程日程

	public RouteServiceBean() {
		super();
	}

	public RouteServiceBean(String userId) {
		super(userId);
	}

	public Long getExpertServiceId() {
		return expertServiceId;
	}

	public void setExpertServiceId(Long expertServiceId) {
		this.expertServiceId = expertServiceId;
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}

	public RouteScheduleBean getSchedule() {
		return schedule;
	}

	public void setSchedule(RouteScheduleBean schedule) {
		this.schedule = schedule;
	}

	public RouteActivityBean getActivity() {
		return activity;
	}

	public void setActivity(RouteActivityBean activity) {
		this.activity = activity;
	}

	public ExpertServiceBean getService() {
		return service;
	}

	public void setService(ExpertServiceBean service) {
		this.service = service;
	}
}
