package com.yt.vo.chat;

import java.io.Serializable;

import com.yt.business.bean.ChatMessageBean;
import com.yt.vo.member.UserVO;

public class ChatMessageVO implements Serializable {

	private static final long serialVersionUID = 7556031196472498017L;

	private ChatMessageBean bean = null;

	public ChatMessageVO(ChatMessageBean bean) {
		super();
		this.bean = bean;
	}

	public Long getId() {
		return bean.getId();
	}

	public boolean isNotice() {
		return bean.isNotice();
	}

	public String getText() {
		return bean.getContent();
	}

	public UserVO getFromUser() {
		return UserVO.transform(bean.getSender());
	}

}
