package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@HbaseTable(name = "T_ROUTE_ACTIVITY")
@NodeEntity
public class RouteActivityBean extends BaseBeanImpl {

	private static final long serialVersionUID = 6259294378320824143L;
	private static final String INDEX_NAME = "routeActivity";

	@HbaseColumn(name = "name")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String name; // 行程活动名称

	@HbaseColumn(name = "memo")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String memo; // 行程活动备注

	@HbaseColumn(name = "idx")
	private int index = 1; // 行程活动排序号

	@HbaseColumn(name = "stm")
	private long startTime = 0; // 行程活动始时间

	@HbaseColumn(name = "etm")
	private long endTime = 0; // 行程活动结束时间

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.INCOMING)
	private transient RouteScheduleBean schedule = null; // 行程活动关联的行程日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ResourceBean.class, direction = Direction.OUTGOING)
	private transient ResourceBean resource = null; // 行程活动关联的资源

	public RouteActivityBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		super.setRowKey(name);
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public RouteScheduleBean getSchedule() {
		return schedule;
	}

	public void setSchedule(RouteScheduleBean schedule) {
		this.schedule = schedule;
	}

	public ResourceBean getResource() {
		return resource;
	}

	public void setResource(ResourceBean resource) {
		this.resource = resource;
	}

}
