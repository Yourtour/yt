package com.yt.response;

import com.yt.core.common.StaticErrorEnum;

public class ResponsePagingDataVO<T> extends ResponseDataVO<T> {
	private int sEcho; //先不用
	private int iTotalRecords; //总记录数
	private int iTotalDisplayRecords;  //本次查询记录数

	public ResponsePagingDataVO() {
		super();
	}

	public ResponsePagingDataVO(StaticErrorEnum error) {
		this();
		super.setErrorCode(error.errorCode);
		super.setErrorText(error.errorText);
	}

	public ResponsePagingDataVO(int total, int resultSize, T data) {
		super(data);
		this.iTotalRecords = total;
		this.iTotalDisplayRecords = resultSize;
	}

	public int getsEcho() {
		return sEcho;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	
}
