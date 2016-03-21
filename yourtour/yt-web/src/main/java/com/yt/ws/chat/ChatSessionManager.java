package com.yt.ws.chat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChatSessionManager {
	private static final Log LOG = LogFactory.getLog(ChatSessionManager.class);
	private static ChatSessionManager manager = null;

	public static ChatSessionManager getManager() {
		if (manager == null) {
			manager = new ChatSessionManager();
		}
		return manager;
	}

	private Map<String, Map<Long, Session>> placeChatRooms = null,
			routeChatRooms = null, dynamicChatRooms = null;

	private Map<String, Set<String>> dynamicWhiteList = null;

	private ChatSessionManager() {
		super();
		placeChatRooms = new HashMap<String, Map<Long, Session>>();
		routeChatRooms = new HashMap<String, Map<Long, Session>>();
		dynamicChatRooms = new HashMap<String, Map<Long, Session>>();
		dynamicWhiteList = new HashMap<String, Set<String>>();
	}

	public synchronized void addPlaceChatSession(String placeCode,
			Session session, long userId) {
		Map<Long, Session> list = null;
		if (placeChatRooms.containsKey(placeCode)) {
			list = placeChatRooms.get(placeCode);
		} else {
			list = new HashMap<Long, Session>();
		}
		list.put(userId, session);
		placeChatRooms.put(placeCode, list);
	}

	public synchronized void delPlaceChatSession(String placeCode,
			Session session, long userId) {
		if (placeChatRooms.containsKey(placeCode)) {
			Map<Long, Session> list = placeChatRooms.get(placeCode);
			list.remove(userId);
		}
	}

	public Iterable<Session> getPlaceChatRoom(String placeCode) {
		return placeChatRooms.get(placeCode).values();
	}

	public Map<String, Map<Long, Session>> getPlaceChatRooms() {
		return placeChatRooms;
	}

	public synchronized void addRouteChatSession(String routeCode,
			Session session, long userId) {
		Map<Long, Session> list = null;
		if (routeChatRooms.containsKey(routeCode)) {
			list = routeChatRooms.get(routeCode);
		} else {
			list = new HashMap<Long, Session>();
		}
		list.put(userId, session);
		routeChatRooms.put(routeCode, list);
	}

	public synchronized void delRouteChatSession(String routeCode,
			Session session, long userId) {
		if (routeChatRooms.containsKey(routeCode)) {
			Map<Long, Session> list = routeChatRooms.get(routeCode);
			list.remove(userId);
		}
	}

	public Iterable<Session> getRouteChatRoom(String routeCode) {
		return routeChatRooms.get(routeCode).values();
	}

	public Map<String, Map<Long, Session>> getRouteChatRooms() {
		return routeChatRooms;
	}

	public synchronized void addDynamicChatSession(String roomCode,
			Session session, long userId) {
		Map<Long, Session> list = null;
		if (dynamicChatRooms.containsKey(roomCode)) {
			list = dynamicChatRooms.get(roomCode);
		} else {
			list = new HashMap<Long, Session>();
		}
		// 判断该session是否在白名单中，否则不加入
		if (dynamicWhiteList.get(roomCode) != null
				&& dynamicWhiteList.get(roomCode).contains(userId)) {
			list.put(userId, session);
			dynamicChatRooms.put(roomCode, list);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"An validated user(%d) request join the chat session.",
						userId));
			}
		} else {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String
						.format("An invalidated user(%d) request join the chat session.",
								userId));
			}
		}
	}

	public synchronized void delDynamicChatSession(String roomCode,
			Session session, long userId) {
		if (dynamicChatRooms.containsKey(roomCode)) {
			Map<Long, Session> list = dynamicChatRooms.get(roomCode);
			list.remove(userId);
			// 判断如果已经没有聊天着后，清空白名单
			if (list.isEmpty() && dynamicWhiteList.get(roomCode) != null) {
				dynamicWhiteList.get(roomCode).clear();
			}
		}
	}

	public synchronized void addDynamiceChatWhiteList(String roomCode,
			String userCode) {
		Set<String> whiteList = null;
		if (dynamicWhiteList.containsKey(roomCode)) {
			whiteList = dynamicWhiteList.get(roomCode);
		} else {
			whiteList = new HashSet<String>();
		}
		if (!whiteList.contains(userCode)) {
			whiteList.add(userCode);
		}
		dynamicWhiteList.put(roomCode, whiteList);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Add a white list(user=%s) into chat room(%s) successfully.",
							userCode, roomCode));
		}
	}

	public synchronized void delDynamiceChatWhiteList(String roomCode,
			String userCode, long userId) {
		Set<String> whiteList = null;
		if (dynamicWhiteList.containsKey(roomCode)) {
			whiteList = dynamicWhiteList.get(roomCode);
		} else {
			whiteList = new HashSet<String>();
		}
		if (whiteList.contains(userCode)) {
			whiteList.remove(userCode);
			// 真正踢出会话
			Map<Long, Session> sessions = dynamicChatRooms.get(roomCode);
			if (sessions != null && sessions.containsKey(userCode)) {
				Session session = sessions.get(userCode);
				if (session != null) {
					try {
						session.close(new CloseReason(
								CloseReason.CloseCodes.CANNOT_ACCEPT,
								String.format(
										"The user(%s) was be kicked out by user(%d).",
										userCode, userId)));
					} catch (Exception ex) {
						if (LOG.isErrorEnabled()) {
							LOG.error(
									String.format(
											"Close the websocket session fail. user: %s.",
											userCode), ex);
						}
					}
				}
			}
		}
		dynamicWhiteList.put(roomCode, whiteList);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Remove a white list(user=%s) into chat room(%s) successfully.",
							userCode, roomCode));
		}
	}

	public Iterable<Session> getDynamicChatRoom(String roomCode) {
		return dynamicChatRooms.get(roomCode).values();
	}

	public Map<String, Map<Long, Session>> getDynamicChatRooms() {
		return dynamicChatRooms;
	}

}
