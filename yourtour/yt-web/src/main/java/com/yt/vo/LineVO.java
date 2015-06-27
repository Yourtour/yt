package com.yt.vo;

import java.util.ArrayList;
import java.util.List;

import com.yt.bean.LineBean;

public class LineVO extends LineBean {
	List<UserVO> users = new ArrayList<UserVO>();
	
	public LineVO() {
		// TODO Auto-generated constructor stub
	}

	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

}
