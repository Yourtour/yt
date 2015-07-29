package com.yt.vo;

import com.yt.error.StaticErrorEnum;

public class ResponseVO<T> {
	private int errorCode = 0;
	private String errorText = "";
	private T data = null;

	public ResponseVO() {
		super();
	}

	public ResponseVO(T data) {
		this();
		this.data = data;
	}

	public ResponseVO(StaticErrorEnum error) {
		this();
		this.errorCode = error.errorCode;
		this.errorText = error.errorText;
	}

	public ResponseVO(int errorCode, String errorText) {
		this();
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	public ResponseVO(int errorCode, String errorText, T data) {
		this(errorCode, errorText);
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
