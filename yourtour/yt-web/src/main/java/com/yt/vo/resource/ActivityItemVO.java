package com.yt.vo.resource;

import com.yt.business.bean.ActivityItemBean;
import com.yt.business.bean.RouteActivityItemBean;
import com.yt.vo.BaseVO;

public class ActivityItemVO extends BaseVO {
	private String imageUrl; // 图片
	private String title;
	private String memo;
	private int thumbupNum; // 点评数

	public static ActivityItemVO transform(ActivityItemBean bean) {
		if (bean == null) {
			return null;
		}
		ActivityItemVO vo = new ActivityItemVO();
		vo.setId(bean.getGraphId());
		vo.setTitle(bean.getTitle());
		vo.setMemo(bean.getMemo());
		vo.setImageUrl(bean.getImageUrl());
		vo.setThumbupNum(bean.getThumbupNum());

		return vo;
	}

	public ActivityItemVO() {
		super();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getThumbupNum() {
		return thumbupNum;
	}

	public void setThumbupNum(int thumbupNum) {
		this.thumbupNum = thumbupNum;
	}
}
