package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 该实体定义聊天消息数据信息。
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
@HbaseTable(name = "T_CHAT_MSG")
@NodeEntity
public class ChatMessageBean extends BaseBeanImpl {
	private static final long serialVersionUID = -8960391956314705776L;
	private static final String INDEX_NAME = "chatMessage"; // 定义了本实体中全文检索的索引名称。

	public enum MessageType {
		TEXT, // 文本消息
		IMAGE, // 图片消息
		AUDIO, // 音频消息
		VIDEO // 视频消息
	}

	private boolean isNotice = false; // 标识本内容是否为一条公告内容

	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String content = null; // 聊天内容，如果是图片等，则为存放路径
	// 注：聊天时间使用父类中的createdTime

	private MessageType messageType = MessageType.TEXT; // 消息类型

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean sender = null; // 消息发送人

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ChatRoomBean.class, direction = Direction.OUTGOING)
	private transient ChatRoomBean chatRoom = null; // 关联的聊天室

	public ChatMessageBean() {
		super();
	}

	public ChatMessageBean(Long id) {
		super(id);
	}

	public boolean isNotice() {
		return isNotice;
	}

	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the sender
	 */
	public UserProfileBean getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(UserProfileBean sender) {
		this.sender = sender;
	}

	public ChatRoomBean getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(ChatRoomBean chatRoom) {
		this.chatRoom = chatRoom;
	}

}
