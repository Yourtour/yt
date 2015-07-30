package com.yt.vo;

import com.yt.error.StaticErrorEnum;

public class ResponseDataVO<T> extends ResponseVO {
	private T data = null;

	public ResponseDataVO() {
		super();
	}

	public ResponseDataVO(T data) {
		this();
		this.data = data;
	}

	public ResponseDataVO(StaticErrorEnum error) {
		this();
		super.setErrorCode(error.errorCode);
		super.setErrorText(error.errorText);
	}

	public ResponseDataVO(int errorCode, String errorText, T data) {
		super(errorCode, errorText);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
