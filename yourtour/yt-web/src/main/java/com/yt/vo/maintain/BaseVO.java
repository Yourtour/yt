package com.yt.vo.maintain;

import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

public class BaseVO {

	private Long graphid;
	private String rowKey;
	private String createdUserId = "";
	private long createdTime;
	private String updatedUserId = "";
	private long updatedTime;

	public void fromBean(Neo4JBaseBean bean) {
		if (bean == null) {
			return;
		}
		setGraphid(bean.getGraphId());
		setCreatedTime(bean.getCreatedTime());
		setCreatedUserId(bean.getCreatedUserId());
		setRowKey(bean.getRowKey());
		setUpdatedTime(bean.getUpdatedTime());
		setUpdatedUserId(bean.getUpdatedUserId());
	}

	public void toBean(Neo4JBaseBean bean) {
		bean.setCreatedTime(getCreatedTime());
		bean.setCreatedUserId(getCreatedUserId());
		bean.setGraphId(getGraphid());
		bean.setRowKey(getRowKey());
		bean.setUpdatedTime(getUpdatedTime());
		bean.setUpdatedUserId(getUpdatedUserId());
	}

	public BaseVO() {
		super();
	}

	public Long getGraphid() {
		return graphid;
	}

	public void setGraphid(Long graphid) {
		this.graphid = graphid;
	}

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

}