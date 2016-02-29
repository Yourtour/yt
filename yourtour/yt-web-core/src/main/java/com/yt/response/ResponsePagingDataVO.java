package com.yt.response;

import com.yt.error.StaticErrorEnum;

public class ResponsePagingDataVO<T> extends ResponseDataVO<T> {
	private long totalCount = 0;

	public ResponsePagingDataVO() {
		super();
	}

	public ResponsePagingDataVO(StaticErrorEnum error) {
		this();
		super.setErrorCode(error.errorCode);
		super.setErrorText(error.errorText);
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
