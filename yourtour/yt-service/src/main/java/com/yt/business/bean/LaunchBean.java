package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

/**
 * APP启动中需要的信息内容。
 * 
 * Created by John.Peng on 2016-03-11
 */
@HbaseTable(name = "T_LAUNCH_INFO")
@NodeEntity
public class LaunchBean extends BaseBeanImpl {
	private static final long serialVersionUID = -1678777442987394947L;
	@Indexed
	private String accessToken;

	private transient String sessionToken;

	public LaunchBean() {
		super();
		super.setId(-1l);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
}
