package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

/**
 * Created by 林平 on 2016/3/2.
 */
@HbaseTable(name = "T_VERSION_INFO")
@NodeEntity
public class VersionBean extends BaseBeanImpl {
	private static final long serialVersionUID = 1655657744495996545L;

	public enum TYPE {
		APP, // 游徒APP
		EXPERT_APP// 达人APP
	}

	private TYPE type = TYPE.APP; // 应用类型
	private String version; // 版本号
	private String versionUrl; // 新版本路径
	private String releaseNotes; // 发布说明
	private boolean isForceUpgrade = false; // 是否强制升级

	public VersionBean() {
		super();
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionUrl() {
		return versionUrl;
	}

	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}

	public String getReleaseNotes() {
		return releaseNotes;
	}

	public void setReleaseNotes(String releaseNotes) {
		this.releaseNotes = releaseNotes;
	}

	public boolean isForceUpgrade() {
		return isForceUpgrade;
	}

	public void setForceUpgrade(boolean isForceUpgrade) {
		this.isForceUpgrade = isForceUpgrade;
	}
}
