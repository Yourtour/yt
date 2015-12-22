package com.yt.vo.route;

import com.yt.business.bean.RouteServiceBean;
import com.yt.vo.BaseVO;

public class RouteServiceVO extends BaseVO {
	private String  serviceId;
	private String 	title;
	private String	memo;
	private String  scheduleId;
	private String  routeId;

	public static RouteServiceVO transform(RouteServiceBean bean) {
		if (bean == null) {
			return null;
		}
		RouteServiceVO vo = new RouteServiceVO();
		vo.fromBean(bean);
		vo.setTitle(bean.getTitle());
		vo.setMemo(bean.getMemo());
		return vo;
	}

	public RouteServiceVO() {
		super();
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
