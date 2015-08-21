package com.yt.error;

public enum StaticErrorEnum {
	AUTHENTICATE_FAIL(1001, "用户认证失败，请检查登录账户和密码。"), USER_NOT_EXIST(1002,
			"指定的用户不存在。"), FETCH_DB_DATA_FAIL(3001, "获取数据库数据发生错误。"), THE_DATA_NOT_EXIST(
			3002, "指定的数据不存在。"), THE_INPUT_IS_NULL(3003, "输入的数据对象为空。"), DB_OPERATE_FAIL(
			3004, "操作数据库发生错误。");

	public int errorCode;
	public String errorText;

	private StaticErrorEnum(int errorCode, String errorText) {
		this.errorCode = errorCode;
		this.errorText = errorText;
	}
}
