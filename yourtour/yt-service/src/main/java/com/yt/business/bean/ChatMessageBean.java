package com.yt.business.bean;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

/**
 * 该实体定义聊天数据信息。
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
	
	private boolean isNotice = false; // 标识本内容是否为一条公告内容
	
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String message = null; // 聊天内容
	// 注：聊天时间使用父类中的createedTime，聊天内容将来可以扩展成为JSON标识的复杂对象。
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean userProfile = null; // 关联的聊天人

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ChatSessionBean.class, direction = Direction.OUTGOING)
	private transient ChatSessionBean chatSession = null; // 关联的聊天室

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserProfileBean getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileBean userProfile) {
		this.userProfile = userProfile;
	}

	public ChatSessionBean getChatSession() {
		return chatSession;
	}

	public void setChatSession(ChatSessionBean chatSession) {
		this.chatSession = chatSession;
	}

}
