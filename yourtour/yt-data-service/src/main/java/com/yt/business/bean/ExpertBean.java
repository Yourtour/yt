package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.AlongIntentionType;
import com.yt.business.common.Constants.Status;
import com.yt.business.neo4j.repository.CommentTuple;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.List;

/**
 * 该实体定义了系统中的行程的结伴信息。结伴信息和行程以及结伴发布者之间的关系通过图状数据库Neo4j存储
 * 
 * @author Tony.Zhang
 * 
 */
@HbaseTable(name = "T_USER_EXPERT_INFO")
@NodeEntity
public class ExpertBean extends BaseBeanImpl {
	private static final long serialVersionUID = -3433522673262851121L;

	private String  memo;

	private String  tags;
	private String  places;
	private String  result; //审批结果

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_IS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private UserProfileBean profile = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_SERVICE, type = PlaceBean.class, direction = Direction.OUTGOING, isList = true)
	private List<PlaceBean> placeList = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RECOMMEND, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private List<RouteMainBean> recommendRoutes = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = ExpertServiceBean.class, direction = Direction.OUTGOING, isList = true)
	private List<ExpertServiceBean> services = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_COMMENTED, type = CommentBean.class, direction = Direction.OUTGOING, isList = true)
	private List<CommentBean> comments = null;

	//预先规划好的行程
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_EXPERT, type = RouteMainBean.class, direction = Direction.INCOMING, isList = true)
	private List<RouteMainBean> routes = null;

	public ExpertBean() {
		super();
	}

	public List<RouteMainBean> getRecommendRoutes() {
		return recommendRoutes;
	}

	public void setRecommendRoutes(List<RouteMainBean> recommendRoutes) {
		this.recommendRoutes = recommendRoutes;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public List<PlaceBean> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<PlaceBean> placeList) {
		this.placeList = placeList;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<ExpertServiceBean> getServices() {
		return services;
	}

	public void setServices(List<ExpertServiceBean> services) {
		this.services = services;
	}

	public List<CommentBean> getComments() {
		return comments;
	}

	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteMainBean> routes) {
		this.routes = routes;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}