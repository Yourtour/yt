package com.yt.vo.home;

import com.yt.business.bean.VersionBean;

public class VersionVO {
	private long id;
	private String newVersionUrl, releaseNotes;
	private boolean isForceUpgrade;

	public VersionVO() {
		super();
	}

	public static VersionVO transform(VersionBean bean) {
		if (bean == null) {
			return null;
		}
		VersionVO vo = new VersionVO();
		vo.id = bean.getId();
		vo.newVersionUrl = bean.getVersionUrl();
		vo.releaseNotes = bean.getReleaseNotes();
		vo.isForceUpgrade = bean.isForceUpgrade();
		return vo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNewVersionUrl() {
		return newVersionUrl;
	}

	public void setNewVersionUrl(String newVersionUrl) {
		this.newVersionUrl = newVersionUrl;
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
