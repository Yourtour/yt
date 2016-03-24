package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 该实体定义聊天室数据信息。
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2016年2月12日</td>
 * <td>John.Peng</td>
 * <td>Create</td>
 * </tr>
 * </table>
 * 
 * @author John.Peng
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_CHAT_SESSION")
@NodeEntity
public class ChatRoomBean extends BaseBeanImpl {
	private static final long serialVersionUID = -8555540274427305434L;

	// 聊天类型：行程聊天室、动态聊天室
	public enum ChatRoomType {
		ROUTE, // 行程聊天室
		DYNAMIC // 动态聊天室
	}

	@Indexed
	private String chatRoomNo = null; // 聊天室号码，如果是行程聊天室，则是前缀+行程的ID。

	@Indexed
	private String chatRoomName = null; // 聊天室名称

	private String chatRoomUrl = null; // 聊天室访问地址

	private ChatRoomType chatRoomType = ChatRoomType.DYNAMIC; // 聊天室类型

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = RouteMainBean.class, direction = Direction.OUTGOING)
	private transient RouteMainBean route = null; // 聊天室所属的行程，如果为空，则不属于任何一个行程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_MEMBER, type = UserProfileBean.class, isList = true, direction = Direction.INCOMING)
	private transient List<UserProfileBean> member = new Vector<UserProfileBean>(); // 聊天室成员

	private transient ChatMessageBean lastMessage; // 最后一条消息

	private transient int unreadMessageNum; // 未读消息

	public ChatRoomBean() {
		super();
	}

	public ChatRoomBean(Long id) {
		super(id);
	}

	public String getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(String chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

	public String getChatRoomUrl() {
		return chatRoomUrl;
	}

	public void setChatRoomUrl(String chatRoomUrl) {
		this.chatRoomUrl = chatRoomUrl;
	}

	public List<UserProfileBean> getUserProfiles() {
		return member;
	}

	public ChatRoomType getChatRoomType() {
		return chatRoomType;
	}

	public void setChatRoomType(ChatRoomType chatRoomType) {
		this.chatRoomType = chatRoomType;
	}

	public int getChatterNum() {
		return member.size();
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	public List<UserProfileBean> getMember() {
		return member;
	}

	public void setMember(List<UserProfileBean> member) {
		this.member = member;
	}

	public ChatMessageBean getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(ChatMessageBean lastMessage) {
		this.lastMessage = lastMessage;
	}

	public int getUnreadMessageNum() {
		return unreadMessageNum;
	}

	public void setUnreadMessageNum(int unreadMessageNum) {
		this.unreadMessageNum = unreadMessageNum;
	}

	/**
	 * 返回当前聊天室是否为一个动态聊天室，动态聊天室就是不属于任何一个目的地或行程，被用户动态创建的聊天室。
	 * 
	 * @return 如果是，返回true；否则返回false。
	 */
	public boolean isDynamicChatSession() {
		return this.route == null;
	}

}
