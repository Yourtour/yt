package com.yt.business.bean;

import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义了系统中的线路信息。线路相关的景点之间的关系通过图状数据库Neo4j存储
 * 
 */
@HbaseTable(name = "T_LINE_INFO")
public class LineBean extends BaseBean {
	private static final long serialVersionUID = -3433522673262851121L;

	private 	@HbaseColumn(name = "name")		String	 name;  //名称 
	private 	@HbaseColumn(name = "img") 			String	 imageUrl;  //图片 
	private 	@HbaseColumn(name = "intr")			String 	intro; 	//概述， 线路进行简单介绍
	private 	@HbaseColumn(name = "feat")			String 	feature; 	//特点， 对线路特点进行简单描述
	private 	@HbaseColumn(name = "reas")			String 	reason;  	//推荐理由，描述推荐理由
	private	@HbaseColumn(name = "rind")			int 	recommendIndex; 	//推荐指数
	private	@HbaseColumn(name = "ruid")			String 	recommendUserId; 	//推荐人
	private	@HbaseColumn(name = "plac")			String 	place; 	//目的地
	
	private	@HbaseColumn(name = "anum")		int arriveNum; 	//到达人数
	private	@HbaseColumn(name = "cscore")		int commentScore; 	//点评分数
	private	@HbaseColumn(name = "cnum")		int commentNum; 	//点评数
	private	@HbaseColumn(name = "fnum")			int favoriteNum; 	//收藏数
	private	@HbaseColumn(name = "snum")			int shareNum; 	//分享数
	
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		Status	status;
	
	public LineBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getRecommendIndex() {
		return recommendIndex;
	}

	public void setRecommendIndex(int recommendIndex) {
		this.recommendIndex = recommendIndex;
	}

	public String getRecommendUserId() {
		return recommendUserId;
	}

	public void setRecommendUserId(String recommendUserId) {
		this.recommendUserId = recommendUserId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getArriveNum() {
		return arriveNum;
	}

	public void setArriveNum(int arriveNum) {
		this.arriveNum = arriveNum;
	}

	public int getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(int commentScore) {
		this.commentScore = commentScore;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getShareNum() {
		return shareNum;
	}

	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
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
