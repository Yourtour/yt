package com.yt.common;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
	private static final long serialVersionUID = -1930398421590088096L;

	private 	String 		errorCode;
	private 	String 		errorMsg;
	private 	Object 	data;
	
	public static 	final String 	SUCCESS = "0000";
	public static	final	String	EXCEPTION = "9999";
	
	public 		ResponseMessage(String errorCode){
		this(errorCode, "");
	}
	
	public 		ResponseMessage(String errorCode, String errorMsg){
		this(errorCode, errorMsg, null);
	}
	
	public 		ResponseMessage(String errorCode, Object data){
		this(errorCode, "", data);
	}
	
	public 		ResponseMessage(String errorCode, String errorMsg, Object data){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
