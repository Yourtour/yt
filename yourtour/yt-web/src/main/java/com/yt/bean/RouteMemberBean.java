package com.yt.bean;

import com.yt.common.Constants.RoleType;
import com.yt.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * @author Tony.Zhang
 * 
 * 行程成员bean，定义了行程成员信息
 *
 */
@HbaseTable(name = "T_ROUTE_MEMBER_INFO")
public class RouteMemberBean extends BaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	
	public static enum ROLE{LEADER, MEMBER, EXPERT, HOST}
	
	private 	@HbaseColumn(name = "rid")				String 	routeId; 	//行程ID
	private 	@HbaseColumn(name = "uid")			String 	userId; 	//用户ID
	private 	@HbaseColumn(name = "role")			RoleType 	role; 	//角色 
	private 	@HbaseColumn(name = "cuid")			String createdUserId = "";
	private 	@HbaseColumn(name = "ct")				long createdTime;
	private 	@HbaseColumn(name = "uuid")			String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")				long updatedTime;
	private 	@HbaseColumn(name = "stat")			Status	status;
	
	public RouteMemberBean() {
		super();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
