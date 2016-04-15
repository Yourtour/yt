package com.yt.vo;

import com.yt.business.BaseBeanImpl;
import com.yt.business.SocialBeanImpl;

public class SocialVO extends BaseVO {
	private double commentScore; // 点评分数
	private int commentNum = 0; // 评论数
	private int favoriteNum = 0; // 收藏数
	private int likeNum = 0; // 点赞数
	private int readNum = 0; // 浏览数
	private int shareNum; // 分享数
	private int goodNum; // 好评
	private int mediumNum; // 中评
	private int badNum; // 差评
	private int imageNum; // 晒图

	public SocialVO() {
		super();
	}

	@Override
	public void fromBean(BaseBeanImpl bean) {
		if (bean == null) {
			return;
		}
		super.fromBean(bean);
		SocialBeanImpl social = (SocialBeanImpl) bean;
		setCommentNum(social.getCommentNum());
		setCommentScore(social.getCommentScore());
		setFavoriteNum(social.getFavoriteNum());
		setLikeNum(social.getLikeNum());
		setReadNum(social.getReadNum());
		setShareNum(social.getShareNum());
		setGoodNum(social.getGoodNum());
		setMediumNum(social.getMediumNum());
		setBadNum(social.getBadNum());
		setImageNum(social.getImageNum());
	}

	@Override
	public void toBean(BaseBeanImpl bean) {
		super.toBean(bean);
		SocialBeanImpl social = (SocialBeanImpl) bean;
		social.setCommentNum(getCommentNum());
		social.setCommentScore(getCommentScore());
		social.setFavoriteNum(getFavoriteNum());
		social.setLikeNum(getLikeNum());
		social.setReadNum(getReadNum());
		social.setShareNum(getShareNum());
		social.setGoodNum(getGoodNum());
		social.setMediumNum(getMediumNum());
		social.setBadNum(getBadNum());
		social.setImageNum(getImageNum());
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

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getShareNum() {
		return shareNum;
	}

	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public int getMediumNum() {
		return mediumNum;
	}

	public void setMediumNum(int mediumNum) {
		this.mediumNum = mediumNum;
	}

	public int getBadNum() {
		return badNum;
	}

	public void setBadNum(int badNum) {
		this.badNum = badNum;
	}

	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

}
