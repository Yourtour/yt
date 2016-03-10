package com.yt.ws.chat;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

import com.yt.business.bean.ChatSessionBean;
import com.yt.business.service.IChatService;
import com.yt.business.service.IUserService;
import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

public abstract class AbstractChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(AbstractChatRoomEndpoint.class);

	protected IChatService chatRepository;

	protected IUserService userService;

	public AbstractChatRoomEndpoint() {
		super();
		chatRepository = ContextLoader.getCurrentWebApplicationContext()
				.getBean(IChatService.class);
	}

	public void openSession(ChatSessionTypeEnum type, String roomCode,
			Session session, String userId) {
		// 在管理器中添加建立的会话
		ChatSessionUtils.addChatSession(type, roomCode, session, userId);
		// 检测并打开指定的目的地聊天室
		try {
			ChatSessionBean chatSessionBean = null;
			switch (type.name()) {
			case "PLACE_SESSION":
				chatSessionBean = chatRepository.openPlaceChatRoom(
						Long.valueOf(roomCode), userId);
				break;
			case "ROUTE_SESSION":
				chatSessionBean = chatRepository.openRouteChatRoom(
						Long.valueOf(roomCode), userId);
				break;
			case "DYNAMIC_SESSION":
			default:
				chatSessionBean = chatRepository.openDynamicChatRoom(roomCode,
						userId);
				break;
			}
			// 新增一条加入聊天会话历史记录
			chatRepository.saveChatJoinRecord(userId, chatSessionBean);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Open the chat room(%s) fail, place's id: %s",
						type.name(), roomCode), ex);
			}
		}
	}

	public void closeSession(ChatSessionTypeEnum type, String roomCode,
			Session session, String userId) {
		// 在管理器中移除关闭的会话
		ChatSessionUtils.delChatSession(type, roomCode, session, userId);
		// 更新该用户最近聊天加入历史的退出时间
		try {
			ChatSessionBean chatSessionBean = null;
			switch (type.name()) {
			case "PLACE_SESSION":
				chatSessionBean = chatRepository.openPlaceChatRoom(
						Long.valueOf(roomCode), userId);
				break;
			case "ROUTE_SESSION":
				chatSessionBean = chatRepository.openRouteChatRoom(
						Long.valueOf(roomCode), userId);
				break;
			case "DYNAMIC_SESSION":
			default:
				chatSessionBean = chatRepository.openDynamicChatRoom(roomCode,
						userId);
				break;
			}
			chatRepository.saveChatJoinRecord(userId, chatSessionBean);
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String
						.format("Update the user(id=%s)'s chat join history fail,  room(type:%s, code: %s).",
								userId, type.name(), roomCode));
			}
		}
	}

	public void onError(Session session, Throwable throwable) {
		if (LOG.isErrorEnabled()) {
			LOG.error(
					String.format("The session(%s) has any error.",
							session.getId()), throwable);
		}
	}

	public void processMessage(ChatSessionTypeEnum type, String roomCode,
			Session session, String userId, String command) {
		try {
			ChatSessionBean chatSessionBean = null;
			switch (type.name()) {
			case "PLACE_SESSION":
				chatSessionBean = chatRepository.openPlaceChatRoom(
						Long.valueOf(roomCode), userId);
				break;
			case "ROUTE_SESSION":
				chatSessionBean = chatRepository.openRouteChatRoom(
						Long.valueOf(roomCode), userId);
				break;
			case "DYNAMIC_SESSION":
			default:
				chatSessionBean = chatRepository.openDynamicChatRoom(roomCode,
						userId);
				break;
			}
			if (chatSessionBean != null) {
				ChatSessionUtils.processChatCommand(type, roomCode, command,
						session, chatRepository, chatSessionBean,
						Long.valueOf(userId));
				if (LOG.isDebugEnabled()) {
					LOG.debug("Update the user's newest chat join record successfully.");
				}
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Open the chat room fail, type: %s, room: %s.",
							type.name(), roomCode));
				}
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Process the message command fail, user(id=%s), type(%s), room(%s), message(%s)",
								userId, type.name(), roomCode, command), ex);
			}
		}
	}

}
