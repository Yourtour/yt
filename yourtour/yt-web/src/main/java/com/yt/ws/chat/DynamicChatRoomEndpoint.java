package com.yt.ws.chat;

import java.io.IOException;

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

import com.yt.ws.chat.ChatSessionUtils.ChatSessionTypeEnum;

@ServerEndpoint("/ws/chat/dynamic/{roomCode}/{userId}")
public class DynamicChatRoomEndpoint extends AbstractChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(DynamicChatRoomEndpoint.class);

	public DynamicChatRoomEndpoint() {
		super();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("roomCode") String roomCode,
			@PathParam("userId") Long userId) {
		super.openSession(ChatSessionTypeEnum.DYNAMIC_SESSION, roomCode,
				session, userId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("->OnOpen(): sessionId(%s), dynamic(%s).",
					session.getId(), roomCode));
		}
	}

	@OnClose
	public void onClose(Session session,
			@PathParam("roomCode") String roomCode,
			@PathParam("userId") Long userId, CloseReason reason) {
		super.closeSession(ChatSessionTypeEnum.DYNAMIC_SESSION, roomCode,
				session, userId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnClose(): sessionId(%s), dynamic(%s), close code(%s), close reason(%s).",
							session.getId(), roomCode, reason.getCloseCode()
									.toString(), reason.getReasonPhrase()));
		}
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		super.onError(session, throwable);
	}

	@OnMessage
	public void onMessage(Session session,
			@PathParam("roomCode") String roomCode,
			@PathParam("userId") Long userId, String command)
			throws IOException {
		super.processMessage(ChatSessionTypeEnum.DYNAMIC_SESSION, roomCode,
				session, userId, command);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"->OnMessage(): sessionId(%s), dynamic(%s), command(%s).",
					session.getId(), roomCode, command));
		}
	}

}
