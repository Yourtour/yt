package com.yt.vo.home;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.vo.place.PlaceVO;
import com.yt.vo.route.RouteVO;

public class ActivityVO {
	private long id;
	private String imageUrl;
	private PlaceVO place;
	private List<RouteVO> routes;

	public ActivityVO() {
		super();
		this.routes = new Vector<RouteVO>();
	}

	public static ActivityVO transform(ActivityBean bean) {
		if (bean == null) {
			return null;
		}
		ActivityVO vo = new ActivityVO();
		vo.setId(bean.getId());
		vo.setImageUrl(bean.getImageUrl());
		if (bean.getPlace() != null) {
			vo.setPlace(PlaceVO.transform(bean.getPlace()));
		}
		for (RouteMainBean route : bean.getRoutes()) {
			if (route == null) {
				continue;
			}
			vo.getRoutes().add(RouteVO.transform(route));
		}
		return vo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public PlaceVO getPlace() {
		return place;
	}

	public void setPlace(PlaceVO place) {
		this.place = place;
	}

	public List<RouteVO> getRoutes() {
		return routes;
	}

}