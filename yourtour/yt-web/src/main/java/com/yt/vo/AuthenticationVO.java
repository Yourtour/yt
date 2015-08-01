package com.yt.vo;

public class AuthenticationVO {
	private String code, password;

	public AuthenticationVO() {
		super();
	}

	public AuthenticationVO(String code, String password) {
		this();
		this.code = code;
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
