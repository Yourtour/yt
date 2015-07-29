package com.yt.error;

public enum StaticErrorEnum {
	FETCH_DB_DATA_FAIL(3001, "获取数据库数据发生错误。"),
	THE_DATA_NOT_EXIST(3002, "指定的数据不存在。");

	public int errorCode;
	public String errorText;

	private StaticErrorEnum(int errorCode, String errorText) {
		this.errorCode = errorCode;
		this.errorText = errorText;
	}
}
