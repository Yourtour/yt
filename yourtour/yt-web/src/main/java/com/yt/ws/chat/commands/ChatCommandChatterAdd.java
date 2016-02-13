package com.yt.ws.chat.commands;

import javax.websocket.Session;

import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.ChatRepository;
import com.yt.ws.chat.ChatSessionManager;
import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

public class ChatCommandChatterAdd extends AbstractChatCommandChatter {
	public static final String COMMAND_NAME = "DEL_CHATTER";

	public ChatCommandChatterAdd() {
		super();
		super.setType(COMMAND_NAME);
	}

	@Override
	public void processCommand(ChatSessionTypeEnum type, String roomCode,
			ChatSessionManager sessionManager, ChatRepository repository,
			Session session, ChatSessionBean chatSessionBean,
			UserProfileBean userProfileBean) {
		String[] chatters = super.getChatters().split(",");
		for (String userCode : chatters) {
			sessionManager.addDynamiceChatWhiteList(roomCode, userCode);
		}
	}

}
