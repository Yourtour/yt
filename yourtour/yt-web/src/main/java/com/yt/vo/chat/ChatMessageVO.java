package com.yt.vo.chat;

import java.io.Serializable;

import com.yt.business.bean.ChatMessageBean;
import com.yt.vo.member.UserProfileVO;

public class ChatMessageVO implements Serializable {

	private static final long serialVersionUID = 7556031196472498017L;

	private ChatMessageBean bean = null;

	public ChatMessageVO(ChatMessageBean bean) {
		super();
		this.bean = bean;
	}

	public Long getId() {
		return bean.getGraphId();
	}

	public boolean isNotice() {
		return bean.isNotice();
	}

	public String getText() {
		return bean.getMessage();
	}

	public UserProfileVO getFromUser() {
		return new UserProfileVO(bean.getUserProfile());
	}

}
