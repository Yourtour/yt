package com.yt.business.bean;

import java.util.List;

import com.yt.business.SocialBeanImpl;

/**
 * 该实体定义了有服务层获取的首页数据
 * 
 * @author Tony.Zhang
 * 
 */
public class HomeBean extends SocialBeanImpl {
	private static final long serialVersionUID = -3433522673262851121L;

	private List<RouteMainBean> recommendedRoutes;  //推荐行程

	private List<RouteMainBean> hotRoutes;  //热门行程（大家都在玩的行程）

	private List<NewsBean> consults;  //资讯信息

	private List<BannerBean> banners; //Banner信息

	public HomeBean() {
		super();
	}

	public List<RouteMainBean> getRecommendedRoutes() {
		return recommendedRoutes;
	}

	public void setRecommendedRoutes(List<RouteMainBean> recommendedRoutes) {
		this.recommendedRoutes = recommendedRoutes;
	}

	public List<RouteMainBean> getHotRoutes() {
		return hotRoutes;
	}

	public void setHotRoutes(List<RouteMainBean> hotRoutes) {
		this.hotRoutes = hotRoutes;
	}

	public List<NewsBean> getConsults() {
		return consults;
	}

	public void setConsults(List<NewsBean> consults) {
		this.consults = consults;
	}

	public List<BannerBean> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerBean> banners) {
		this.banners = banners;
	}
}
