package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.UserProfileBean;

@QueryResult
public class CommentTuple {
	@ResultColumn("comment")
	private CommentBean comment;

	@ResultColumn("user")
	private UserProfileBean user;
	
	public CommentTuple(){
	}

	public CommentBean getComment() {
		return comment;
	}

	public void setComment(CommentBean comment) {
		this.comment = comment;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}