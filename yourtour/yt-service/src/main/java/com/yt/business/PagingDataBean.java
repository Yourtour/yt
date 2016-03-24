package com.yt.business;

public class PagingDataBean<T> {
	private T data = null;
	private int totalPages = 0, currentPageNum = 0;

	public PagingDataBean() {
		super();
	}

	public PagingDataBean(long totalCount, long nextCursor, int limit, T data) {
		this();
		this.calculatePages(totalCount, nextCursor, limit);
		this.setData(data);
	}

	private void calculatePages(long totalCount, long nextCursor, int limit) {
		this.currentPageNum = (int) (nextCursor % limit);
		this.totalPages = (int) (totalCount / limit);
		int mod = (int) (totalCount % limit);
		if (mod > 0) {
			this.totalPages += 1;
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

}
