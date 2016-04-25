package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.SocialBeanImpl;
import com.yt.business.common.Constants;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 平台中和内容相关的实体的基础类
 */
@NodeEntity
public class ContentBean extends SocialBeanImpl {
	private static final long serialVersionUID = -5019182758425160992L;

	public static final String RELATION_TYPE_PROVIDE = "PROVIDE";

	// 内容状态
	public enum Status {
		DRAFT, // 草稿
		APPROVED_PASS, // 审核通过
		APPROVED_NOT_PASS, // 审核不通过
		PENDDING, // 排队中
		RELEASED, // 发布
		CLOSED // 关闭
	}

	// 内容分类
	public enum ContentCategory {
		DISCOVER, // 发现
		YT_RECOMMEND, // 游徒推荐
		ACTIVITY // 活动
	}
	
	private String title; // 标题
	private String subTitle; // 副标题
	private String imageUrl; // 图片
	private String brief; // 概述
	private String feature; // 特色
	private String placeInfo; // 目的地(冗余字段)
	private String tagInfo; // 标签
	private String userInfo; // 内容提供者信息(冗余字段)
	private String content; // 活动详细描述
	private int homeRecommend = 0; // 是否首页推荐
	private int placeRecommend = 0; // 是否目的地推荐

	private Status status = Status.DRAFT;

	private ContentCategory category = ContentCategory.YT_RECOMMEND;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = RouteMainBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteMainBean> routes; // 和内容相关的行程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<PlaceBean> placeList; // 和内容相关的目的地

	@Neo4jRelationship(relationship = RELATION_TYPE_PROVIDE, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean user; // 内容提供者

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = DictBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<DictBean> tagLists; // 标签

	public ContentBean() {
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

	public String getPlaceInfo() {
		return placeInfo;
	}

	public void setPlaceInfo(String placeInfo) {
		this.placeInfo = placeInfo;
	}

	public String getTagInfo() {
		return tagInfo;
	}

	public void setTagInfo(String tagInfo) {
		this.tagInfo = tagInfo;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
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

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteMainBean> routes) {
		this.routes = routes;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ContentCategory getCategory() {
		return category;
	}

	public void setCategory(ContentCategory category) {
		this.category = category;
	}

	public List<PlaceBean> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<PlaceBean> placeList) {
		this.placeList = placeList;
	}

	public List<DictBean> getTagLists() {
		return tagLists;
	}

	public void setTagLists(List<DictBean> tagLists) {
		this.tagLists = tagLists;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}
