package com.yt.vo.member;

public class AuthenticationVO {
	private String id, code, password;

	public AuthenticationVO() {
		super();
	}

	public AuthenticationVO(String id, String code, String password) {
		this();
		this.id = id;
		this.code = code;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
