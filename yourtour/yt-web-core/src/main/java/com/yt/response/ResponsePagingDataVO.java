package com.yt.response;

import com.yt.core.common.StaticErrorEnum;

public class ResponsePagingDataVO<T> extends ResponseDataVO<T> {
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
