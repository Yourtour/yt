package com.yt.vo.member;

import com.yt.vo.BaseVO;

public class RegisterVO extends BaseVO {
	private String mobile;
	private String authcode;
	private String password;

	public RegisterVO() {
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

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
}
