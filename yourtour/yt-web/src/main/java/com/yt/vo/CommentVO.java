package com.yt.vo;

import com.yt.business.bean.CommentBean;

import java.io.Serializable;

public class CommentVO implements Serializable{
	private String abbr = "", name = "";

	public CommentVO() {
		super();
	}

	public static CommentVO transform(CommentBean bean){
		CommentVO comment = new CommentVO();

		return comment;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
