package com.yt.vo;

import com.yt.error.StaticErrorEnum;

public class ResponseVO {
	private int errorCode = 0;
	private String errorText = "";

	public ResponseVO() {
		super();
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
}
