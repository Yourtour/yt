package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

/**
 * 该实体定义聊天人员加入历史数据信息。
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
@HbaseTable(name = "T_CHAT_JOIN_HIST")
@NodeEntity
public class ChatJoinHistoryBean extends BaseBeanImpl {
	private static final long serialVersionUID = -6640467687331288206L;

	// 进入聊天室时间: createdTime
	// 退出聊天室时间: updatedTime

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean userProfile = null; // 关联的用户信息

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ChatSessionBean.class, direction = Direction.OUTGOING)
	private transient ChatSessionBean chatSession = null; // 关联的聊天室

	public ChatJoinHistoryBean() {
		super();
	}

	public ChatJoinHistoryBean(Long id) {
		super(id);
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
