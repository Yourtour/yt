package com.yt.ws.chat;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.ChatJoinHistoryBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.ChatRepository;
import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

@Component
public abstract class AbstractChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(AbstractChatRoomEndpoint.class);
	
	@Autowired
	private ChatRepository chatRepository;

	public AbstractChatRoomEndpoint() {
		super();
	}

	public void openSession(ChatSessionTypeEnum type, String roomCode,
			Session session) {
		// 在管理器中添加建立的会话
		ChatSessionUtils.addChatSession(type, roomCode, session);
		// 检测并打开指定的目的地聊天室
		String operator = session.getUserPrincipal().getName();
		try {
			ChatSessionBean chatSessionBean = null;
			switch (type.name()) {
			case "PLACE_SESSION":
				chatSessionBean = chatRepository.openPlaceChatRoom(
						Long.valueOf(roomCode), operator);
				break;
			case "ROUTE_SESSION":
				chatSessionBean = chatRepository.openRouteChatRoom(
						Long.valueOf(roomCode), operator);
				break;
			case "DYNAMIC_SESSION":
			default:
				chatSessionBean = chatRepository.openDynamicChatRoom(roomCode,
						operator);
				break;
			}
			// 新增一条加入聊天会话历史记录
			UserProfileBean userProfile = (UserProfileBean) chatRepository.get(
					UserProfileBean.class, "code", operator);
			if (userProfile == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Fetch the user profile fail, code: %s.", operator));
				}
				ChatJoinHistoryBean his = new ChatJoinHistoryBean();
				his.setChatSession(chatSessionBean);
				his.setUserProfile(userProfile);
				chatRepository.save(his, true, operator);
				if (LOG.isDebugEnabled()) {
					LOG.debug(String
							.format("Open the chat room(type: %s, code:%s) and create the chat join history(operator:%s) successfullly.",
									type.name(), roomCode, operator));
				}
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Open the chat room fail, type: %s, roomCode: %s.",
							type.name(), roomCode));
				}
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Open the chat room(%s) fail, place's id: %s",
						type.name(), roomCode), ex);
			}
		}
	}

	public void closeSession(ChatSessionTypeEnum type, String roomCode,
			Session session) {
		// 在管理器中移除关闭的会话
		ChatSessionUtils.delChatSession(type, roomCode, session);
		// 更新该用户最近聊天加入历史的退出时间
		String operator = session.getUserPrincipal().getName();
		try {
			UserProfileBean userProfile = (UserProfileBean) chatRepository.get(
					UserProfileBean.class, "code", operator);
			if (userProfile != null) {
				ChatJoinHistoryBean his = chatRepository
						.getNewestChatJoinRecord(userProfile.getGraphId());
				if (his != null) {
					chatRepository.save(his, operator);
					if (LOG.isDebugEnabled()) {
						LOG.debug("Update the user's newest chat join record successfully.");
					}
				} else {
					if (LOG.isWarnEnabled()) {
						LOG.warn(String
								.format("The user's newest chat join record not exist, user code: %s.",
										operator));
					}
				}
			} else {
				if (LOG.isErrorEnabled()) {
					LOG.error(String.format(
							"The user profile not exist, code: %s.", operator));
				}
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String
						.format("Update the user(%s)'s chat join history fail,  room(type:%s, code: %s).",
								operator, type.name(), roomCode));
			}
		}
	}

	public void processMessage(ChatSessionTypeEnum type, String roomCode,
			Session session, String command) {
		String operator = session.getUserPrincipal().getName();
		try {
			ChatSessionBean chatSessionBean = null;
			switch (type.name()) {
			case "PLACE_SESSION":
				chatSessionBean = chatRepository.openPlaceChatRoom(
						Long.valueOf(roomCode), operator);
				break;
			case "ROUTE_SESSION":
				chatSessionBean = chatRepository.openRouteChatRoom(
						Long.valueOf(roomCode), operator);
				break;
			case "DYNAMIC_SESSION":
			default:
				chatSessionBean = chatRepository.openDynamicChatRoom(roomCode,
						operator);
				break;
			}
			if (chatSessionBean != null) {
				UserProfileBean userProfile = (UserProfileBean) chatRepository
						.get(UserProfileBean.class, "code", operator);
				if (userProfile != null) {
					ChatSessionUtils.processChatCommand(type, roomCode,
							command, session, chatRepository, chatSessionBean,
							userProfile);
					if (LOG.isDebugEnabled()) {
						LOG.debug("Update the user's newest chat join record successfully.");
					}
				} else {
					if (LOG.isErrorEnabled()) {
						LOG.error(String.format(
								"The user profile not exist, code: %s.",
								operator));
					}
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
				LOG.error(String
						.format("Process the message command fail, user(%s), type(%s), room(%s), message(%s)",
								operator, type.name(), roomCode, command));
			}
		}
	}

}
