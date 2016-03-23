package com.yt.ws.chat.message;

import com.yt.business.bean.ChatMessageBean.MessageType;

public abstract class AbstractChatMessage {
	private MessageType type = MessageType.TEXT; // 消息类型，默认为文本消息
	private long senderId; // 发送者ID
	private boolean isNotice; // 是否为通告，一般通告置顶
	private String content; // 消息内容，文本直接存放，其他（如图片、视频）则是BASE64编码

	public AbstractChatMessage() {
		super();
	}

	public MessageType getType() {
		return type;
	}

	protected void setType(MessageType type) {
		this.type = type;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public boolean isNotice() {
		return isNotice;
	}

	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
