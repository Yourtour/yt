package com.yt.vo.route;

import java.io.Serializable;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.RouteMemberBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.core.utils.StringUtils;
import com.yt.vo.BaseVO;
import com.yt.vo.member.UserVO;

public class RouteMemberVO extends BaseVO{
	private static final long serialVersionUID = 2714649904252429482L;
	private String role; // 线路名称
	private UserVO profile;

	public static RouteMemberBean transform(RouteMemberVO vo){
		RouteMemberBean bean = new RouteMemberBean();

		return bean;
	}


	public static RouteMemberVO transform(RouteMemberBean bean){
		RouteMemberVO vo = new RouteMemberVO();

		vo.fromBean(bean);
		vo.setRole(bean.getRole());

		UserProfileBean profile = bean.getProfile();
		vo.setProfile(UserVO.transform(profile));

		return vo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserVO getProfile() {
		return profile;
	}

	public void setProfile(UserVO profile) {
		this.profile = profile;
	}
}
