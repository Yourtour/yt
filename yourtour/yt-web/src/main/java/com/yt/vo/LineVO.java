package com.yt.vo;

import com.yt.business.bean.LineBean;
import com.yt.business.common.Constants.Status;

public class LineVO {
	private Long graphid;
	private String rowKey;
	private String createdUserId = "";
	private long createdTime;
	private String updatedUserId = "";
	private long updatedTime;
	private String name; // 名称
	private String imageUrl; // 图片
	private String intro; // 概述， 线路进行简单介绍
	private String feature; // 特点， 对线路特点进行简单描述
	private String reason; // 推荐理由，描述推荐理由
	private double recommendIndex; // 推荐指数
	private double commentIndex; // 点评指数
	private String place; // 目的地
	private int arriveNum; // 到达人数
	private String tags; // 标签
	private double commentScore; // 点评分数
	private int commentNum; // 点评数
	private int thumbupNum; // 点赞数
	private int favoriteNum; // 收藏数
	private int shareNum; // 分享数
	private Status status;

	public static LineVO transform(LineBean bean) {
		if (bean == null) {
			return null;
		}
		LineVO vo = new LineVO();
		vo.setArriveNum(bean.getArriveNum());
		vo.setCommentIndex(bean.getCommentIndex());
		vo.setCommentNum(bean.getCommentNum());
		vo.setCommentScore(bean.getCommentNum());
		vo.setCreatedTime(bean.getCreatedTime());
		vo.setCreatedUserId(bean.getCreatedUserId());
		vo.setFavoriteNum(bean.getFavoriteNum());
		vo.setFeature(bean.getFeature());
		vo.setGraphid(bean.getGraphId());
		vo.setImageUrl(bean.getImageUrl());
		vo.setIntro(bean.getIntro());
		vo.setName(bean.getName());
		vo.setPlace(bean.getPlace());
		vo.setReason(bean.getReason());
		vo.setRecommendIndex(bean.getRecommendIndex());
		vo.setRowKey(bean.getRowKey());
		vo.setShareNum(bean.getShareNum());
		vo.setStatus(bean.getStatus());
		vo.setTags(bean.getTags());
		vo.setThumbupNum(bean.getThumbupNum());
		vo.setUpdatedTime(bean.getUpdatedTime());
		vo.setUpdatedUserId(bean.getUpdatedUserId());
		return vo;
	}

	public static LineBean transform(LineVO vo) {
		if (vo == null) {
			return null;
		}
		LineBean bean = new LineBean();
		bean.setArriveNum(vo.getArriveNum());
		bean.setCommentIndex(vo.getCommentIndex());
		bean.setCommentNum(vo.getCommentNum());
		bean.setCommentScore(vo.getCommentScore());
		bean.setCreatedTime(vo.getCreatedTime());
		bean.setCreatedUserId(vo.getCreatedUserId());
		bean.setFavoriteNum(vo.getFavoriteNum());
		bean.setFeature(vo.getFeature());
		bean.setGraphId(vo.getGraphid());
		bean.setImageUrl(vo.getImageUrl());
		bean.setIntro(vo.getIntro());
		bean.setName(vo.getName());
		bean.setPlace(vo.getPlace());
		bean.setReason(vo.getReason());
		bean.setRecommendIndex(vo.getRecommendIndex());
		bean.setRowKey(vo.getRowKey());
		bean.setShareNum(vo.getShareNum());
		bean.setStatus(vo.getStatus());
		bean.setTags(vo.getTags());
		bean.setThumbupNum(vo.getThumbupNum());
		bean.setUpdatedTime(vo.getUpdatedTime());
		bean.setUpdatedUserId(vo.getUpdatedUserId());
		return bean;
	}

	public LineVO() {
		super();
	}

	public Long getGraphid() {
		return graphid;
	}

	public void setGraphid(Long graphid) {
		this.graphid = graphid;
	}

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public double getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(double commentScore) {
		this.commentScore = commentScore;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getThumbupNum() {
		return thumbupNum;
	}

	public void setThumbupNum(int thumbupNum) {
		this.thumbupNum = thumbupNum;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
