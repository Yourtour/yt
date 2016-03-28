package com.yt.ws.chat;

import java.util.Date;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSONObject;
import com.yt.business.bean.ChatMessageBean;
import com.yt.business.service.IChatService;
import com.yt.ws.chat.message.AbstractChatMessage;

@ServerEndpoint("/ws/chat/route/{routeId}/{userId}")
public class RouteChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(RouteChatRoomEndpoint.class);

	private IChatService chatService = null;

	public RouteChatRoomEndpoint() {
		super();
		chatService = ContextLoader.getCurrentWebApplicationContext().getBean(
				IChatService.class);
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("routeId") Long routeId,
			@PathParam("userId") Long userId) throws Exception {
		RouteChatSessionManager.getManager().addChatSession(routeId, session,
				userId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"->OnOpen(): sessionId(%s), route(%d), user(%d).",
					session.getId(), routeId, userId));
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("routeId") Long routeId,
			@PathParam("userId") Long userId, CloseReason reason)
			throws Exception {
		RouteChatSessionManager.getManager().delChatSession(routeId, session,
				userId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnClose(): sessionId(%s), route(%s), close code(%s), close reason(%s).",
							session.getId(), routeId, reason.getCloseCode()
									.toString(), reason.getReasonPhrase()));
		}
	}

	@OnError
	public void onError(Session session, @PathParam("routeId") Long routeId,
			@PathParam("userId") Long userId, Throwable throwable) {
		if (LOG.isErrorEnabled()) {
			LOG.error(
					String.format(
							"The chat room throw a exception, route(%d), user(%d), session(%s).",
							routeId, userId, session.getId()), throwable);
		}
	}

	@OnMessage
	public void onMessage(Session session, @PathParam("routeId") Long routeId,
			@PathParam("userId") Long userId, String content) throws Exception {
		// 将接收到的文本转化为指令对象并保存到数据库
		AbstractChatMessage command = ChatSessionUtils
				.transformCommand(content);
		ChatMessageBean messageBean = chatService.saveRouteChatMessage(routeId,
				userId, command.getType(), command.getContent(),
				command.isNotice());

		// 循环将收到的消息转发到聊天室中其他人员
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id", messageBean.getSender().getId());
		jsonUser.put("nickName", messageBean.getSender().getNickName());
		jsonUser.put("imageUrl", messageBean.getSender().getImageUrl());
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("contentType", command.getType().name());
		jsonMessage.put("content", command.getContent());
		jsonMessage.put("user", jsonUser);
		int num = 0;
		for (Session s : RouteChatSessionManager.getManager().getChatRoom(
				routeId)) {
			jsonMessage.put("sendTime", new Date().getTime());
			s.getBasicRemote().sendText(jsonMessage.toJSONString());
			num++;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnMessage(): sessionId(%s), route(%d), command(%s), sendNum(%d).",
							session.getId(), routeId, command, num));
		}
	}

}
