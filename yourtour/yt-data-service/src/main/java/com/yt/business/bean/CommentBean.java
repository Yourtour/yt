package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.BaseBean;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseDictBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

import java.util.List;

@HbaseTable(name = "T_COMMENT_INFO")
@NodeEntity
public class CommentBean extends BaseBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;

	private double 	score = 0d;
	private double   healthScore = 0d;
	private double   trafficScore = 0d;
	private	double	facilityScore = 0d;
	private double	environmentScore = 0d;
	private String 	memo;
	private String 	imageUrls;
	private int		thumbup = 0;
	private String	replied = "";
	private String	tags;

	private int recommendedIndex = 0; //-1 不推荐 0：待改善 1：推荐

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean user;

	public CommentBean() {
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

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getThumbup() {
		return thumbup;
	}

	public void setThumbup(int thumbup) {
		this.thumbup = thumbup;
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
}
