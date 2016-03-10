package com.yt.ws.chat.commands;

import java.util.Date;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.service.IChatService;
import com.yt.ws.chat.ChatSessionManager;
import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

public class ChatCommandMessageText extends AbstractChatCommandMessage {
	private static final Log LOG = LogFactory
			.getLog(ChatCommandMessageText.class);
	public static final String MESSAGE_TYPE = "text/plain";

	public ChatCommandMessageText() {
		super();
		super.setMessageType(MESSAGE_TYPE);
	}

	public String getTextMessage() {
		return (String) super.getMessage();
	}

	public void setTextMessage(String message) {
		super.setMessage(message);
	}

	@Override
	public void processCommand(ChatSessionTypeEnum type, String roomCode,
			ChatSessionManager sessionManager, IChatService repository,
			Session session, ChatSessionBean chatSessionBean, long userId) {
		try {
			// 记录接收到的消息
			ChatMessageBean message = repository.saveChatMessage(
					super.isNotice(), this.getTextMessage(), chatSessionBean,
					userId);
			if (LOG.isDebugEnabled()) {
				LOG.debug("save a text message successfully.");
			}
			// 转发接收到的消息
			Iterable<Session> sessions = null;
			switch (type.name()) {
			case "PLACE_SESSION":
				sessions = sessionManager.getPlaceChatRoom(roomCode);
				break;
			case "ROUTE_SESSION":
				sessions = sessionManager.getRouteChatRoom(roomCode);
				break;
			case "DYNAMIC_SESSION":
			default:
				sessions = sessionManager.getDynamicChatRoom(roomCode);
				break;
			}

			JSONObject jsonUser = new JSONObject();
			jsonUser.put("id", message.getUserProfile().getId());
			jsonUser.put("nickName", message.getUserProfile().getNickName());
			jsonUser.put("imageUrl", message.getUserProfile().getImageUrl());
			JSONObject jsonMessage = new JSONObject();
			jsonMessage.put("contentType", MESSAGE_TYPE);
			jsonMessage.put("content", this.getTextMessage());
			jsonMessage.put("user", jsonUser);
			for (Session s : sessions) {
				jsonMessage.put("sendTime", new Date().getTime());
				s.getBasicRemote().sendText(jsonMessage.toJSONString());
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("process command fail", ex);
			}
		}
	}
}
