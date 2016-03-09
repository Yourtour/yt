package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;

public class RouteMemberBean extends BaseBeanImpl {
	private static final long serialVersionUID = -2071225440268179136L;

	private String role;
	private String permission;
	private String imageUrl;
	private String impression;

	private UserProfileBean user;

	public RouteMemberBean() {
		super();
	}

	public RouteMemberBean(Long id) {
		super(id);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}
}
