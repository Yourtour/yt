package com.yt.vo;

import java.util.HashSet;
import java.util.Set;

import com.yt.business.bean.LineBean;

public class LineVO extends LineBean {
	private static final long serialVersionUID = 1L;

	Set<UserVO> users = new HashSet<UserVO>();
	Set<ResourceVO> resources = new HashSet<ResourceVO>();
	
	public LineVO() {
	}

	public Set<UserVO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserVO> users) {
		this.users = users;
	}

	public Set<ResourceVO> getResources() {
		return resources;
	}

	public void setResources(Set<ResourceVO> resources) {
		this.resources = resources;
	}

}
