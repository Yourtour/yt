package com.yt.ws.chat.commands;

import javax.websocket.Session;

import com.yt.business.bean.ChatSessionBean;
import com.yt.business.service.IChatService;
import com.yt.ws.chat.ChatSessionManager;
import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

public class ChatCommandChatterDel extends AbstractChatCommandChatter {
	public static final String COMMAND_NAME = "ADD_CHATTER";

	public ChatCommandChatterDel() {
		super();
		super.setType(COMMAND_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.ws.chat.commands.AbstractChatCommand#processCommand(com.yt.ws.
	 * chat.ChatSessionUtils.ChatSessionTypeEnum, java.lang.String,
	 * com.yt.ws.chat.ChatSessionManager, com.yt.business.service.IChatService,
	 * javax.websocket.Session, com.yt.business.bean.ChatSessionBean, long)
	 */
	@Override
	public void processCommand(ChatSessionTypeEnum type, String roomCode,
			ChatSessionManager sessionManager, IChatService repository,
			Session session, ChatSessionBean chatSessionBean, long userId) {
		String[] chatters = super.getChatters().split(",");
		for (String userCode : chatters) {
			sessionManager.delDynamiceChatWhiteList(roomCode, userCode, userId);
		}
	}

}
