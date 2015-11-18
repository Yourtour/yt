package com.yt.vo.route;

import java.util.List;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.vo.BaseVO;
import com.yt.vo.basedata.PlaceVO;

public class RouteScheduleVO extends BaseVO {
	private int index;
	private String name;
	private long date;
	private int  days;
	private String placeIds;
	private String places;
	private String description;
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
		vo.setDescription(bean.getDescription());
		vo.setPlaces(bean.getPlaces());
		if (bean.getActivities() != null && bean.getActivities().size() > 0) {
			for (RouteActivityBean activity : bean.getActivities()) {
				RouteActivityVO activityVO = new RouteActivityVO();
				activityVO.setId(activity.getGraphId());
				activityVO.setName(activity.getName());
				vo.getActivities().add(activityVO);
			}
		}
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
		bean.setDescription(vo.getDescription());
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
