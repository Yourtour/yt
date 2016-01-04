package com.yt.vo.route;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants.Status;
import com.yt.vo.BaseVO;

public class LineVO extends BaseVO {
	private String code;
	private String name; // 名称
	private String imageUrl; // 图片
	private String intro; // 概述， 线路进行简单介绍
	private String feature; // 特点， 对线路特点进行简单描述
	private String reason; // 推荐理由，描述推荐理由
	private double recommendIndex; // 推荐指数
	private double commentIndex; // 点评指数
	private int arriveNum; // 到达人数
	private String tags; // 标签
	private double commentScore; // 点评分数
	private int commentNum; // 点评数
	private int thumbupNum; // 点赞数
	private int favoriteNum; // 收藏数
	private int shareNum; // 分享数

	private String place; // 目的地
	private Long placeId; // 目的地对象ID
	private String scenes; // 线路包括的景点ID，逗号分割
	private String hotels; // 线路中包括的宾馆ID，逗号分割
	private String restaurants; // 线路中包括的饭店ID，逗号分割
	private Status status;

	public static LineVO transform(LineBean bean) {
		if (bean == null) {
			return null;
		}
		LineVO vo = new LineVO();
		vo.fromBean(bean);
		vo.setCode(bean.getCode());
		vo.setArriveNum(bean.getArriveNum());
		vo.setCommentIndex(bean.getCommentIndex());
		vo.setCommentNum(bean.getCommentNum());
		vo.setCommentScore(bean.getCommentNum());
		vo.setFavoriteNum(bean.getFavoriteNum());
		vo.setFeature(bean.getFeature());
		vo.setImageUrl(bean.getImageUrl());
		vo.setIntro(bean.getIntro());
		vo.setName(bean.getName());
		vo.setReason(bean.getReason());
		vo.setRecommendIndex(bean.getRecommendIndex());
		vo.setShareNum(bean.getShareNum());
		vo.setStatus(bean.getStatus());
		vo.setTags(bean.getTags());
		vo.setThumbupNum(bean.getThumbupNum());

		// 从目的地对象中获取ID和名称，便于前端显示
		PlaceBean place = bean.getPlace();
		if (place != null) {
			vo.setPlace(place.getName());
			vo.setPlaceId(place.getGraphId());
		} else {
			vo.setPlace("");
			vo.setPlaceId(null);
		}

		// 从景点对象中获取ID，便于前端显示
		StringBuffer sbSceneId = new StringBuffer();
		for (int index = 0, num = bean.getScenes().size(); index < num; index++) {
			SceneResourceBean scene = bean.getScenes().get(index);
			if (scene == null) {
				continue;
			}
			sbSceneId.append(scene.getGraphId());
			sbSceneId.append(",");
		}
		int len = sbSceneId.length();
		// 去除最后一个逗号
		len = (len > 0) ? len - 1 : 0;
		vo.setScenes(sbSceneId.substring(0, len));

		return vo;
	}

	public static LineBean transform(LineVO vo) {
		if (vo == null) {
			return null;
		}
		LineBean bean = new LineBean();
		vo.toBean(bean);
		bean.setCode(vo.getCode());
		bean.setArriveNum(vo.getArriveNum());
		bean.setCommentIndex(vo.getCommentIndex());
		bean.setCommentNum(vo.getCommentNum());
		bean.setCommentScore(vo.getCommentScore());
		bean.setFavoriteNum(vo.getFavoriteNum());
		bean.setFeature(vo.getFeature());
		bean.setImageUrl(vo.getImageUrl());
		bean.setIntro(vo.getIntro());
		bean.setName(vo.getName());
		bean.setRowKey(bean.getName());

		bean.setReason(vo.getReason());
		bean.setRecommendIndex(vo.getRecommendIndex());
		bean.setShareNum(vo.getShareNum());
		bean.setStatus(vo.getStatus());
		bean.setTags(vo.getTags());
		bean.setThumbupNum(vo.getThumbupNum());

		// 从VO中取出目的地的ID，并设置到PlaceBean中，便于后续建立关联关系
		PlaceBean place = new PlaceBean();
		place.setGraphId(vo.getPlaceId());
		bean.setPlace(place);

		// 从VO中取出景点的ID，并设置到SceneResourceBean中，便于后续建立关联关系
		String[] sceneIds = vo.getScenes().split(",");
		for (int index = 0; index < sceneIds.length; index++) {
			if (sceneIds[index].length() <= 0) {
				continue;
			}
			SceneResourceBean scene = new SceneResourceBean();
			scene.setGraphId(Long.valueOf(sceneIds[index]));
			bean.getScenes().add(scene);
		}

		return bean;
	}

	public LineVO() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
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

	public String getScenes() {
		return scenes;
	}

	public void setScenes(String scenes) {
		this.scenes = scenes;
	}

	public String getHotels() {
		return hotels;
	}

	public void setHotels(String hotels) {
		this.hotels = hotels;
	}

	public String getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(String restaurants) {
		this.restaurants = restaurants;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
