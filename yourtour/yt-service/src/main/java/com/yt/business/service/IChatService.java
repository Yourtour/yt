package com.yt.business.service;

import java.util.List;

import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatMessageBean.MessageType;
import com.yt.business.bean.ChatRoomBean;
import com.yt.business.bean.UserProfileBean;

public interface IChatService {
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
	 * 返回指定用户的历史聊天室列表，按照最近聊天排序。
	 * 
	 * @param userId
	 *            指定的用户ID
	 * @return 该用户历史聊天室列表
	 * @throws Exception
	 *             操作过程中发生异常
	 */
	public List<ChatRoomBean> getChatRooms(long userId) throws Exception;

	/**
	 * 获取最热的前N名聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatRoomBean> getTopNChatRooms(int n) throws Exception;

	/**
	 * 获取最热的前N名行程聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的行程聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatRoomBean> getTopNRouteChatRooms(int n) throws Exception;

	/**
	 * 获取最热的前N名动态聊天室
	 * 
	 * @param n
	 *            名次数
	 * @return 最热的动态聊天室列表
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public List<ChatRoomBean> getTopNDynamicChatRooms(int n) throws Exception;

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
	 * 为行程创建一个专属聊天室
	 * 
	 * @param routeId
	 *            行程ID
	 * @param userId
	 *            用户ID
	 * @return 创建好的行程聊天室
	 * @throws Exception
	 *             创建过程中发生的异常
	 */
	public ChatRoomBean createRouteChatRoom(Long routeId, Long userId)
			throws Exception;

	/**
	 * 为某群用户创建一个动态聊天室
	 * 
	 * @param userId
	 *            用户ID
	 * @param member
	 *            聊天成员，用户ID的集合
	 * @return 创建好的动态聊天室
	 * @throws Exception
	 *             创建过程中发生的异常
	 */
	public ChatRoomBean createDynamicChatRoom(Long userId, List<Long> member)
			throws Exception;

	/**
	 * 向指定的动态聊天室添加聊天者
	 * 
	 * @param chatRoomNO
	 *            动态聊天室代码
	 * @param chatters
	 *            聊天者ID列表
	 * @param userId
	 *            操作用户ID
	 * @return 添加后的聊天室
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public ChatRoomBean addDynamicChatters(String chatRoomNO,
			List<Long> chatters, Long userId) throws Exception;

	/**
	 * 在指定的动态聊天室删除聊天者
	 * 
	 * @param chatRoomNO
	 *            动态聊天室代码
	 * @param chatters
	 *            聊天者ID列表
	 * @param userId
	 *            操作用户ID
	 * @return 添加后的聊天室
	 * @throws Exception
	 *             操作过程中发生的异常
	 */
	public ChatRoomBean delDynamicChatters(String chatRoomNO,
			List<Long> chatters, Long userId) throws Exception;

	/**
	 * 保存一条行程聊天室中的聊天消息
	 * 
	 * @param routeId
	 *            行程ID
	 * @param userId
	 *            用户ID
	 * @param messageType
	 *            消息类型
	 * @param content
	 *            聊天内容
	 * @param isNotice
	 *            是否为通告内容
	 * @return 保存好的聊天消息
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public ChatMessageBean saveRouteChatMessage(Long routeId, Long userId,
			MessageType messageType, String content, boolean isNotice)
			throws Exception;

	/**
	 * 保存一条动态聊天室的聊天消息
	 * 
	 * @param chatRoomNO
	 *            动态聊天室号码，d打头的UUID
	 * @param userId
	 *            用户ID
	 * @param messageType
	 *            消息类型
	 * @param content
	 *            聊天内容
	 * @param isNotice
	 *            是否为通告内容
	 * @return 保存好的聊天消息
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public ChatMessageBean saveDynamicChatMessage(String chatRoomNO,
			Long userId, MessageType messageType, String content,
			boolean isNotice) throws Exception;

}
