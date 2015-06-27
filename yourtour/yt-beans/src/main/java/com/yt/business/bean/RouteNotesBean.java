package com.yt.bean;

import com.yt.common.Constants.ContentType;
import com.yt.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * @author Tony.Zhang
 * 
 * 行程随记bean，定义了行程过程中的随记信息
 *
 */
@HbaseTable(name = "T_ROUTE_NOTES_INFO")
public class RouteNotesBean extends BaseBean {
	private static final long serialVersionUID = 1857376918585112905L;
	
	private	@HbaseColumn(name = "rid")			String routeId = "";  //行程ID
	private	@HbaseColumn(name = "sid")			String scheduleId = ""; //行程安排ID
	private	@HbaseColumn(name = "url")			String url = ""; //
	private 	@HbaseColumn(name = "type")		ContentType type;
	private 	@HbaseColumn(name = "words")	String words = "";
	private 	@HbaseColumn(name = "live")		int	live;
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		Status	status;
	
	public RouteNotesBean() {
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
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

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
