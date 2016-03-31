package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.core.utils.DateUtils;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

@HbaseTable(name = "T_ROUTE_SCHEDULE_ITEM")
@NodeEntity
public class RouteScheduleItemBean extends BaseBeanImpl implements Cloneable{
	private static final long serialVersionUID = 6259294378320824143L;

	@HbaseColumn(name = "title")
	private String title; //

	@HbaseColumn(name = "memo")
	private String memo; // 行程活动备注

	private String imageUrl;

	private String option; //0：可选 1：必须

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleBean.class, direction = Direction.INCOMING)
	private transient RouteScheduleBean schedule = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ResourceActivityItemBean.class, direction = Direction.OUTGOING)
	private transient ResourceActivityItemBean resourceActivityItem = null;

	public RouteScheduleItemBean() {
		super();
	}

	public RouteScheduleItemBean(Long itemId) {
		super(itemId);
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

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public RouteScheduleBean getSchedule() {
		return schedule;
	}

	public void setSchedule(RouteScheduleBean schedule) {
		this.schedule = schedule;
	}

	public ResourceActivityItemBean getResourceActivityItem() {
		return resourceActivityItem;
	}

	public void setResourceActivityItem(ResourceActivityItemBean resourceActivityItem) {
		this.resourceActivityItem = resourceActivityItem;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		RouteScheduleItemBean item = new RouteScheduleItemBean();

		item.setTitle(this.getTitle());
		item.setOption(this.getOption());
		item.setImageUrl(this.getImageUrl());
		item.setUpdatedTime(DateUtils.getCurrentTimeMillis());
		item.setCreatedTime(DateUtils.getCurrentTimeMillis());

		return item;
	}
}
