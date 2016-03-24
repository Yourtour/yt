package com.yt.business;

public class PagingConditionBean {
	private long nextCursor, total;
	private int limit;

	public PagingConditionBean() {
		super();
	}

	public PagingConditionBean(long nextCursor, int limit, long total) {
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

	public long getTotal() {
		return total;
	}

}
