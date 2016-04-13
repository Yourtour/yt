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

	private String 	title;			//标题
	private String 	subTitle;		//副标题
	private Long 	startTime;		//开始时间
	private Long 	endTime;		//结束时间
	private String 	imageUrl;		//图片
	private String	brief;			//概述
	private	String  feature;		//特色
	private String 	tags;           //活动标签
	private String  content;		//活动详细描述
	private int		recommendIndex; //推荐指数
	private int     homeRecommend = 0; //是否首页推荐
	private int     placeRecommend = 0; //是否目的地推荐

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteMainBean> routes;

	public ActivityBean() {
		super();
		this.routes = new Vector<RouteMainBean>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getHomeRecommend() {
		return homeRecommend;
	}

	public void setHomeRecommend(int homeRecommend) {
		this.homeRecommend = homeRecommend;
	}

	public int getPlaceRecommend() {
		return placeRecommend;
	}

	public void setPlaceRecommend(int placeRecommend) {
		this.placeRecommend = placeRecommend;
	}

	public int getRecommendIndex() {
		return recommendIndex;
	}

	public void setRecommendIndex(int recommendIndex) {
		this.recommendIndex = recommendIndex;
	}

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteMainBean> routes) {
		this.routes = routes;
	}
}
