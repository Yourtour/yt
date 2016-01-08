package com.yt.vo.route;

import com.yt.business.bean.RouteServiceBean;
import com.yt.vo.BaseVO;
import com.yt.vo.member.ExpertServiceVO;

public class RouteServiceVO extends ExpertServiceVO {
	private String  expertServiceId;
	private String  scheduleId;
	private String  routeId;

	public static RouteServiceVO transform(RouteServiceBean bean) {
		if (bean == null) {
			return null;
		}
		RouteServiceVO valueObject = new RouteServiceVO();
		valueObject.setExpertServiceId(String.valueOf(bean.getExpertServiceId()));
		valueObject.setId(bean.getGraphId());
		valueObject.setFee(bean.getFee());
		valueObject.setTitle(bean.getTitle());
		valueObject.setMemo(bean.getMemo());
		valueObject.setWithdraw(bean.getWithdraw());
		valueObject.setFeeExcluding(bean.getFeeExcluding());
		valueObject.setFeeIncluding(bean.getFeeIncluding());
		valueObject.setImageUrl(bean.getImageUrl());
		valueObject.setImageUrls(bean.getImageUrls());

		return valueObject;
	}

	public RouteServiceVO() {
		super();
	}

	public String getExpertServiceId() {
		return expertServiceId;
	}

	public void setExpertServiceId(String expertServiceId) {
		this.expertServiceId = expertServiceId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
}
