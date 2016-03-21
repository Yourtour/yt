package com.yt.business.service;

import com.yt.business.bean.ChatJoinHistoryBean;
import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;

import java.util.List;

public interface IChatService {
	/**
	 * 根据指定的目的的ID打开对应的目的的聊天室，如该聊天室不存在，则创建一个新的聊天室。
	 * 
	 * @param placeId
	 *            目的的ID
	 * @param userId
	 *            操作人员ID
	 * @return 成功返回目的的聊天室会话对象，否在返回null。
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public ChatSessionBean openPlaceChatRoom(long placeId, long userId)
			throws Exception;

	/**
	 * 根据指定的行程ID打开对应的行程聊天室，如该聊天室不存在，则创建一个行程聊天室。
	 * 
	 * @param routeId
	 *            行程ID
	 * @param userId
	 *            操作人员ID
	 * @return 成功返回目的的聊天室会话对象，否在返回null。
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public ChatSessionBean openRouteChatRoom(long routeId, long userId)
			throws Exception;

	/**
	 * 根据指定的动态聊天室号码打开指定的动态聊天室，如该聊天室不存在，则创建一个新的动态聊天室。
	 * 
	 * @param roomNo
	 *            聊天室房间号，一般是以“d”开头的UUID号码。
	 * @param userId
	 *            操作人员ID
	 * @return 成功返回目的的聊天室会话对象，否在返回null。
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public ChatSessionBean openDynamicChatRoom(String roomNo, long userId)
			throws Exception;

	/**
	 * 返回指定聊天室中的聊天人员列表。
	 * 
	 * @param roomNo
	 *            聊天室房间号码
	 * @return 聊天人员对象列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<UserProfileBean> getChatters(String roomNo) throws Exception;

	/**
	 * 获取最热的前N名聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatSessionBean> getTopNChatRooms(int n) throws Exception;

	/**
	 * 获取最热的前N名目的的聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的目的的聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatSessionBean> getTopNPlaceChatRooms(int n) throws Exception;

	/**
	 * 获取最热的前N名行程聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的行程聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatSessionBean> getTopNRouteChatRooms(int n) throws Exception;

	/**
	 * 获取最热的前N名动态聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的动态聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatSessionBean> getTopNDynamicChatRooms(int n)
			throws Exception;

	/**
	 * 返回指定聊天室中指定用户ID的所有未阅读消息总数
	 * 
	 * @param roomNo
	 *            聊天室房间号
	 * @param userId
	 *            指定的用户ID
	 * @return 满足条件的消息总数
	 * @throws Exception
	 *             操作过程中发生异常
	 */
	public long getUnreadMessageCount(String roomNo, long userId)
			throws Exception;

	/**
	 * 返回指定聊天室中指定用户ID的未阅读消息，可以分页获取。
	 * 
	 * @param roomNo
	 * @param nextCursor
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ChatMessageBean> getUnreadMessages(String roomNo,
			Long nextCursor, long userId) throws Exception;

	/**
	 * 返回指定聊天室中指定用户ID的未阅读消息，可以分页获取。
	 * 
	 * @param roomNo
	 *            聊天室房间号
	 * @param userId
	 *            指定的用户ID
	 * @param skip
	 *            跳过的记录条数
	 * @param limit
	 *            获取的记录条数
	 * @return 符合条件的消息记录列表
	 * @throws Exception
	 *             操作过程中发生异常
	 */
	public List<ChatMessageBean> getUnreadMessages(String roomNo, long userId,
			int skip, int limit) throws Exception;

	/**
	 * 返回指定用户的历史聊天室列表，按照最近聊天排序。
	 * 
	 * @param userId
	 *            指定的用户ID
	 * @return 该用户历史聊天室列表
	 * @throws Exception
	 *             操作过程中发生异常
	 */
	public List<ChatSessionBean> getHistoryChatRooms(long userId)
			throws Exception;

	/**
	 * 返回指定用户最新创建的历史聊天室加入记录。
	 * 
	 * @param userId
	 *            指定的用户ID
	 * @return 该用户历史聊天室加入记录
	 * @throws Exception
	 *             操作过程中发生异常
	 */
	public ChatJoinHistoryBean getNewestChatJoinRecord(long userId)
			throws Exception;

	public void saveChatJoinRecord(long userId, ChatSessionBean sessionBean)
			throws Exception;

	public ChatMessageBean saveChatMessage(boolean isNotice,
			String textMessage, ChatSessionBean chatSessionBean, long userId)
			throws Exception;
}
