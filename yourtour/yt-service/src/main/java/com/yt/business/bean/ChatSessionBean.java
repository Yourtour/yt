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
public class ChatSessionBean extends BaseBeanImpl {
	private static final long serialVersionUID = -8555540274427305434L;

	// 聊天类型：目的地聊天室、行程聊天室、动态聊天室
	public static final String CHAT_TYPE_PLACE = "CHAT_TYPE_PLACE",
			CHAT_TYPE_ROUTE = "CHAT_TYPE_ROUTE",
			CHAT_TYPE_DYNAMIC = "CHAT_TYPE_DYNAMIC";

	@Indexed
	private String chatRoomNo = null; // 聊天室号码，如果是目的地或者行程聊天室，则是前缀+目的地和行程的ID。

	private String chatRoomUrl = null; // 聊天室访问地址

	private String chatRoomType = CHAT_TYPE_DYNAMIC; // 聊天室类型

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = PlaceBean.class, direction = Direction.OUTGOING)
	private transient PlaceBean place = null; // 聊天室所属的目的地，如果为空，则不属于任何一个目的地

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_AT, type = RouteMainBean.class, direction = Direction.OUTGOING)
	private transient RouteMainBean route = null; // 聊天室所属的行程，如果为空，则不属于任何一个行程

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, isList = true, direction = Direction.INCOMING)
	private transient List<UserProfileBean> userProfiles = new Vector<UserProfileBean>(); // 聊天室当前聊天人员

	public ChatSessionBean() {
		super();
	}

	public ChatSessionBean(Long id) {
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
		return userProfiles;
	}

	public String getChatRoomType() {
		return chatRoomType;
	}

	public void setChatRoomType(String chatRoomType) {
		this.chatRoomType = chatRoomType;
	}

	public int getChatterNum() {
		return userProfiles.size();
	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}

	/**
	 * 返回当前聊天室是否为一个动态聊天室，动态聊天室就是不属于任何一个目的地或行程，被用户动态创建的聊天室。
	 * 
	 * @return 如果是，返回true；否则返回false。
	 */
	public boolean isDynamicChatSession() {
		return this.place == null && this.route == null;
	}

}
