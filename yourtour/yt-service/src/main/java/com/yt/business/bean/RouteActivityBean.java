package com.yt.business.bean;

import java.util.List;

import com.yt.core.utils.DateUtils;
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
public class RouteActivityBean extends BaseBeanImpl implements Cloneable{
	private static final long serialVersionUID = 6259294378320824143L;
	private static final String INDEX_NAME = "routeActivity";

	@HbaseColumn(name = "title")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String title; // 行程活动名称

	@HbaseColumn(name = "memo")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String memo; // 行程活动备注

	@HbaseColumn(name = "idx")
	private int index = 1; // 行程活动排序号
	
	private long date;

	private String price; //价格信息

	private String currency; //币种

	@HbaseColumn(name = "stm")
	private String startTime = "00:00"; // 行程活动始时间

	@HbaseColumn(name = "etm")
	private String endTime = "00:00"; // 行程活动结束时间

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.INCOMING)
	private transient RouteScheduleBean schedule = null; // 行程活动关联的行程日程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ResourceBean.class, direction = Direction.OUTGOING)
	private transient ResourceBean resource = null; // 行程活动关联的资源

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteServiceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteServiceBean> services = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteActivityItemBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteActivityItemBean> items = null;

	public RouteActivityBean() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime == null ? "00:00":startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime == null?"00:00":endTime;
	}

	public void setEndTime(String endTime) {
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

	public List<RouteServiceBean> getServices() {
		return services;
	}

	public void setServices(List<RouteServiceBean> services) {
		this.services = services;
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

	public List<RouteActivityItemBean> getItems() {
		return items;
	}

	public void setItems(List<RouteActivityItemBean> items) {
		this.items = items;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		RouteActivityBean activity = new RouteActivityBean();

		activity.setTitle(this.getTitle());
		activity.setIndex(this.getIndex());
		activity.setStartTime(this.getStartTime());
		activity.setEndTime(this.getEndTime());
		activity.setUpdatedTime(DateUtils.getCurrentTimeMillis());
		activity.setCreatedTime(DateUtils.getCurrentTimeMillis());
		activity.setResource(this.getResource());

		return activity;
	}
}
