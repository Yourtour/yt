package com.yt.ws.chat.message;

import com.yt.business.bean.ChatMessageBean.MessageType;

public class ChatMessageText extends AbstractChatMessage {
	public ChatMessageText() {
		super();
		super.setType(MessageType.TEXT);
	}

	public String getTextMessage() {
		return super.getContent();
	}

	public void setTextMessage(String text) {
		super.setContent(text);
	}

}
