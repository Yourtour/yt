package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.yt.business.common.Constants.GroupRole;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 行程成员bean，定义了和行程相关的各种成员信息
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年6月28日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j操作进行了修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_ROUTE_MEMBER_INFO")
@NodeEntity
public class RouteMemberBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -8980153602025087935L;

	private @HbaseColumn(name = "rid")
	@RelatedTo(type = "route", direction = Direction.BOTH, elementClass = RouteBean.class)
	RouteBean route; // 行程ID
	private @HbaseColumn(name = "uid")
	@RelatedTo(type = "user", direction = Direction.BOTH, elementClass = UserBean.class)
	UserBean user; // 用户ID
	private @HbaseColumn(name = "role")
	GroupRole role; // 角色
	private @HbaseColumn(name = "pos")
	String position; // 位置信息

	private @HbaseColumn(name = "cuid")
	transient String createdUserId = "";
	private @HbaseColumn(name = "ct")
	transient long createdTime;
	private @HbaseColumn(name = "uuid")
	transient String updatedUserId = "";
	private @HbaseColumn(name = "ut")
	transient long updatedTime;
	private @HbaseColumn(name = "stat")
	@Indexed
	Status status;

	public RouteMemberBean() {
		super();
	}

	public RouteBean getRoute() {
		return route;
	}

	public void setRoute(RouteBean route) {
		this.route = route;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public GroupRole getRole() {
		return role;
	}

	public void setRole(GroupRole role) {
		this.role = role;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
