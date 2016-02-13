package com.yt.ws.chat.commands;

public abstract class AbstractChatCommandMessage extends AbstractChatCommand {
	public static final String COMMAND_NAME = "MESSAGE";

	private String messageType = "";
	private boolean notice = false;
	private Object message = null;

	public AbstractChatCommandMessage() {
		super();
		super.setType(COMMAND_NAME);
	}

	public String getMessageType() {
		return messageType;
	}

	protected void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean notice) {
		this.notice = notice;
	}

	public Object getMessage() {
		return message;
	}

	protected void setMessage(Object message) {
		this.message = message;
	}

}
