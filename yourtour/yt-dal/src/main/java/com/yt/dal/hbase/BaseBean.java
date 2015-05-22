package com.yt.dal.hbase;

import java.io.Serializable;

public abstract class BaseBean implements Serializable {
	private static final long serialVersionUID = -1098345715801304322L;
	private String rowKey;
	private long timestamp;
	private Object value;

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
