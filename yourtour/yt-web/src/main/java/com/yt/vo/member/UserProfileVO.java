package com.yt.vo.member;

import java.io.Serializable;

import com.yt.business.bean.UserProfileBean;
import com.yt.core.utils.DateUtils;

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

	public String getExpert(){
		return bean.getExpert();
	}

	public String getNickName(){
		return bean.getNickName();
	}

	public String getName(){return bean.getName();}

	public String getGender(){
		return bean.getGender().name;
	}

	public String getBirthday(){
		return DateUtils.formatDate(bean.getBirthday());
	}
}
