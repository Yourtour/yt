package com.yt.business;

public class PagingDataBean<T> {
	private T data = null;
	private long total = 0;

	public PagingDataBean() {
		super();
	}

	public PagingDataBean(long totalCount, T data) {
		this();
		this.total = totalCount;
		this.setData(data);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
