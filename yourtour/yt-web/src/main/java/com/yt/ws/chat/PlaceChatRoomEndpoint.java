package com.yt.ws.chat;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

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
@ServerEndpoint("/ws/chat/place/{placeId}")
public class PlaceChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(PlaceChatRoomEndpoint.class);

	@Autowired
	private ChatRepository chatRepository;

	public PlaceChatRoomEndpoint() {
		super();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("placeId") String placeId) {
		// 在管理器中添加建立的会话
		ChatSessionUtils.addChatSession(ChatSessionTypeEnum.PLACE_SESSION,
				placeId, session);
		// 检测并打开指定的目的地聊天室
		String operator = session.getUserPrincipal().getName();
		try {
			ChatSessionBean chatSessionBean = chatRepository.openPlaceChatRoom(
					Long.valueOf(placeId), operator);
			if (chatSessionBean == null) {
				// 新增一条加入聊天会话历史记录
				UserProfileBean userProfile = (UserProfileBean) chatRepository
						.get(UserProfileBean.class, "code", operator);
				if (userProfile == null) {
					if (LOG.isWarnEnabled()) {
						LOG.warn(String.format(
								"Fetch the user profile fail, code: %s.",
								operator));
					}
				}
				ChatJoinHistoryBean his = new ChatJoinHistoryBean();
				his.setChatSession(chatSessionBean);
				his.setUserProfile(userProfile);
				chatRepository.save(his, true, operator);
				if (LOG.isDebugEnabled()) {
					LOG.debug(String
							.format("Open the place chat room(placeId=%s) and create the chat join history(operator=%s) successfullly.",
									placeId, operator));
				}
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format(
							"Open the place chat room fail, place's id: %s.",
							placeId));
				}
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Open the place chat room fail, place's id: %s",
						placeId), ex);
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("->OnOpen(): sessionId(%s), place(%s).",
					session.getId(), placeId));
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("placeId") String placeId,
			CloseReason reason) {
		// 在管理器中移除关闭的会话
		ChatSessionUtils.delChatSession(ChatSessionTypeEnum.PLACE_SESSION,
				placeId, session);
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
								.format("The user's newest chat join record not exist, user code: $s.",
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
						.format("Update the user(%s)'s chat join history fail, placde(%s).",
								operator, placeId));
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnClose(): sessionId(%s), place(%s), code(%s), reason(%s).",
							session.getId(), placeId, reason.getCloseCode()
									.toString(), reason.getReasonPhrase()));
		}
	}

	@OnMessage
	public void onMessage(Session session,
			@PathParam("placeId") String placeId, String command)
			throws IOException {
		String operator = session.getUserPrincipal().getName();
		try {
			ChatSessionBean chatSessionBean = chatRepository.openPlaceChatRoom(
					Long.valueOf(placeId), operator);
			if (chatSessionBean != null) {
				UserProfileBean userProfile = (UserProfileBean) chatRepository
						.get(UserProfileBean.class, "code", operator);
				if (userProfile != null) {
					ChatSessionUtils.processChatCommand(
							ChatSessionTypeEnum.PLACE_SESSION, placeId,
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
							"Open the place chat room fail, place's id: %s.",
							placeId));
				}
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String
						.format("Process the message command fail, user(%s), place(%s), message(%s)",
								operator, placeId, command));
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"->OnMessage(): sessionId(%s), place(%s), command(%s).",
					session.getId(), placeId, command));
		}
	}

}
