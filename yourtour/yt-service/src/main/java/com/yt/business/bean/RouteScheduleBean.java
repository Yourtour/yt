package com.yt.business.bean;

import java.util.List;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

@HbaseTable(name = "T_ROUTE_SCHEDULE")
@NodeEntity
public class RouteScheduleBean extends BaseBeanImpl {
	private static final long serialVersionUID = 8074543232974381934L;
	private static final String INDEX_NAME = "routeSchedule";

	private String 	title;

	private Long	parentId;

	@HbaseColumn(name = "idx")
	private long index = 1; // 行程日程排序号

	@HbaseColumn(name = "dt")
	private Long date = 0l; // 行程日程日期

	@HbaseColumn(name = "stm")
	private String startTime = "00:00"; // 行程活动始时间

	@HbaseColumn(name = "etm")
	private String endTime = "00:00"; // 行程活动结束时间

	private String price; //价格信息
	private String currency; //币种

	@HbaseColumn(name = "desc")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String memo; // 日程描述

	private String places; //目的地

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ResourceBean.class, direction = Direction.OUTGOING)
	private transient ResourceBean resource = null; // 行程活动关联的资源

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleItemBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteScheduleItemBean> items = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean routeMain = null; // 行程日程关联的行程

	public RouteScheduleBean() {
		super();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public RouteMainBean getRouteMain() {
		return routeMain;
	}

	public void setRouteMain(RouteMainBean routeMain) {
		this.routeMain = routeMain;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ResourceBean getResource() {
		return resource;
	}

	public void setResource(ResourceBean resource) {
		this.resource = resource;
	}

	public List<RouteScheduleItemBean> getItems() {
		return items;
	}

	public void setItems(List<RouteScheduleItemBean> items) {
		this.items = items;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}
}
