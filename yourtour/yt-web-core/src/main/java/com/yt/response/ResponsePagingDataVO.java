package com.yt.response;

import com.yt.core.common.StaticErrorEnum;

public class ResponsePagingDataVO<T> extends ResponseDataVO<T> {
	private long total = 0;

	public ResponsePagingDataVO() {
		super();
	}

	public ResponsePagingDataVO(StaticErrorEnum error) {
		this();
		super.setErrorCode(error.errorCode);
		super.setErrorText(error.errorText);
	}

	public ResponsePagingDataVO(long total, T data) {
		super(data);
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

}
