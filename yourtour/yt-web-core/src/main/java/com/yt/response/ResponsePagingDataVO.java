package com.yt.response;

import com.yt.core.common.StaticErrorEnum;

import java.util.List;

public class ResponsePagingDataVO<T> extends ResponseDataVO<T> {
	private int sEcho; //先不用
	private int iTotalRecords; //总记录数
	private int iTotalDisplayRecords;  //本次查询记录数
	private List<T> data;  //结果集

	private int totalPages = 0, currentPageNum = 0;

	public ResponsePagingDataVO() {
		super();
	}

	public ResponsePagingDataVO(StaticErrorEnum error) {
		this();
		super.setErrorCode(error.errorCode);
		super.setErrorText(error.errorText);
	}

	public ResponsePagingDataVO(int totalPages, int currentPageNum, T data) {
		super(data);
		this.totalPages = totalPages;
		this.currentPageNum = currentPageNum;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

}
