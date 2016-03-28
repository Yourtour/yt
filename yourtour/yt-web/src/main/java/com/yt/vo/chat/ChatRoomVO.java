package com.yt.vo.chat;

import java.io.Serializable;

import com.yt.business.bean.ChatRoomBean;
import com.yt.business.bean.UserProfileBean;

public class ChatRoomVO implements Serializable {
	private static final long serialVersionUID = 1014540185314715793L;

	private long id;
	private int unreadMessageNum;
	private String code, name, type, wsUrl, lastMessage;

	public ChatRoomVO() {
		super();
	}

	public static ChatRoomVO transform(ChatRoomBean bean) {
		if (bean == null) {
			return null;
		}
		ChatRoomVO vo = new ChatRoomVO();
		vo.id = bean.getId();
		vo.unreadMessageNum = bean.getUnreadMessageNum();
		vo.code = bean.getChatRoomNo();
		if (bean.getChatRoomName() == null) {
			StringBuffer sb = new StringBuffer();
			for (UserProfileBean user : bean.getMember()) {
				if (user == null) {
					continue;
				}
				sb.append(user.getNickName());
				sb.append(" ");
			}
			vo.name = sb.substring(0, Math.min(15, sb.length()));
		} else {
			vo.name = bean.getChatRoomName();
		}
		vo.type = bean.getChatRoomType().name();
		vo.wsUrl = bean.getChatRoomUrl();
		if (bean.getLastMessage() != null) {
			vo.lastMessage = String.format("%s:%s", bean.getLastMessage()
					.getSender().getNickName(), bean.getLastMessage()
					.getContent());
		} else {
			vo.lastMessage = "";
		}
		return vo;
	}

	public long getId() {
		return id;
	}

	public int getUnreadMessageNum() {
		return unreadMessageNum;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public String getLastMessage() {
		return lastMessage;
	}

}
