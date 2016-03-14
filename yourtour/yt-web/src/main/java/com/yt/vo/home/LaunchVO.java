package com.yt.vo.home;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.VersionBean;

public class LaunchVO {
	private long id;
	private String accessToken, sessionToken;
	private VersionVO version;
	private ActivityVO activity;

	public LaunchVO() {
		super();
		this.version = new VersionVO();
		this.activity = new ActivityVO();
	}

	public static LaunchVO transform(LaunchBean launch, VersionBean version,
			ActivityBean activity) {
		LaunchVO vo = new LaunchVO();
		if (launch != null) {
			vo.id = launch.getId();
			vo.accessToken = launch.getAccessToken();
			vo.sessionToken = launch.getSessionToken();
		}
		if (version != null) {
			vo.version = VersionVO.transform(version);
		}

		if (activity != null) {
			vo.activity = ActivityVO.transform(activity);
		} else {
			vo.activity = null;
		}
		return vo;
	}

	public long getId() {
		return id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public VersionVO getVersion() {
		return version;
	}

	public ActivityVO getActivity() {
		return activity;
	}

}
