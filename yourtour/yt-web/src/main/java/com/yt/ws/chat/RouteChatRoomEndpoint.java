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

import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

@ServerEndpoint("/ws/chat/route/{routeId}")
public class RouteChatRoomEndpoint extends AbstractChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(RouteChatRoomEndpoint.class);

	public RouteChatRoomEndpoint() {
		super();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("routeId") String routeId) {
		super.openSession(ChatSessionTypeEnum.ROUTE_SESSION, routeId, session);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("->OnOpen(): sessionId(%s), route(%s).",
					session.getId(), routeId));
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("routeId") String routeId,
			CloseReason reason) {
		super.closeSession(ChatSessionTypeEnum.ROUTE_SESSION, routeId, session);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnClose(): sessionId(%s), route(%s), close code(%s), close reason(%s).",
							session.getId(), routeId, reason.getCloseCode()
									.toString(), reason.getReasonPhrase()));
		}
	}

	@OnMessage
	public void onMessage(Session session,
			@PathParam("routeId") String routeId, String command)
			throws IOException {
		super.processMessage(ChatSessionTypeEnum.ROUTE_SESSION, routeId,
				session, command);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"->OnMessage(): sessionId(%s), route(%s), command(%s).",
					session.getId(), routeId, command));
		}
	}

}
