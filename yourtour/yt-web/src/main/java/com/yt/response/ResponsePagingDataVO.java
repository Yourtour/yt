package com.yt.response;

public class ResponsePagingDataVO<T> extends ResponseDataVO<T> {
	private long totalCount = 0;

	public ResponsePagingDataVO() {
		super();
	}

	public ResponsePagingDataVO(long totalCount, T data) {
		super(data);
		this.totalCount = totalCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
