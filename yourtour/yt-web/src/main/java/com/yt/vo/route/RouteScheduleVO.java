package com.yt.vo.route;

import com.yt.business.bean.RouteScheduleBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.BaseVO;

import java.util.List;

public class RouteScheduleVO extends BaseVO {
	private int index;
	private long date;
	private int  days;
	private String placeIds;
	private String places;
	private String memo;

	public static RouteScheduleVO transform(RouteScheduleBean bean) {
		if (bean == null) {
			return null;
		}
		RouteScheduleVO vo = new RouteScheduleVO();
		vo.fromBean(bean);
		vo.setIndex(bean.getIndex());
		vo.setDate(bean.getDate());
		vo.setPlaces(bean.getPlaces());
		
		return vo;
	}

	public static RouteScheduleBean transform(RouteScheduleVO vo) {
		if (vo == null) {
			return null;
		}
		RouteScheduleBean bean = new RouteScheduleBean();
		vo.toBean(bean);
		bean.setIndex(vo.getIndex());
		bean.setDate(vo.getDate());
		bean.setMemo(vo.getMemo());
		bean.setPlaces(vo.getPlaces());

		return bean;
	}

	public RouteScheduleVO() {
		super();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return DateUtils.formatDate(this.date);
	}

	public String getPlaceIds() {
		return placeIds;
	}

	public void setPlaceIds(String placeIds) {
		this.placeIds = placeIds;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public String getType(){
		return "day";
	}
}
