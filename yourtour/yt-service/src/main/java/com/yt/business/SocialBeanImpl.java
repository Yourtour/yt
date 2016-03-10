package com.yt.business;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class SocialBeanImpl extends BaseBeanImpl {
	private static final long serialVersionUID = 6037109574215594532L;
	private double commentScore; // 点评分数
	private int commentNum = 0; //评论数
	private int favoriteNum = 0; //收藏数
	private int likeNum = 0; //点赞数
	private int readNum = 0; //浏览数
	private int shareNum; // 分享数

	public SocialBeanImpl(){
		super();
	}

	public SocialBeanImpl(Long graphId){
		super(graphId);
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
