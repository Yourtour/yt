package com.yt.vo;

import com.yt.business.BaseBeanImpl;

import java.util.UUID;

public class BaseVO {
	private Long graphId;
	private String rowKey;
	private String createdUserId = "";
	private long createdTime;
	private String updatedUserId = "";
	private long updatedTime;

	public void fromBean(BaseBeanImpl bean) {
		if (bean == null) {
			return;
		}
		setId(bean.getGraphId());
		setCreatedTime(bean.getCreatedTime());
		setCreatedUserId(bean.getCreatedUserId());
		setRowKey(bean.getRowKey());
		setUpdatedTime(bean.getUpdatedTime());
		setUpdatedUserId(bean.getUpdatedUserId());
	}

	public void toBean(BaseBeanImpl bean) {
		bean.setCreatedTime(getCreatedTime());
		bean.setCreatedUserId(getCreatedUserId());
		bean.setGraphId(getId());
		bean.setRowKey(getRowKey());
		bean.setUpdatedTime(getUpdatedTime());
		bean.setUpdatedUserId(getUpdatedUserId());
	}

	public BaseVO() {
		super();
	}

	public Long getId() {
		return graphId;
	}

	public void setId(Long graphid) {
		this.graphId = graphid == 0 ? null : graphid;
	}

	public String getRowKey() {
		return UUID.randomUUID().toString();
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