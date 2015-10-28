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

@HbaseTable(name = "T_ROUTE_MAIN")
@NodeEntity
public class RouteMainBean extends BaseBeanImpl {

	private static final long serialVersionUID = -2071225440268179136L;
	private static final String INDEX_NAME = "route";

	@HbaseColumn(name = "name")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String name; // 行程名称

	@HbaseColumn(name = "sdt")
	private long startDate = 0; // 行程开始日期

	@HbaseColumn(name = "edt")
	private long endDate = 0; // 行程结束日期

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_FROM, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean fromPlace = null; // 行程出发地点

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteScheduleBean> schedules = null; // 行程包含的日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteProvisionBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteProvisionBean> provisions = null; // 行程包含的准备

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserBean.class, direction = Direction.OUTGOING)
	private transient UserBean owner = null; // 行程所有者

	public RouteMainBean() {
		super();
		schedules = new Vector<RouteScheduleBean>();
		provisions = new Vector<RouteProvisionBean>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		super.setRowKey(name);
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public PlaceBean getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(PlaceBean fromPlace) {
		this.fromPlace = fromPlace;
	}

	public List<RouteScheduleBean> getSchedules() {
		return schedules;
	}

	public List<RouteProvisionBean> getProvisions() {
		return provisions;
	}

	public UserBean getOwner() {
		return owner;
	}

	public void setOwner(UserBean owner) {
		this.owner = owner;
	}

}
