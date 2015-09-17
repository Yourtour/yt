package com.yt.vo.route;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;

public class RecommendLineVO {
	private Long graphid;
	private String rowKey;
	private String name; // 名称
	private String imageUrl; // 图片
	private String feature; // 特点， 对线路特点进行简单描述
	private String reason; // 推荐理由，描述推荐理由
	private double recommendIndex; // 推荐指数
	private double commentIndex; // 点评指数
	private String place; // 目的地
	private Long placeId; // 目的地ID
	private String tags; // 标签
	private double commentScore; // 点评分数
	private int commentNum; // 点评数
	private int thumbupNum; // 点赞数
	private int favoriteNum; // 收藏数
	private int shareNum; // 分享数

	public static RecommendLineVO transform(LineBean bean) {
		if (bean == null) {
			return null;
		}
		RecommendLineVO vo = new RecommendLineVO();
		vo.setCommentIndex(bean.getCommentIndex());
		vo.setCommentNum(bean.getCommentNum());
		vo.setCommentScore(bean.getCommentScore());
		vo.setFavoriteNum(bean.getFavoriteNum());
		vo.setFeature(bean.getFeature());
		vo.setGraphid(bean.getGraphId());
		vo.setImageUrl(bean.getImageUrl());
		vo.setName(bean.getName());
		vo.setReason(bean.getReason());
		PlaceBean place = bean.getPlace();
		if (place != null) {
			vo.setPlace(place.getName());
			vo.setPlaceId(place.getGraphId());
		} else {
			vo.setPlace("");
			vo.setPlaceId(null);
		}
		vo.setRecommendIndex(bean.getCommentIndex());
		vo.setRowKey(bean.getRowKey());
		vo.setShareNum(bean.getShareNum());
		vo.setTags(bean.getTags());
		vo.setThumbupNum(bean.getThumbupNum());
		return vo;
	}

	public RecommendLineVO() {
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

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
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
}
