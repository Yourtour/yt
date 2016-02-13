package com.yt.ws.chat.commands;

public abstract class AbstractChatCommandChatter extends AbstractChatCommand {

	private String chatters = "";

	public AbstractChatCommandChatter() {
		super();
	}

	public String getChatters() {
		return chatters;
	}

	public void setChatters(String chatters) {
		this.chatters = chatters;
	}

}