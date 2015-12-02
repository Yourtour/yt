package com.yt.vo.member;

import java.io.Serializable;

import com.yt.business.bean.UserProfileBean;

public class UserProfileVO implements Serializable{
	private static final long serialVersionUID = 7565498288049730405L;
	
	private UserProfileBean bean;
	
	public UserProfileVO(UserProfileBean bean) {
		this.bean = bean;
	}
	
	public String getMobile(){
		return bean.getMobileNo();
	}
	
	public String getId(){
		return bean.getGraphId().toString();
	}
}