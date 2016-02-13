package com.yt.vo.chat;

import java.io.Serializable;

import com.yt.business.bean.ChatSessionBean;

public class ChatSessionVO implements Serializable {
	private static final long serialVersionUID = 1014540185314715793L;

	private ChatSessionBean sessionBean = null;
	
	public ChatSessionVO(ChatSessionBean sessionBean) {
		super();
		this.sessionBean = sessionBean;
	}
	
	public Long getId() {
		return sessionBean.getGraphId();
	}
	
	public String getCode() {
		return sessionBean.getChatRoomNo();
	}
	
	public String getType() {
		return sessionBean.getChatRoomType();
	}
	
	public String getUrl() {
		return sessionBean.getChatRoomUrl();
	}
	
	public int getChatterNumber() {
		return sessionBean.getChatterNum();
	}

}
