package com.yt.ws.chat.message;

import com.yt.business.bean.ChatMessageBean.MessageType;
import com.yt.core.utils.Base64Utils;

public class ChatMessageImage extends AbstractChatMessage {
	private String path; // 图片存放路径

	public ChatMessageImage() {
		super();
		super.setType(MessageType.IMAGE);
	}

	public byte[] getImageContent() throws Exception {
		return Base64Utils.decode(super.getContent());
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.ws.chat.message.AbstractChatMessage#getContent()
	 */
	@Override
	public String getContent() {
		// 重载基类中的方法，如果是附件类型的消息，则返回附件的路径
		return path;
	}

}
