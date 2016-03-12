package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

/**
 * 相关APP发布的版本信息
 * 
 * Created by John.Peng on 2016/3/12.
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

	@Override
	public int compareTo(BaseBeanImpl o) {
		VersionBean version = (VersionBean) o;
		if (version == null) {
			return -1;
		}
		String srcVersion = this.version, tarVersion = version.getVersion();
		if (srcVersion == null && tarVersion == null) {
			return 0;
		}
		if (srcVersion == null) {
			return 1;
		}
		if (tarVersion == null) {
			return 1;
		}
		// 两个版本都不为空
		String[] src = srcVersion.split("\\."), tar = tarVersion.split("\\.");
		int len = Math.min(src.length, tar.length);
		for (int i = 0; i < len; i++) {
			int result = Integer.valueOf(tar[i]) - Integer.valueOf(src[i]);
			if (result == 0) {
				continue;
			} else {
				return result;
			}
		}
		return 0;
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
