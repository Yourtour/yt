package com.yt.ws.chat.commands;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.ChatRepository;
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
		try {
			// 记录接收到的消息
			ChatMessageBean message = new ChatMessageBean();
			message.setNotice(super.isNotice());
			message.setMessage(this.getTextMessage());
			message.setChatSession(chatSessionBean);
			message.setUserProfile(userProfileBean);
			String operator = userProfileBean.getCode();
			repository.save(message, true, operator);
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
			for (Session s : sessions) {
				if (session.getId().equals(s.getId())) {
					// 自己的会话，消息前面加星号标记
					s.getBasicRemote().sendText(
							String.format("[*]%s", this.getTextMessage()));
				} else {
					// 转发的消息
					s.getBasicRemote().sendText(this.getTextMessage());
				}
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("process command fail", ex);
			}
		}
	}
}
