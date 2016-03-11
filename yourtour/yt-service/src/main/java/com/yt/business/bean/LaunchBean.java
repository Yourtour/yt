package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

/**
 * APP启动中需要的信息内容。
 * 
 * Created by John.Peng by 2016-03-11
 */
@HbaseTable(name = "T_LAUNCH_INFO")
@NodeEntity
public class LaunchBean extends BaseBeanImpl {
	private static final long serialVersionUID = -1678777442987394947L;
	private String accessToken;
	private long lastAccessDate;
	
	public LaunchBean() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(long lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
}
