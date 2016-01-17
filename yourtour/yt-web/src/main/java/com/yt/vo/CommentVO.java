package com.yt.vo;

import com.yt.business.bean.CommentBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.member.UserProfileVO;
import com.yt.vo.member.UserVO;

import java.io.Serializable;

public class CommentVO extends BaseVO{
	private float 	score = 0f;
	private String 	memo;
	private String 	imageUrls;
	private int		thumbup = 0;
	private String	replied = "";
	private String	tags;
	private String 	createdDate;
	private UserVO user;

	public CommentVO() {
		super();
	}

	public static CommentVO transform(CommentBean bean){
		CommentVO comment = new CommentVO();

		comment.setImageUrls(bean.getImageUrls());
		comment.setMemo(bean.getMemo());
		comment.setReplied(bean.getReplied());
		comment.setScore(bean.getScore());
		comment.setTags(bean.getTags());
		comment.setThumbup(bean.getThumbup());
		comment.setCreatedDate(DateUtils.formatDate(bean.getCreatedTime()));

		comment.setUser(UserVO.transform(bean.getUser()));


		return comment;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getThumbup() {
		return thumbup;
	}

	public void setThumbup(int thumbup) {
		this.thumbup = thumbup;
	}

	public String getReplied() {
		return replied;
	}

	public void setReplied(String replied) {
		this.replied = replied;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
