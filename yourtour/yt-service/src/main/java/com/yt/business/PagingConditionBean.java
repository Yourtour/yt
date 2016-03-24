package com.yt.business;

public class PagingConditionBean {
	private long nextCursor;
	private int limit, total;

	public PagingConditionBean() {
		super();
	}

	public PagingConditionBean(long nextCursor, int limit, int total) {
		this();
		this.nextCursor = nextCursor;
		this.limit = limit;
		this.total = total;
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public int getLimit() {
		return limit;
	}

	public int getTotal() {
		return total;
	}

}
