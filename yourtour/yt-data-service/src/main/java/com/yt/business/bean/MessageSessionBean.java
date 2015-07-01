package com.yt.business.bean;

import com.yt.business.common.Constants.ContentType;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义聊天会话信息以及参与会话的用户信息
 */
@HbaseTable(name = "T_MESSAGE_SESSION_INFO")
public class MessageSessionBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	private 	@HbaseColumn(name = "name")	String name = ""; //会话名
	private 	@HbaseColumn(name = "uids")	String userIds = ""; //参与会话用户ID，采用分号分隔
	private 	@HbaseColumn(name = "lcnt")	String latestContent = ""; //最后发送内容
	private 	@HbaseColumn(name = "type")	ContentType  type; //类型
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

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public void setLatestContent(String latestContent) {
		this.latestContent = latestContent;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
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
