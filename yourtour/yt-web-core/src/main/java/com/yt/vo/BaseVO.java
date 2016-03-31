package com.yt.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.business.BaseBeanImpl;

public class BaseVO {
	private static final Log LOG = LogFactory.getLog(BaseVO.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private Long id;
	private String rowKey;
	private Long createdUserId = 0l;
	private String createdTime;
	private Long updatedUserId = 0l;
	private String updatedTime;

	public void fromBean(BaseBeanImpl bean) {
		if (bean == null) {
			return;
		}
		setId(bean.getId());
		setCreatedTime(sdf.format(new Date(bean.getCreatedTime())));
		setCreatedUserId(bean.getCreatedUserId());
		setRowKey(bean.getRowKey());
		setUpdatedTime(sdf.format(new Date(bean.getUpdatedTime())));
		setUpdatedUserId(bean.getUpdatedUserId());
	}

	public void toBean(BaseBeanImpl bean) {
		try {
			bean.setCreatedTime(sdf.parse(getCreatedTime()).getTime());
		} catch (Exception ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("Parse the date time fail, src: %s.",
						getCreatedTime()), ex);
			}
		}
		bean.setCreatedUserId(getCreatedUserId());
		bean.setId(getId() == null || getId() == -1 ? null : getId());
		bean.setRowKey(getRowKey());
		try {
			bean.setUpdatedTime(sdf.parse(getUpdatedTime()).getTime());
		} catch (Exception ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("Parse the date time fail, src: %s.",
						getUpdatedTime()), ex);
			}
		}
		bean.setUpdatedUserId(getUpdatedUserId());
	}

	public BaseVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = (id == null || id == -1l ? null : id);
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}