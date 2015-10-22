package com.yt.business.neo4j.repository;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.UserBean;

@QueryResult
public class CommentTuple {
	@ResultColumn("comment")
	private CommentBean comment;

	@ResultColumn("user")
	private UserBean user;
	
	public CommentTuple(){
	}

	public CommentBean getComment() {
		return comment;
	}

	public void setComment(CommentBean comment) {
		this.comment = comment;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
}