package com.yt.vo.route;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.vo.BaseVO;
import com.yt.vo.member.UserProfileVO;
import com.yt.vo.member.UserVO;

public class RouteRecommendItemVO extends BaseVO {
	private String 	name; // 行程名称
	private String 	lineName;
	private String 	imageUrl;

	private float rankScore;
	private int thumbupNum;
	private int favoriteNum;
	private int shareNum;
	private int commentNum;

	private UserProfileVO user;

	public static RouteRecommendItemVO transform(RouteMainBean bean) {
		if (bean == null) {
			return null;
		}

		RouteRecommendItemVO routeVO = new RouteRecommendItemVO();
		routeVO.fromBean(bean);
		routeVO.setName(bean.getName());
		routeVO.setLineName(bean.getLineName());
		routeVO.setCommentNum(bean.getCommentNum());
		routeVO.setThumbupNum(bean.getThumbupNum());
		routeVO.setFavoriteNum(bean.getFavoriteNum());
		routeVO.setShareNum(bean.getShareNum());
		routeVO.setImageUrl(bean.getImageUrl());

		UserProfileBean owner = bean.getOwner();
		if(owner != null){
			routeVO.setUser(new UserProfileVO(bean.getOwner()));
		}

		return routeVO;
	}

	public RouteRecommendItemVO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getRankScore() {
		return rankScore;
	}

	public void setRankScore(float rankScore) {
		this.rankScore = rankScore;
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

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public UserProfileVO getUser() {
		return user;
	}

	public void setUser(UserProfileVO user) {
		this.user = user;
	}
}
