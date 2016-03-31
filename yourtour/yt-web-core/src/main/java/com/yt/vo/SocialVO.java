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

	public SocialVO() {
		super();
	}

	@Override
	public void fromBean(BaseBeanImpl bean) {
		if (bean == null) {
			return;
		}
		super.fromBean(bean);
		SocialBeanImpl socaial = (SocialBeanImpl) bean;
		setCommentNum(socaial.getCommentNum());
		setCommentScore(socaial.getCommentScore());
		setFavoriteNum(socaial.getFavoriteNum());
		setLikeNum(socaial.getLikeNum());
		setReadNum(socaial.getReadNum());
		setShareNum(socaial.getShareNum());
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

}
