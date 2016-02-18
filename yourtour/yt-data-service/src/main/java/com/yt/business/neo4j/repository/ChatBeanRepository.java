package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.ChatJoinHistoryBean;
import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;

public interface ChatBeanRepository extends GraphRepository<ChatSessionBean> {
	/**
	 * 返回指定聊天室中的聊天人员列表
	 * 
	 * @param roomNo
	 *            聊天室房间号码
	 * @return 聊天人员列表
	 */
	@Query("MATCH (room:ChatSessionBean)<-[:HAS]-(user:UserProfileBean) "
			+ "WITH room, user " + "WHERE room.chatRoomNo = {0} " + "RETURN user")
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
	@Query("MATCH (room:ChatSessionBean)<-[:HAS]-(user:UserProfileBean) "
			+ "WITH room, count(user) AS  users " + "ORDER BY users DESC "
			+ "RETURN room " + "SKIP {0} LIMIT {1}")
	public List<ChatSessionBean> getHotterChatRooms(int skip, int limit);

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
	@Query("MATCH (room:ChatSessionBean)<-[:HAS]-(user:UserProfileBean) "
			+ "WITH room, count(user) AS  users "
			+ "WHERE room.chatRoomType = {0} " + "ORDER BY users DESC "
			+ "RETURN room " + "SKIP {1} LIMIT {2}")
	public List<ChatSessionBean> getHotterChatRoomsByType(String type,
			int skip, int limit);

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
			+ "MATCH user<-[:RELATED]-(his:ChatJoinHistoryBean)-[:RELATED]->(room:ChatSessionBean)<-[:RELATED]-(msg:ChatMessageBean) "
			+ "WITH room, max(his.updatedTime) AS maxTime, count(msg) AS msgCount "
			+ "WHERE room.chatRoomNo = {0} AND msg.createdTime >= maxTime "
			+ "RETURN msgCount")
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
			+ "MATCH user<-[:RELATED]-(his:ChatJoinHistoryBean)-[:RELATED]->(room:ChatSessionBean)<-[:RELATED]-(msg:ChatMessageBean) "
			+ "WITH room, max(his.updatedTime) AS maxTime, count(msg) AS msgCount "
			+ "WHERE room.chatRoomNo = {0} AND msg.createdTime >= maxTime "
			+ "ORDER BY msg.createdTime " + "RETURN msg "
			+ "SKIP {2} LIMIT {3}")
	public List<ChatMessageBean> getUnreadMessages(String roomNo, long userId,
			int skip, int limit);

	/**
	 * 返回指定用户的历史聊天室列表，按照最近聊天排序。
	 * 
	 * @param userId
	 *            指定的用户ID
	 * @return 该用户历史聊天室列表
	 */
	@Query("START user=node({0}) "
			+ "MATCH user<-[:RELATED]-(his:ChatJoinHistoryBean)-[:RELATED]->(room:ChatSessionBean)<-[:RELATED]-(msg:ChatMessageBean) "
			+ "WITH room" + "ORDER BY msg.createdTime DESC "
			+ "RETURN DISTINCT room")
	public List<ChatSessionBean> getHistoryChatRooms(long userId);

	/**
	 * 返回指定用户最新创建的历史聊天室加入记录。
	 * 
	 * @param userId
	 *            指定的用户ID
	 * @return 该用户历史聊天室加入记录
	 */
	@Query("START user=node({0}) "
			+ "MATCH user<-[:RELATED]-(his:ChatJoinHistoryBean) "
			+ "ORDER BY his.createdTime DESC " + "RETURN his "
			+ "SKIP 0 LIMIT 1")
	public ChatJoinHistoryBean getNewestChatJoinRecord(long userId);
}
