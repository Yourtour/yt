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

@ServerEndpoint("/ws/chat/place/{placeId}/{userId}")
public class PlaceChatRoomEndpoint extends AbstractChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(PlaceChatRoomEndpoint.class);
	
	public PlaceChatRoomEndpoint() {
		super();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("placeId") String placeId,
			@PathParam("userId") String userId) {
		super.openSession(ChatSessionTypeEnum.PLACE_SESSION, placeId, session,
				userId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("->OnOpen(): sessionId(%s), place(%s).",
					session.getId(), placeId));
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("placeId") String placeId,
			@PathParam("userId") String userId, CloseReason reason) {
		super.closeSession(ChatSessionTypeEnum.PLACE_SESSION, placeId, session,
				userId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnClose(): sessionId(%s), place(%s), close code(%s), close reason(%s).",
							session.getId(), placeId, reason.getCloseCode()
									.toString(), reason.getReasonPhrase()));
		}
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		super.onError(session, throwable);
	}

	@OnMessage
	public void onMessage(Session session,
			@PathParam("placeId") String placeId,
			@PathParam("userId") String userId, String command)
			throws IOException {
		super.processMessage(ChatSessionTypeEnum.PLACE_SESSION, placeId,
				session, userId, command);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"->OnMessage(): sessionId(%s), place(%s), command(%s).",
					session.getId(), placeId, command));
		}
	}

}
