package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 达人信息对象
 * 
 * @author Tony.Zhang
 * 
 */
@HbaseTable(name = "T_EXPERT_INFO")
@NodeEntity
public class ExpertBean extends BaseBeanImpl {
	private static final long serialVersionUID = -3433522673262851121L;

	public enum Status {
		APPLICATION, // 申请
		APPROVING, // 审批中
		APPROVED_PASS, // 审批通过
		APPROVED_NOT_PASS, // 审批不通过
		FROZEN // 冻结
	}

	private String result; // 审批结果内容
	private Status status = Status.APPLICATION; // 达人状态

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_SERVICE, type = PlaceBean.class, direction = Direction.INCOMING)
	private transient List<PlaceBean> places; // 达人服务的目的地
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_IS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean profile = null; // 达人用户Profile信息
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ExpertServiceBean.class, direction = Direction.OUTGOING)
	private transient List<ExpertServiceBean> services = null; // 达人服务列表
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.OUTGOING)
	private transient List<RouteMainBean> routes = null; // 推荐行程清单

	public ExpertBean() {
		super();
		this.places = new Vector<PlaceBean>();
		this.services = new Vector<ExpertServiceBean>();
		this.routes = new Vector<RouteMainBean>();
	}

	public List<PlaceBean> getPlaces() {
		return places;
	}

	public List<ExpertServiceBean> getServices() {
		return services;
	}

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

	public UserProfileBean getProfile() {
		return profile;
	}

	public void setProfile(UserProfileBean profile) {
		this.profile = profile;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}