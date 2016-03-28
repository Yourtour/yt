package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.core.utils.DateUtils;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

@HbaseTable(name = "T_ROUTE_ACTIVITY_ITEM")
@NodeEntity
public class RouteActivityItemBean extends BaseBeanImpl implements Cloneable{
	private static final long serialVersionUID = 6259294378320824143L;

	@HbaseColumn(name = "title")
	private String title; //

	@HbaseColumn(name = "memo")
	private String memo; // 行程活动备注

	private String imageUrl;

	private String option; //0：可选 1：必须

	private Long   resourceActivityItemId;

	@HbaseColumn(name = "idx")
	private int index = 1; // 行程活动排序号

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteActivityBean.class, direction = Direction.INCOMING)
	private transient RouteActivityBean activity = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ResourceActivityItemBean.class, direction = Direction.OUTGOING)
	private transient ResourceActivityItemBean resourceActivityItem = null;

	public RouteActivityItemBean() {
		super();
	}

	public RouteActivityItemBean(Long itemId) {
		super(itemId);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getResourceActivityItemId() {
		return resourceActivityItemId;
	}

	public void setResourceActivityItemId(Long resourceActivityItemId) {
		this.resourceActivityItemId = resourceActivityItemId;
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

	public RouteActivityBean getActivity() {
		return activity;
	}

	public void setActivity(RouteActivityBean activity) {
		this.activity = activity;
	}

	public ResourceActivityItemBean getResourceActivityItem() {
		return resourceActivityItem;
	}

	public void setResourceActivityItem(ResourceActivityItemBean resourceActivityItem) {
		this.resourceActivityItem = resourceActivityItem;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		RouteActivityItemBean item = new RouteActivityItemBean();

		item.setTitle(this.getTitle());
		item.setIndex(this.getIndex());
		item.setOption(this.getOption());
		item.setImageUrl(this.getImageUrl());
		item.setUpdatedTime(DateUtils.getCurrentTimeMillis());
		item.setCreatedTime(DateUtils.getCurrentTimeMillis());

		return item;
	}
}
