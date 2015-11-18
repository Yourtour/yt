package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@HbaseTable(name = "T_ROUTE_SCHEDULE")
@NodeEntity
public class RouteScheduleBean extends BaseBeanImpl {

	private static final long serialVersionUID = 8074543232974381934L;
	private static final String INDEX_NAME = "routeSchedule";

	@HbaseColumn(name = "idx")
	private int index = 1; // 行程日程排序号

	@HbaseColumn(name = "dt")
	private long date = 0; // 行程日程日期
	
	private int days = 1;
	
	@HbaseColumn(name = "desc")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String description; // 行程日程描述

	private String places;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean routeMain = null; // 行程日程关联的行程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteActivityBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteActivityBean> activities = null; // 行程日程包含的行程活动

	public RouteScheduleBean() {
		super();
		activities = new Vector<RouteActivityBean>();
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RouteMainBean getRouteMain() {
		return routeMain;
	}

	public void setRouteMain(RouteMainBean routeMain) {
		this.routeMain = routeMain;
	}

	public List<RouteActivityBean> getActivities() {
		return activities;
	}

}
