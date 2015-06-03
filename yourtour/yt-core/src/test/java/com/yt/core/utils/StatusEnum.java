package com.yt.core.utils;

public enum StatusEnum {
	DRAFT("Draft", "草稿"), ACTIVED("Actived", "激活"), CANCELED("Canceled", "取消"), CLOSED("Closed", "关闭");

	public String code, name;

	private StatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
}