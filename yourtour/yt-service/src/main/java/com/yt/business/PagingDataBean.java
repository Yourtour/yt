package com.yt.business;

public class PagingDataBean<T> {
	private T data = null;
	private int total = 0;

	public PagingDataBean() {
		super();
	}

	public PagingDataBean(int totalCount, T data) {
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
