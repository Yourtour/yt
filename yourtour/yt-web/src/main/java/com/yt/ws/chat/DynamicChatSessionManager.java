package com.yt.ws.chat;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DynamicChatSessionManager {
	private static final Log LOG = LogFactory
			.getLog(DynamicChatSessionManager.class);
	private static DynamicChatSessionManager manager = null;

	public static DynamicChatSessionManager getManager() {
		if (manager == null) {
			manager = new DynamicChatSessionManager();
		}
		return manager;
	}

	// 所有动态聊天室的会话：聊天室代码，用户ID，WS会话。
	private Map<String, Map<Long, Session>> chatRooms = null;

	private DynamicChatSessionManager() {
		super();
		chatRooms = new HashMap<String, Map<Long, Session>>();
	}

	/**
	 * 将远端的WS会话加入到动态聊天室中
	 * 
	 * @param routeService
	 *            行程业务逻辑服务接口
	 * @param roomNO
	 *            聊天室代码
	 * @param session
	 *            WS会话
	 * @param userId
	 *            用户ID
	 */
	public synchronized void addChatSession(String roomNO, Session session,
			Long userId) {
		if (roomNO == null || session == null || userId == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The room'id or session or user's id is null.");
			}
			return;
		}
		Map<Long, Session> list = null;
		if (chatRooms.containsKey(roomNO)) {
			list = chatRooms.get(roomNO);
		} else {
			list = new HashMap<Long, Session>();
		}
		list.put(userId, session);
		chatRooms.put(roomNO, list);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("The user join the dynamic chat room: roomNO(%s), user(%d), session(%s).",
							roomNO, userId, session.getId()));
		}
	}

	/**
	 * 将远端的WS会话从动态聊天室中删除
	 * 
	 * @param roomNO
	 *            聊天室代码
	 * @param session
	 *            WS会话
	 * @param userId
	 *            用户ID
	 */
	public synchronized void delChatSession(String roomNO, Session session,
			Long userId) {
		if (chatRooms.containsKey(roomNO)) {
			Map<Long, Session> list = chatRooms.get(roomNO);
			list.remove(userId);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("The user exit the route chat room, roomNO(%s), user(%d), session(%s).",
								roomNO, userId, session.getId()));
			}
		}
	}

	/**
	 * 获取指定动态聊天室中的活动会话
	 * 
	 * @param roomNO
	 *            聊天室代码
	 * @return 活动会话列表
	 */
	public Iterable<Session> getChatRoom(String roomNO) {
		return chatRooms.get(roomNO).values();
	}

	/**
	 * 获取所有活动的动态聊天室
	 * 
	 * @return 动态聊天室集合
	 */
	public Map<String, Map<Long, Session>> getChatRooms() {
		return chatRooms;
	}

}
