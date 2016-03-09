package com.yt.business.bean;

import com.yt.business.BusinessBeanImpl;
import com.yt.business.CommentBaseBean;
import com.yt.hbase.annotation.HbaseTable;
import org.springframework.data.neo4j.annotation.NodeEntity;

@HbaseTable(name = "T_COMMENT_INFO")
@NodeEntity
public class FavoriteBean extends BusinessBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;

	private Long    parentId = 0l;
	private double 	score = 0d;
	private double   healthScore = 0d;
	private double   trafficScore = 0d;
	private	double	facilityScore = 0d;
	private double	environmentScore = 0d;
	private String 	memo;
	private String 	imageUrl; //多张图片使用逗号分隔
	private String	replied = "";
	private String	tags;

	private int recommendedIndex = 0; //-1 不推荐 0：待改善 1：推荐

	private transient UserProfileBean user;

	private transient CommentBaseBean entity;

	public FavoriteBean() {
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(double healthScore) {
		this.healthScore = healthScore;
	}

	public double getTrafficScore() {
		return trafficScore;
	}

	public void setTrafficScore(double trafficScore) {
		this.trafficScore = trafficScore;
	}

	public double getFacilityScore() {
		return facilityScore;
	}

	public void setFacilityScore(double facilityScore) {
		this.facilityScore = facilityScore;
	}

	public double getEnvironmentScore() {
		return environmentScore;
	}

	public void setEnvironmentScore(double environmentScore) {
		this.environmentScore = environmentScore;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getReplied() {
		return replied;
	}

	public void setReplied(String replied) {
		this.replied = replied;
	}

	public int getRecommendedIndex() {
		return recommendedIndex;
	}

	public void setRecommendedIndex(int recommendedIndex) {
		this.recommendedIndex = recommendedIndex;
	}

	public CommentBaseBean getEntity() {
		return entity;
	}

	public void setEntity(CommentBaseBean entity) {
		this.entity = entity;
	}
}
