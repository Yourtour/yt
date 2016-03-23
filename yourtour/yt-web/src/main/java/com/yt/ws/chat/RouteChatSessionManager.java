package com.yt.ws.chat;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

import com.yt.business.service.IRouteService;

public class RouteChatSessionManager {
	private static final Log LOG = LogFactory
			.getLog(RouteChatSessionManager.class);
	private static RouteChatSessionManager manager = null;

	public static RouteChatSessionManager getManager() {
		if (manager == null) {
			manager = new RouteChatSessionManager();
		}
		return manager;
	}

	// 所有行程聊天室的会话：行程ID，用户ID，WS会话。
	private Map<Long, Map<Long, Session>> chatRooms = null;
	private IRouteService routeService;

	private RouteChatSessionManager() {
		super();
		routeService = ContextLoader.getCurrentWebApplicationContext().getBean(
				IRouteService.class);
		chatRooms = new HashMap<Long, Map<Long, Session>>();
	}

	/**
	 * 将远端的WS会话加入到行程聊天室中
	 * 
	 * @param routeService
	 *            行程业务逻辑服务接口
	 * @param routeId
	 *            行程ID
	 * @param session
	 *            WS会话
	 * @param userId
	 *            用户ID
	 */
	public synchronized void addChatSession(Long routeId, Session session,
			Long userId) {
		if (routeId == null || session == null || userId == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("The route's id or session or user's id is null.");
			}
			return;
		}
		// 先判断该用户是否在行程聊天室的白名单中，否则不予加入
		if (!routeService.isRouteMember(routeId, userId)) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String
						.format("This user can not be join the chat room, route(%d), user(%d), session(%s).",
								routeId, userId, session.getId()));
			}
			return;
		}
		Map<Long, Session> list = null;
		if (chatRooms.containsKey(routeId)) {
			list = chatRooms.get(routeId);
		} else {
			list = new HashMap<Long, Session>();
		}
		list.put(userId, session);
		chatRooms.put(routeId, list);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("The user join the route chat room: route(%d), user(%d), session(%s).",
							routeId, userId, session.getId()));
		}
	}

	/**
	 * 将远端的WS会话从行程聊天室中删除
	 * 
	 * @param routeId
	 *            行程ID
	 * @param session
	 *            WS会话
	 * @param userId
	 *            用户ID
	 */
	public synchronized void delChatSession(Long routeId, Session session,
			Long userId) {
		if (chatRooms.containsKey(routeId)) {
			Map<Long, Session> list = chatRooms.get(routeId);
			list.remove(userId);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("The user exit the route chat room, route(%d), user(%d), session(%s).",
								routeId, userId, session.getId()));
			}
		}
	}

	/**
	 * 获取指定行程聊天室中的活动会话
	 * 
	 * @param routeId
	 *            行程ID
	 * @return 活动会话列表
	 */
	public Iterable<Session> getChatRoom(Long routeId) {
		return chatRooms.get(routeId).values();
	}

	/**
	 * 获取所有活动的行程聊天室
	 * 
	 * @return 行程聊天室集合
	 */
	public Map<Long, Map<Long, Session>> getChatRooms() {
		return chatRooms;
	}

}
