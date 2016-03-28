package com.yt.vo;

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

	public void fromBean(SocialBeanImpl bean) {
		if (bean == null) {
			return;
		}
		super.fromBean(bean);
		setCommentNum(bean.getCommentNum());
		setCommentScore(bean.getCommentScore());
		setFavoriteNum(bean.getFavoriteNum());
		setLikeNum(bean.getLikeNum());
		setReadNum(bean.getReadNum());
		setShareNum(bean.getShareNum());
	}

	public void toBean(SocialBeanImpl bean) {
		super.toBean(bean);
		bean.setCommentNum(getCommentNum());
		bean.setCommentScore(getCommentScore());
		bean.setFavoriteNum(getFavoriteNum());
		bean.setLikeNum(getLikeNum());
		bean.setReadNum(getReadNum());
		bean.setShareNum(getShareNum());
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
