package com.yt.response;

import com.yt.core.common.StaticErrorEnum;

public class ResponseVO {
	private String errorCode = "0";
	private String errorText = "";

	public ResponseVO() {
		super();
	}

	public ResponseVO(StaticErrorEnum error) {
		this();
		this.errorCode = error.errorCode;
		this.errorText = error.errorText;
	}

	public ResponseVO(String errorCode, String errorText) {
		this();
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
