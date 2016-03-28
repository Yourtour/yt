package com.yt.ws.chat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yt.business.bean.ChatMessageBean.MessageType;
import com.yt.ws.chat.message.AbstractChatMessage;
import com.yt.ws.chat.message.ChatMessageImage;
import com.yt.ws.chat.message.ChatMessageText;

public class ChatSessionUtils {
	private static final Log LOG = LogFactory.getLog(ChatSessionUtils.class);

	public enum ChatSessionTypeEnum {
		PLACE_SESSION, ROUTE_SESSION, DYNAMIC_SESSION
	}

	private ChatSessionUtils() {
		super();
	}

	public static AbstractChatMessage transformCommand(String command)
			throws Exception {
		if (command == null || command.isEmpty()) {
			return null;
		}
		JSONObject json = JSON.parseObject(command);
		String type = json.getString("type");
		if (MessageType.TEXT.name().equals(type)) {
			// TEXT Message
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Parse a TEXT message, message: %s",
						command));
			}
			return JSON.toJavaObject(json, ChatMessageText.class);
		} else if (MessageType.IMAGE.name().equals(type)) {
			// IMAGE Message
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("Parse a IMAGE message, message: %s",
						command));
			}
			return JSON.toJavaObject(json, ChatMessageImage.class);
		} else if (MessageType.AUDIO.name().equals(type)) {
			// TODO AUDIO Message
			throw new UnsupportedOperationException();
		} else if (MessageType.VIDEO.name().equals(type)) {
			// TODO VIDEO Message
			throw new UnsupportedOperationException();
		} else {
			throw new Exception(String.format(
					"Not supported message type, message: %s.", command));
		}
	}

}
