package com.yt.business;

import com.yt.business.common.Constants;
import com.yt.core.utils.DateUtils;
import com.yt.hbase.BaseBean;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.neo4j.bean.Neo4jBaseBean;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.io.Serializable;
import java.util.UUID;

@NodeEntity
public class BusinessBeanImpl extends BaseBeanImpl {
	private double commentScore; // 点评分数
	private int commentNum = 0; //评论数
	private int favoriteNum = 0; //收藏数
	private int likeNum = 0; //点赞数
	private int readNum = 0; //浏览数
	private int shareNum; // 分享数

	public BusinessBeanImpl(){
		super();
	}

	public BusinessBeanImpl(Long graphId){
		super(graphId);
	}

	/**
	 * 默认的构造函数
	 */
	public BusinessBeanImpl(String userId)
	{
		super(userId);
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
