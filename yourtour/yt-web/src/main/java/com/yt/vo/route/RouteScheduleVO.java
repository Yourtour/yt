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
	private long date;
	private int  days;
	private String description;
	private PlaceVO place;
	private RouteVO route;
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
		if (bean.getPlace() != null) {
			PlaceVO placeVO = new PlaceVO();
			placeVO.setId(bean.getPlace().getGraphId());
			placeVO.setCode(bean.getPlace().getCode());
			placeVO.setShorter(bean.getPlace().getShorter());
			placeVO.setText(bean.getPlace().getMemo());
			vo.setPlace(placeVO);
		}
		if (bean.getRouteMain() != null) {
			RouteVO routeVO = new RouteVO();
			routeVO.setId(bean.getRouteMain().getGraphId());
			routeVO.setName(bean.getRouteMain().getName());
			vo.setRoute(routeVO);
		}
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
		if (vo.getPlace() != null) {
			PlaceBean place = new PlaceBean();
			place.setGraphId(vo.getPlace().getId());
			bean.setPlace(place);
		}
		if (vo.getRoute() != null) {
			RouteMainBean route = new RouteMainBean();
			route.setGraphId(vo.getRoute().getId());
			bean.setRouteMain(route);
		}
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

	public PlaceVO getPlace() {
		return place;
	}

	public void setPlace(PlaceVO place) {
		this.place = place;
	}

	public RouteVO getRoute() {
		return route;
	}

	public void setRoute(RouteVO route) {
		this.route = route;
	}

	public List<RouteActivityVO> getActivities() {
		return activities;
	}

}
