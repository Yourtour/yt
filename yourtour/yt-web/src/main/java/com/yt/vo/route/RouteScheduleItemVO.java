package com.yt.vo.route;

import com.yt.business.bean.*;
import com.yt.vo.BaseVO;

public class RouteScheduleItemVO extends BaseVO {
	private String title, memo, option, imageUrl;
	private int index;
	private Long resourceActivityItemId;

	public static RouteScheduleItemVO transform(RouteScheduleItemBean bean) {
		if (bean == null) {
			return null;
		}
		RouteScheduleItemVO vo = new RouteScheduleItemVO();
		vo.fromBean(bean);

		ResourceActivityItemBean resourceActivityItem = bean.getResourceActivityItem();
		vo.setTitle(resourceActivityItem.getTitle());
		vo.setMemo(resourceActivityItem.getMemo());
		vo.setOption(bean.getOption());
		vo.setImageUrl(bean.getImageUrl());

		return vo;
	}

	public static RouteScheduleItemBean transform(RouteScheduleItemVO vo) {
		if (vo == null) {
			return null;
		}
		RouteScheduleItemBean bean = new RouteScheduleItemBean();
		vo.toBean(bean);
		bean.setTitle(vo.getTitle());
		bean.setMemo(vo.getMemo());
		bean.setOption(vo.getOption());

		return bean;
	}

	public RouteScheduleItemVO() {
		super();
	}

	public Long getResourceActivityItemId() {
		return resourceActivityItemId;
	}

	public void setResourceActivityItemId(Long resourceActivityItemId) {
		this.resourceActivityItemId = resourceActivityItemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
