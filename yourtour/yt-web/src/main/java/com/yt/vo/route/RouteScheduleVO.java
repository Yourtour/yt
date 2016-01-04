package com.yt.vo.route;

import java.util.List;

import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.BaseVO;

public class RouteScheduleVO extends BaseVO {
	private long index;
	private long date;
	private int  days;
	private String placeIds;
	private String places;
	private String memo;
	private List<RouteActivityVO> activities;

	public static RouteScheduleVO transform(RouteScheduleBean bean) {
		if (bean == null) {
			return null;
		}
		RouteScheduleVO vo = new RouteScheduleVO();
		vo.fromBean(bean);
		vo.setIndex(bean.getIndex());
		vo.setDate(bean.getDate());
		vo.setDays(bean.getDays());
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
		bean.setDays(vo.getDays());
		bean.setPlaces(vo.getPlaces());
		if (vo.getActivities() != null && vo.getActivities().size() > 0) {
			for (RouteActivityVO activityVO : vo.getActivities()) {
				RouteActivityBean activity = new RouteActivityBean();
				activity.setGraphId(activityVO.getId());
				bean.getActivities().add(activity);
			}
		}
		return bean;
	}

	public RouteScheduleVO() {
		super();
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
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

	public List<RouteActivityVO> getActivities() {
		return activities;
	}

	public String getType(){
		return "day";
	}
}
