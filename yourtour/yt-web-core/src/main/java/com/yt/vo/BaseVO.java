package com.yt.vo;

import com.yt.business.BaseBeanImpl;

public class BaseVO {
	private Long id;
	private String rowKey;
	private Long createdUserId = 0l;
	private long createdTime;
	private Long updatedUserId = 0l;
	private long updatedTime;

	public void fromBean(BaseBeanImpl bean) {
		if (bean == null) {
			return;
		}
		setId(bean.getId());
		setCreatedTime(bean.getCreatedTime());
		setCreatedUserId(bean.getCreatedUserId());
		setRowKey(bean.getRowKey());
		setUpdatedTime(bean.getUpdatedTime());
		setUpdatedUserId(bean.getUpdatedUserId());
	}

	public void toBean(BaseBeanImpl bean) {
		bean.setCreatedTime(getCreatedTime());
		bean.setCreatedUserId(getCreatedUserId());
		bean.setId(getId() == null || getId() == -1 ? null : getId());
		bean.setRowKey(getRowKey());
		bean.setUpdatedTime(getUpdatedTime());
		bean.setUpdatedUserId(getUpdatedUserId());
	}

	public BaseVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id == 0 ? null : id;
	}

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}
}