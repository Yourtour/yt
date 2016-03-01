package com.yt.vo.route;

import com.yt.business.bean.*;
import com.yt.vo.BaseVO;

public class RouteActivityItemVO extends BaseVO {
	private String title, memo, option, imageUrl;
	private int index;
	private Long resourceActivityItemId;

	public static RouteActivityItemVO transform(RouteActivityItemBean bean) {
		if (bean == null) {
			return null;
		}
		RouteActivityItemVO vo = new RouteActivityItemVO();
		vo.fromBean(bean);

		vo.setResourceActivityItemId(bean.getResourceActivityItemId());
		ResourceActivityItemBean resourceActivityItem = bean.getResourceActivityItem();
		vo.setTitle(resourceActivityItem.getTitle());
		vo.setMemo(resourceActivityItem.getMemo());
		vo.setIndex(bean.getIndex());
		vo.setOption(bean.getOption());
		vo.setImageUrl(bean.getImageUrl());

		return vo;
	}

	public static RouteActivityItemBean transform(RouteActivityItemVO vo) {
		if (vo == null) {
			return null;
		}
		RouteActivityItemBean bean = new RouteActivityItemBean();
		vo.toBean(bean);
		bean.setTitle(vo.getTitle());
		bean.setMemo(vo.getMemo());
		bean.setIndex(vo.getIndex());
		bean.setOption(vo.getOption());

		return bean;
	}

	public RouteActivityItemVO() {
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
