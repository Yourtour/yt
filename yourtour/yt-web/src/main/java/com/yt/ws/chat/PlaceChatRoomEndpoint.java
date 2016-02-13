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

@ServerEndpoint("/ws/chat/place/{placeId}")
public class PlaceChatRoomEndpoint extends AbstractChatRoomEndpoint {
	private static final Log LOG = LogFactory
			.getLog(PlaceChatRoomEndpoint.class);

	public PlaceChatRoomEndpoint() {
		super();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("placeId") String placeId) {
		super.openSession(ChatSessionTypeEnum.PLACE_SESSION, placeId, session);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("->OnOpen(): sessionId(%s), place(%s).",
					session.getId(), placeId));
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("placeId") String placeId,
			CloseReason reason) {
		super.closeSession(ChatSessionTypeEnum.PLACE_SESSION, placeId, session);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("->OnClose(): sessionId(%s), place(%s), close code(%s), close reason(%s).",
							session.getId(), placeId, reason.getCloseCode()
									.toString(), reason.getReasonPhrase()));
		}
	}

	@OnMessage
	public void onMessage(Session session,
			@PathParam("placeId") String placeId, String command)
			throws IOException {
		super.processMessage(ChatSessionTypeEnum.PLACE_SESSION, placeId,
				session, command);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"->OnMessage(): sessionId(%s), place(%s), command(%s).",
					session.getId(), placeId, command));
		}
	}

}
