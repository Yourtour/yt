package com.yt.vo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.yt.business.PagingConditionBean;

public class PagingConditionVO {

	@DefaultValue("0")
	@QueryParam("nextCursor")
	private long nextCursor;

	@DefaultValue("20")
	// 默认每页20行数据
	@QueryParam("limit")
	private int limit;

	@DefaultValue("0")
	@QueryParam("totalPages")
	private int totalPages;

	public PagingConditionVO() {
		super();
	}

	public PagingConditionBean getCondition() {
		return new PagingConditionBean(nextCursor, limit, totalPages);
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(long nextCursor) {
		this.nextCursor = nextCursor;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
