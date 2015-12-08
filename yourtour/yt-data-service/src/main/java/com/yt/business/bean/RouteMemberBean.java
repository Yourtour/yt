package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
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
@JsonRootName("route")
public class RouteMemberBean extends BaseBeanImpl {

	private static final long serialVersionUID = -2071225440268179136L;
	private static final String INDEX_NAME = "route";

	@HbaseColumn(name = "name")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String name; // 行程名称
	
	private String lineName;
	
	@HbaseColumn(name = "sdt")
	private long startDate = 0; // 行程开始日期
	
	private int duration;

	private String imageUrl;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_FROM, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean fromPlace = null; // 行程出发地点
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_TO, type = PlaceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<PlaceBean> destinations = null; //目的地

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteScheduleBean> schedules = null; // 行程包含的日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteProvisionBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteProvisionBean> provisions = null; // 行程包含的准备

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean owner = null; // 行程所有者

	public RouteMemberBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public PlaceBean getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(PlaceBean fromPlace) {
		this.fromPlace = fromPlace;
	}

	public List<PlaceBean> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<PlaceBean> destinations) {
		this.destinations = destinations;
	}

	public List<RouteScheduleBean> getSchedules() {
		if(schedules == null) schedules = new ArrayList<>();
		
		return schedules;
	}

	public List<RouteProvisionBean> getProvisions() {
		if(provisions == null) provisions = new ArrayList<>();
		
		return provisions;
	}

	public UserProfileBean getOwner() {
		return owner;
	}

	public void setOwner(UserProfileBean owner) {
		this.owner = owner;
	}

}
