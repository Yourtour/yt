package com.yt.core.common;

public enum StaticErrorEnum {
    AUTHENTICATE_FAIL("1001", "用户认证失败，请检查登录账户和密码。"),
    USER_NOT_EXIST("1002", "指定的用户不存在。"),
    USER_EXIST("1003", "指定的账号已经存在。"),
    NICKNAME_EXIST("1004", "指定的昵称已经存在。"),
    USER_MOBILE_EXIST("1005", "手机号已经注册，不能重复注册。"),

    FETCH_DB_DATA_FAIL("3001", "获取数据库数据发生错误。"),
    DATA_NOT_EXIST("3002", "指定的数据不存在。"),
    INPUT_IS_NULL("3003", "输入的数据对象为空。"),
    DB_OPERATE_FAIL("3004", "操作数据库发生错误。"),
    DB_OPERATE_PERMISSION_FAIL("3005", "数据操作权限异常."),

    BUSINESS_ORDER_NOT_EDITABLE("4001", "服务已完成支付，不能再修改。");

    public String errorCode;
    public String errorText;

    private StaticErrorEnum(String errorCode, String errorText) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }
}
