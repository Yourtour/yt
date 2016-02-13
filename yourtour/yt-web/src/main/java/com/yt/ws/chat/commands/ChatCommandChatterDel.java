package com.yt.ws.chat.commands;

import javax.websocket.Session;

import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.ChatRepository;
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
	 * com.yt.ws.chat.ChatSessionManager,
	 * com.yt.business.repository.ChatRepository, javax.websocket.Session,
	 * com.yt.business.bean.ChatSessionBean,
	 * com.yt.business.bean.UserProfileBean)
	 */
	@Override
	public void processCommand(ChatSessionTypeEnum type, String roomCode,
			ChatSessionManager sessionManager, ChatRepository repository,
			Session session, ChatSessionBean chatSessionBean,
			UserProfileBean userProfileBean) {
		String[] chatters = super.getChatters().split(",");
		String operator = session.getUserPrincipal().getName();
		for (String userCode : chatters) {
			sessionManager.delDynamiceChatWhiteList(roomCode, userCode, operator);
		}
	}

}
