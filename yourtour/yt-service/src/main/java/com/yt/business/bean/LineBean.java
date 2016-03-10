package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 
 * 该实体定义了系统中的线路信息。线路相关的景点之间的关系通过图状数据库Neo4j存储
 * 
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
 * <td>2015年7月28日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j的操作模式进行修改完善。</td>
 * </tr>
 * <tr>
 * <td>2015年9月17日</td>
 * <td>John.Peng</td>
 * <td>删除了place（目的地）属性，采用line跟place之间的关联关系来表示。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_LINE_INFO")
@NodeEntity
public class LineBean extends SocialBeanImpl {
	private static final long serialVersionUID = -3433522673262851121L;
	private static final String INDEX_NAME = "line";

	private String name;

	@HbaseColumn(name = "img")
	private String imageUrl; // 图片

	@HbaseColumn(name = "intr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String intro; // 概述， 线路进行简单介绍

	@HbaseColumn(name = "feat")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String feature; // 特点， 对线路特点进行简单描述

	@HbaseColumn(name = "reas")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String reason; // 推荐理由，描述推荐理由

	@HbaseColumn(name = "rind")
	private double recommendIndex; // 推荐指数

	@HbaseColumn(name = "cind")
	private double commentIndex; // 点评指数

	@HbaseColumn(name = "anum")
	private int arriveNum; // 到达人数

	@HbaseColumn(name = "tags")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String tags; // 标签

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean place = null;
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_CONTAIN, type = SceneResourceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<SceneResourceBean> scenes = null;
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RECOMMEND, type = ExpertBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<ExpertBean> experts = null;
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RECOMMEND, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteMainBean> routes = null;

	public LineBean() {
		super();
		scenes = new Vector<SceneResourceBean>();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getRecommendIndex() {
		return recommendIndex;
	}

	public void setRecommendIndex(double recommendIndex) {
		this.recommendIndex = recommendIndex;
	}

	public double getCommentIndex() {
		return commentIndex;
	}

	public void setCommentIndex(double commentIndex) {
		this.commentIndex = commentIndex;
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public List<SceneResourceBean> getScenes() {
		return scenes;
	}

	public int getArriveNum() {
		return arriveNum;
	}

	public void setArriveNum(int arriveNum) {
		this.arriveNum = arriveNum;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
