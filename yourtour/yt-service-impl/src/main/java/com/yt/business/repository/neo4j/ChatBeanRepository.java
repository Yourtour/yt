package com.yt.business.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatRoomBean;
import com.yt.business.bean.UserProfileBean;

public interface ChatBeanRepository extends GraphRepository<ChatRoomBean> {
	/**
	 * 返回指定聊天室中的聊天人员列表
	 * 
	 * @param roomNo
	 *            聊天室房间号码
	 * @return 聊天人员列表
	 */
	@Query("MATCH (room:ChatRoomBean)<-[:MEMBER]-(user:UserProfileBean) "
			+ "WITH room, user WHERE room.chatRoomNo = {0} RETURN user")
	public List<UserProfileBean> getChatters(String roomNo);

	/**
	 * 返回最热门的聊天室，在所有聊天室中排序。
	 * 
	 * @param skip
	 *            跳过的记录条数
	 * @param limit
	 *            获取的记录条数
	 * @return 根据聊天用户数从大到小排序的聊天室列表
	 */
	@Query("MATCH (room:ChatRoomBean)<-[:MEMBER]-(user:UserProfileBean) "
			+ "WITH room, count(user) AS  users ORDER BY users DESC "
			+ "RETURN room SKIP {0} LIMIT {1}")
	public List<ChatRoomBean> getHotterChatRooms(int skip, int limit);

	/**
	 * 返回最热门的聊天室，在指定的类型的聊天室中排序。
	 * 
	 * @param type
	 *            聊天室类别，只能是：CHAT_TYPE_PLACE、CHAT_TYPE_ROUTE、
	 *            CHAT_TYPE_DYNAMIC中的一个。
	 * @param skip
	 *            跳过的记录条数
	 * @param limit
	 *            获取的记录条数
	 * @return 根据聊天用户数从大到小排序的聊天室列表
	 */
	@Query("MATCH (room:ChatRoomBean)<-[:MEMBER]-(user:UserProfileBean) "
			+ "WITH room, count(user) AS  users "
			+ "WHERE room.chatRoomType = {0} ORDER BY users DESC "
			+ "RETURN room SKIP {1} LIMIT {2}")
	public List<ChatRoomBean> getHotterChatRoomsByType(String type, int skip,
			int limit);

	/**
	 * 返回指定聊天室中指定用户ID的所有未阅读消息总数
	 * 
	 * @param roomNo
	 *            聊天室房间号
	 * @param userId
	 *            指定的用户ID
	 * @return 满足条件的消息总数
	 */
	@Query("START user=node({1}) "
			+ "MATCH user-[r:MEMBER]->(room:ChatRoomBean)<-[:RELATED]-(message:ChatMessageBean) "
			+ "WITH room, r.exitTime AS lastTime, count(message) AS unreadMessageCount "
			+ "WHERE room.chatRoomNo = {0} AND message.createdTime >= lastTime "
			+ "RETURN unreadMessageCount")
	public long getUnreadMessageCount(String roomNo, long userId);

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
	 */
	@Query("START user=node({1}) "
			+ "MATCH user-[r:MEMBER]->(room:ChatRoomBean)<-[:RELATED]-(message:ChatMessageBean) "
			+ "WITH room, r.exitTime AS lastTime, count(message) AS unreadMessageCount "
			+ "WHERE room.chatRoomNo = {0} AND message.createdTime >= lastTime "
			+ "ORDER BY message.createdTime " + "RETURN message "
			+ "SKIP {2} LIMIT {3}")
	public List<ChatMessageBean> getUnreadMessages(String roomNo, long userId,
			int skip, int limit);

	/**
	 * 聊天室及相关信息定义
	 */
	@QueryResult
	public class ChatRoomTuple {
		@ResultColumn("room")
		private ChatRoomBean chatRoom;

		@ResultColumn("unreadMessageCount")
		private int unreadMessageCount;

		@ResultColumn("message")
		private ChatMessageBean lastMessage;

		public ChatRoomTuple() {
			super();
		}

		public ChatRoomBean getChatRoom() {
			return chatRoom;
		}

		public void setChatRoom(ChatRoomBean chatRoom) {
			this.chatRoom = chatRoom;
		}

		public int getUnreadMessageCount() {
			return unreadMessageCount;
		}

		public void setUnreadMessageCount(int unreadMessageCount) {
			this.unreadMessageCount = unreadMessageCount;
		}

		public ChatMessageBean getLastMessage() {
			return lastMessage;
		}

		public void setLastMessage(ChatMessageBean lastMessage) {
			this.lastMessage = lastMessage;
		}
	}

	/**
	 * 返回指定用户的历史聊天室列表，按照最近聊天排序。
	 * 
	 * @param userId
	 *            指定的用户ID
	 * @return 该用户历史聊天室列表
	 */
	@Query("START user=node({0}) "
			+ "MATCH user-[r:MEMBER]->(room:ChatRoomBean)<-[:RELATED]-(message:ChatMessageBean) "
			+ "WITH room, r.exitTime AS lastTime, count(message) AS unreadMessageCount "
			+ "WHERE msg.createTime >= lastTime "
			+ "ORDER BY message.createdTime DESC "
			+ "RETURN cr, unreadMessageCount, message LIMIT 1")
	public List<ChatRoomTuple> getHistoryChatRooms(long userId);

}
