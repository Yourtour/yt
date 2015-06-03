package com.yt.bean;

import com.yt.common.Constants.MessageType;
import com.yt.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义聊天会话消息
 */
@HbaseTable(name = "T_MESSAGE_CONTENT_INFO")
public class MessageContentBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	private 	@HbaseColumn(name = "name")	String content = ""; //内容
	private 	@HbaseColumn(name = "type")	MessageType  type; //类型
	private 	@HbaseColumn(name = "sender")		String sender = ""; //发送人
	private 	@HbaseColumn(name = "st")		long sendTime ; //发送时间
	private 	@HbaseColumn(name = "stat")		Status	status;
	
	public MessageContentBean() {
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public MessageType getType() {
		return type;
	}


	public void setType(MessageType type) {
		this.type = type;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public long getSendTime() {
		return sendTime;
	}


	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
