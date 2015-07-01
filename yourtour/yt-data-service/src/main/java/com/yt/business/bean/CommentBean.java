package com.yt.business.bean;

import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义了系统中的所有点评信息
 */
@HbaseTable(name = "T_COMMENT_INFO")
public class CommentBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	private 	@HbaseColumn(name = "title")	String title = ""; //评论标题	
	private 	@HbaseColumn(name = "cont")	String content = ""; //评论内容
	private 	@HbaseColumn(name = "score")	int score = 0; //评分
	private	@HbaseColumn(name = "uvote")		int upvotes = 0; //点赞数
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "stat")	Status	status;
	
	public CommentBean() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
