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
public class ExpertBean extends UserProfileBean {
	private static final long serialVersionUID = -3433522673262851121L;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private PlaceBean place = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RECOMMEND, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private List<RouteMainBean> recommendRoutes = null;

	public ExpertBean() {
		super();
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public List<RouteMainBean> getRecommendRoutes() {
		return recommendRoutes;
	}

	public void setRecommendRoutes(List<RouteMainBean> recommendRoutes) {
		this.recommendRoutes = recommendRoutes;
	}
}