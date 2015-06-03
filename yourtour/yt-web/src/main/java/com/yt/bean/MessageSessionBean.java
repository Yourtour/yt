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
@HbaseTable(name = "T_MESSAGE_SESSION_INFO")
public class MessageSessionBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	private 	@HbaseColumn(name = "name")	String name = ""; //会话名
	private 	@HbaseColumn(name = "lcnt")	String latestContent = ""; //最后发送内容
	private 	@HbaseColumn(name = "type")	MessageType  type; //类型
	private 	@HbaseColumn(name = "lser")	String latestSender = ""; //最后发送人
	private 	@HbaseColumn(name = "lsdtr")	long latestSendDate ; //最后发送时间
	private 	@HbaseColumn(name = "img")	String imageUrl = ""; //会话图标
	
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "stat")		Status	status;
	
	public MessageSessionBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatestContent() {
		return latestContent;
	}

	public void setLatestContent(String latestContent) {
		this.latestContent = latestContent;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getLatestSender() {
		return latestSender;
	}

	public void setLatestSender(String latestSender) {
		this.latestSender = latestSender;
	}

	public long getLatestSendDate() {
		return latestSendDate;
	}

	public void setLatestSendDate(long latestSendDate) {
		this.latestSendDate = latestSendDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
