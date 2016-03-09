package com.yt.ws.chat.commands;

import javax.websocket.Session;

import com.yt.business.bean.ChatSessionBean;
import com.yt.business.service.IChatService;
import com.yt.ws.chat.ChatSessionManager;
import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

public abstract class AbstractChatCommand {
	private String type = "NA";

	public AbstractChatCommand() {
		super();
	}

	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public abstract void processCommand(ChatSessionTypeEnum type,
			String roomCode, ChatSessionManager sessionManager,
			IChatService repository, Session session,
			ChatSessionBean chatSessionBean, long userId);

}
