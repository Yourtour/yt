package com.yt.business.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.exception.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatMessageBean.MessageType;
import com.yt.business.bean.ChatRoomBean;
import com.yt.business.bean.ChatRoomBean.ChatRoomType;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.neo4j.ChatBeanRepository;
import com.yt.business.repository.neo4j.ChatBeanRepository.ChatRoomTuple;
import com.yt.business.service.IChatService;
import com.yt.business.service.IRouteService;
import com.yt.neo4j.repository.CrudOperate;

@Service
public class ChatServiceImpl extends ServiceBase implements IChatService {
	private static final Log LOG = LogFactory.getLog(ChatServiceImpl.class);

	@Autowired
	private ChatBeanRepository repository;

	@Autowired
	private CrudOperate<ChatRoomBean> chatRoomCrudOperate;

	@Autowired
	private CrudOperate<ChatMessageBean> messageCrudOperate;

	@Autowired
	private CrudOperate<PlaceBean> placeCrudOperate;

	@Autowired
	private CrudOperate<RouteMainBean> routeCrudOperate;

	@Autowired
	private CrudOperate<UserProfileBean> userCrudOperate;

	@Autowired
	private IRouteService routeService;

	public ChatServiceImpl() {
		super();
	}

	@Override
	public List<UserProfileBean> getChatters(String roomNo) throws Exception {
		return repository.getChatters(roomNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IChatService#getChatRooms(long)
	 */
	@Override
	public List<ChatRoomBean> getChatRooms(long userId) throws Exception {
		List<ChatRoomTuple> tuples = repository.getHistoryChatRooms(userId);
		List<ChatRoomBean> list = new Vector<ChatRoomBean>();
		for (ChatRoomTuple tuple : tuples) {
			if (tuple == null) {
				continue;
			}
			ChatRoomBean bean = tuple.getChatRoom();
			bean.setUnreadMessageNum(tuple.getUnreadMessageCount());
			bean.setLastMessage(tuple.getLastMessage());
			list.add(bean);
		}
		return list;
	}

	@Override
	public List<ChatRoomBean> getTopNChatRooms(int n) throws Exception {
		return repository.getHotterChatRooms(0, n);
	}

	@Override
	public List<ChatRoomBean> getTopNRouteChatRooms(int n) throws Exception {
		return repository.getHotterChatRoomsByType(ChatRoomType.ROUTE.name(),
				0, n);
	}

	@Override
	public List<ChatRoomBean> getTopNDynamicChatRooms(int n) throws Exception {
		return repository.getHotterChatRoomsByType(ChatRoomType.DYNAMIC.name(),
				0, n);
	}

	@Override
	public long getUnreadMessageCount(String roomNo, long userId)
			throws Exception {
		return repository.getUnreadMessageCount(roomNo, userId);
	}

	@Override
	public List<ChatMessageBean> getUnreadMessages(String roomNo, long userId,
			int skip, int limit) throws Exception {
		return repository.getUnreadMessages(roomNo, userId, skip, limit);
	}

	@Override
	public List<ChatMessageBean> getUnreadMessages(String roomNo,
			Long nextCursor, long userId) throws Exception {
		// TODO 分页获取未阅读的消息
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.service.IChatService#createRouteChatRoom(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public ChatRoomBean createRouteChatRoom(Long routeId, Long userId)
			throws Exception {
		if (routeId == null || userId == null) {
			if (LOG.isErrorEnabled()) {
				LOG.error("The route's id or user's id is null.");
			}
			throw new NullArgumentException();
		}
		RouteMainBean route = routeService.getRouteInfo(routeId, false);
		if (route == null) {
			throw new Exception(String.format(
					"The route is not exist, route(%d).", routeId));
		}

		String chatRoomNO = String.format("r%d", routeId);
		ChatRoomBean bean = chatRoomCrudOperate.get("chatRoomNo", chatRoomNO);
		if (bean == null) {
			// 聊天室不存在，创建一个新的
			bean = new ChatRoomBean();
			bean.setChatRoomNo(chatRoomNO);
			bean.setChatRoomType(ChatRoomType.ROUTE);
			bean.setChatRoomUrl(String.format("/ws/chat/route/%s", chatRoomNO));
		}

		// 获取行程中的成员
		List<UserProfileBean> users = routeService.getRouteMember(routeId);
		bean.setChatRoomName(route.getName());
		bean.setMember(users);
		super.updateBaseInfo(bean, userId);
		chatRoomCrudOperate.save(bean);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create a route chat room successfully, route(%d), roomNO(%s).",
							routeId, bean.getChatRoomNo()));
		}
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.service.IChatService#createDynamicChatRoom(java.lang.
	 * Long, java.util.List)
	 */
	@Override
	public ChatRoomBean createDynamicChatRoom(Long userId, List<Long> member)
			throws Exception {
		ChatRoomBean bean = new ChatRoomBean();
		bean.setChatRoomNo(String.format("d%s", UUID.randomUUID().toString()));
		bean.setChatRoomType(ChatRoomType.DYNAMIC);
		bean.setChatRoomUrl(String.format("/ws/chat/dynamic/%s",
				bean.getChatRoomNo()));

		List<UserProfileBean> users = new Vector<UserProfileBean>();
		for (Long id : member) {
			UserProfileBean user = userCrudOperate.get(id);
			if (user != null) {
				users.add(user);
			}
		}
		bean.setMember(users);
		super.updateBaseInfo(bean, userId);
		chatRoomCrudOperate.save(bean);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create a dynamic chat room successfully, user(%d), roomNO(%s).",
							userId, bean.getChatRoomNo()));
		}
		return bean;
	}

	@Override
	public ChatRoomBean addDynamicChatters(String chatRoomNO,
			List<Long> chatters, Long userId) throws Exception {
		ChatRoomBean bean = chatRoomCrudOperate.get("chatRoomNo", chatRoomNO);
		if (bean == null) {
			throw new Exception(String.format(
					"The dynamic chat room not exist, roomNO(%s).", chatRoomNO));
		}
	
		List<UserProfileBean> users = new Vector<UserProfileBean>();
		for (Long id : chatters) {
			UserProfileBean user = userCrudOperate.get(id);
			if (user != null) {
				users.add(user);
			}
		}
		bean.setMember(users);
		super.updateBaseInfo(bean, userId);
		chatRoomCrudOperate.save(bean);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create a dynamic chat room successfully, user(%d), roomNO(%s).",
							userId, bean.getChatRoomNo()));
		}
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.service.IChatService#delDynamicChatters(java.lang.String,
	 * java.util.List)
	 */
	@Override
	public ChatRoomBean delDynamicChatters(String chatRoomNO,
			List<Long> chatters, Long userId) throws Exception {
		ChatRoomBean bean = chatRoomCrudOperate.get("chatRoomNo", chatRoomNO);
		if (bean == null) {
			throw new Exception(String.format(
					"The dynamic chat room not exist, roomNO(%s).", chatRoomNO));
		}
	
		List<UserProfileBean> users = new Vector<UserProfileBean>();
		for (UserProfileBean user : bean.getMember()) {
			if (user == null) {
				continue;
			}
			long srcId = user.getId();
			boolean found = false;
			for (Long id : chatters) {
				if (srcId == id) {
					found = true;
					break;
				}
			}
			if (!found) {
				users.add(user);
			}
		}
		bean.setMember(users);
		super.updateBaseInfo(bean, userId);
		chatRoomCrudOperate.save(bean);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create a dynamic chat room successfully, user(%d), roomNO(%s).",
							userId, bean.getChatRoomNo()));
		}
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.service.IChatService#saveRouteChatMessage(java.lang.Long,
	 * java.lang.Long, com.yt.business.bean.ChatMessageBean.MessageType,
	 * java.lang.String, boolean)
	 */
	@Override
	public ChatMessageBean saveRouteChatMessage(Long routeId, Long userId,
			MessageType messageType, String content, boolean isNotice)
			throws Exception {
		String roomNo = String.format("r%d", routeId);
		ChatRoomBean chatRoomBean = chatRoomCrudOperate.get("chatRoomNo",
				roomNo);
		if (chatRoomBean == null) {
			throw new Exception(String.format(
					"The route chat room not exist, route(%d).", routeId));
		}

		UserProfileBean userProfile = userCrudOperate.get(userId);
		if (userProfile == null) {
			throw new Exception(String.format("The user not exist, user(%d).",
					userId));
		}

		ChatMessageBean message = new ChatMessageBean();
		message.setNotice(isNotice);
		message.setMessageType(messageType);
		message.setContent(content);
		message.setChatRoom(chatRoomBean);
		message.setSender(userProfile);
		super.updateBaseInfo(message, userId);
		messageCrudOperate.save(message, true);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Save a message successfully, route(%d), user(%d), message(%s)",
							routeId, userId, content));
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.service.IChatService#saveDynamicChatMessage(java.lang
	 * .String, java.lang.Long,
	 * com.yt.business.bean.ChatMessageBean.MessageType, java.lang.String,
	 * boolean)
	 */
	@Override
	public ChatMessageBean saveDynamicChatMessage(String chatRoomNO,
			Long userId, MessageType messageType, String content,
			boolean isNotice) throws Exception {
		ChatRoomBean chatRoomBean = chatRoomCrudOperate.get("chatRoomNo",
				chatRoomNO);
		if (chatRoomBean == null) {
			throw new Exception(String.format(
					"The dynamic chat room not exist, roomNO(%s).", chatRoomNO));
		}

		UserProfileBean userProfile = userCrudOperate.get(userId);
		if (userProfile == null) {
			throw new Exception(String.format("The user not exist, user(%d).",
					userId));
		}

		ChatMessageBean message = new ChatMessageBean();
		message.setNotice(isNotice);
		message.setMessageType(messageType);
		message.setContent(content);
		message.setChatRoom(chatRoomBean);
		message.setSender(userProfile);
		super.updateBaseInfo(message, userId);
		messageCrudOperate.save(message, true);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Save a message successfully, roomNO(%s), user(%d), message(%s)",
							chatRoomNO, userId, content));
		}
		return message;
	}

}
