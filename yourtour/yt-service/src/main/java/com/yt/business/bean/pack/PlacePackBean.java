package com.yt.business.bean.pack;

import java.io.Serializable;
import java.util.List;

import com.yt.business.bean.BannerBean;
import com.yt.business.bean.InfoBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;

/**
 * 目的地包装实体，是数据库实体的组合
 * 
 * Created by 林平 on 2016/3/19.
 */
public class PlacePackBean implements Serializable {
	private static final long serialVersionUID = 6043265050947091384L;
	private PlaceBean place;
	private List<BannerBean> banners;
	private List<RouteMainBean> routes;
	private List<UserProfileBean> experts;
	private List<InfoBean> infoes;

	public PlacePackBean() {

	}

	public PlaceBean getPlace() {
		return place;
	}

	public void setPlace(PlaceBean place) {
		this.place = place;
	}

	public List<BannerBean> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerBean> banners) {
		this.banners = banners;
	}

	public List<RouteMainBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteMainBean> routes) {
		this.routes = routes;
	}

	public List<UserProfileBean> getExperts() {
		return experts;
	}

	public void setExperts(List<UserProfileBean> experts) {
		this.experts = experts;
	}

	public List<InfoBean> getInfoes() {
		return infoes;
	}

	public void setInfoes(List<InfoBean> infoes) {
		this.infoes = infoes;
	}
}
