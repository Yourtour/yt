package com.yt.vo.member;

import java.io.Serializable;

public class LoginVO implements Serializable{
	private static final long serialVersionUID = 7565498288049730405L;
	
	private String mobile;
	private String password;

	public LoginVO() {
		super();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
