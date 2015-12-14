package com.yt.vo.route;

import java.io.Serializable;

import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.core.utils.StringUtils;

public class RouteMemberVO implements Serializable{
	private static final long serialVersionUID = 2714649904252429482L;
	private Long id;
	private Long userId;
	private String nickName;
	private String imageUrl;	
	private Long routeId;
	private String role; // 线路名称

	public RouteMemberVO() {
		super();
	}
	
	public RouteMemberVO(UserProfileBean profile) {
		super();
		
		this.id = profile.getGraphId();
		this.nickName = profile.getNickName();
		this.imageUrl = profile.getImageUrl();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getRole() {
		return StringUtils.isNull(role)?Constants.GroupRole.MEMBER.toString():role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
