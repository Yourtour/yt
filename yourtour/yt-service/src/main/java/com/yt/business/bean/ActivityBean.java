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
 * 系统发布的活动信息，包括：平台活动和达人活动等。
 * 
 * Created by John.Peng on 2016/3/12.
 */
@HbaseTable(name = "T_ACTIVITY_INFO")
@NodeEntity
public class ActivityBean extends BaseBeanImpl {
	private static final long serialVersionUID = -5019182758425160992L;

	public enum Status {
		DRAFT, // 草稿
		APPROVED_PASS, // 审核通过
		APPROVED_NOT_PASS, // 审核不通过
		PENDDING, // 排队中
		RELEASED, // 发布
		CLOSED // 关闭
	}

	private String imageUrl;
	private Status status = Status.DRAFT;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean place;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteMainBean> routes;

	public ActivityBean() {
		super();
		this.routes = new Vector<RouteMainBean>();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

}
