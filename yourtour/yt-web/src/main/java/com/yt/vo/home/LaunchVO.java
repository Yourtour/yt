package com.yt.vo.home;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.VersionBean;

public class LaunchVO {
	private DeviceVO device;
	private ActivityVO activity;

	public class DeviceVO {
		private String accessToken, newVersionUrl, releaseNotes;
		private boolean isForceUpgrade;

		public DeviceVO() {
			super();
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
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

	public class ActivityVO {
		private long id;
		private String imageUrl;

		public ActivityVO() {
			super();
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	}

	public LaunchVO() {
		super();
		this.device = new DeviceVO();
		this.activity = new ActivityVO();
	}

	public static LaunchVO transform(LaunchBean launch, VersionBean version,
			ActivityBean activity) {
		LaunchVO vo = new LaunchVO();
		if (launch != null && version != null) {
			if (launch != null) {
				vo.device.accessToken = launch.getAccessToken();
			}
			if (version != null) {
				vo.device.newVersionUrl = version.getVersionUrl();
				vo.device.releaseNotes = version.getReleaseNotes();
				vo.device.isForceUpgrade = version.isForceUpgrade();
			}
		} else {
			vo.device = null;
		}

		if (activity != null) {
			vo.activity.id = activity.getId();
			vo.activity.imageUrl = activity.getImageUrl();
		} else {
			vo.activity = null;
		}
		return vo;
	}

	public DeviceVO getDevice() {
		return device;
	}

	public ActivityVO getActivity() {
		return activity;
	}

}
