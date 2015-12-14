package com.yt.vo.route;

import java.io.Serializable;

import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.core.utils.StringUtils;

public class RouteSettingVO implements Serializable{
	private static final long serialVersionUID = 2714649904252429482L;
	private Long userId;
	private Long routeId;
	private String attr; 
	private String attrValue;

	public RouteSettingVO() {
		super();
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

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
}
