package com.yt.ws.chat;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.ChatRepository;
import com.yt.ws.chat.commands.AbstractChatCommand;
import com.yt.ws.chat.commands.ChatCommandChatterAdd;
import com.yt.ws.chat.commands.ChatCommandChatterDel;
import com.yt.ws.chat.commands.ChatCommandMessageText;

public class ChatSessionUtils {
	private static final Log LOG = LogFactory.getLog(ChatSessionUtils.class);
	private static ChatSessionManager manager = ChatSessionManager.getManager();

	public enum ChatSessionTypeEnum {
		PLACE_SESSION, ROUTE_SESSION, DYNAMIC_SESSION
	}

	private ChatSessionUtils() {
		super();
	}

	public static void addChatSession(ChatSessionTypeEnum type, String code,
			Session session, String userId) {
		if (code == null || code.isEmpty() || session == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The code or session is null, the operate is ignored.");
			}
			return;
		}
		switch (type) {
		case PLACE_SESSION:
			manager.addPlaceChatSession(code, session, userId);
			break;
		case ROUTE_SESSION:
			manager.addRouteChatSession(code, session, userId);
			break;
		case DYNAMIC_SESSION:
		default:
			manager.addDynamicChatSession(code, session, userId);
			break;
		}
	}

	public static void delChatSession(ChatSessionTypeEnum type, String code,
			Session session, String userId) {
		if (code == null || code.isEmpty() || session == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The code or session is null, the operate is ignored.");
			}
			return;
		}
		switch (type) {
		case PLACE_SESSION:
			manager.delPlaceChatSession(code, session, userId);
			break;
		case ROUTE_SESSION:
			manager.delRouteChatSession(code, session, userId);
			break;
		case DYNAMIC_SESSION:
		default:
			manager.delDynamicChatSession(code, session, userId);
			break;
		}
	}

	public static void processChatCommand(ChatSessionTypeEnum type,
			String roomCode, String command, Session session,
			ChatRepository repository, ChatSessionBean chatSessionBean,
			UserProfileBean userProfile) {
		if (roomCode == null || roomCode.isEmpty() || session == null
				|| command == null || command.isEmpty()) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The code or session or command is null, the operate is ignored.");
			}
			return;
		}
		AbstractChatCommand cmdObject = transformCommand(command);
		if (cmdObject == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The command object is null.");
			}
			return;
		}
		switch (cmdObject.getType()) {
		case ChatCommandChatterAdd.COMMAND_NAME:
		case ChatCommandChatterDel.COMMAND_NAME:
			// 只有动态创建的聊天室允许加人和踢人
			if (type != ChatSessionTypeEnum.DYNAMIC_SESSION) {
				break;
			}
		case ChatCommandMessageText.COMMAND_NAME:
		default:
			cmdObject.processCommand(type, roomCode, manager, repository,
					session, chatSessionBean, userProfile);
			break;
		}
	}

	public static AbstractChatCommand transformCommand(String command) {
		if (command == null || command.isEmpty()) {
			return null;
		}
		JSONObject json = JSON.parseObject(command);
		String type = json.getString("type");
		switch (type) {
		case ChatCommandChatterAdd.COMMAND_NAME:
			return JSON.toJavaObject(json, ChatCommandChatterAdd.class);
		case ChatCommandChatterDel.COMMAND_NAME:
			return JSON.toJavaObject(json, ChatCommandChatterDel.class);
		case ChatCommandMessageText.COMMAND_NAME:
			String messageType = json.getString("messageType");
			if (ChatCommandMessageText.MESSAGE_TYPE.equals(messageType)) {
				return JSON.toJavaObject(json, ChatCommandMessageText.class);
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"The command type['%s:%s'] is not supported.",
							type, messageType));
				}
			}
		default:
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format(
						"The command type['%s'] is not supported.", type));
			}
			return null;
		}
	}

}
