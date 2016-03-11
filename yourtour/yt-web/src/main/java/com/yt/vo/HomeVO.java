package com.yt.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.NewsBean;
import com.yt.business.bean.HomeBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.core.utils.CollectionUtils;
import com.yt.vo.AlongVO;
import com.yt.vo.LineVO;
import com.yt.vo.route.RouteVO;

public class HomeVO extends BaseVO {
	private List<RouteVO> recommends = null;

	private List<RouteVO> hots = null;
	
	private List<BannerVO> banners = null;

	private List<ConsultVO> consults = null;
	
	public static HomeVO transform(HomeBean bean){
		HomeVO vo = new HomeVO();

		List<RouteMainBean> routes = bean.getRecommendedRoutes();
		if(CollectionUtils.isNotEmpty(routes)){
			List<RouteVO> rvoes = new ArrayList<>();

			for(RouteMainBean route : routes){
				rvoes.add(RouteVO.transform(route));
			}

			vo.setRecommends(rvoes);
		}

		routes = bean.getHotRoutes();
		if(CollectionUtils.isNotEmpty(routes)){
			List<RouteVO> rvoes = new ArrayList<>();

			for(RouteMainBean route : routes){
				rvoes.add(RouteVO.transform(route));
			}

			vo.setHots(rvoes);
		}

		vo.setBanners(BannerVO.transform(bean.getBanners()));

		return vo;
	}

	public List<RouteVO> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<RouteVO> recommends) {
		this.recommends = recommends;
	}

	public List<RouteVO> getHots() {
		return hots;
	}

	public void setHots(List<RouteVO> hots) {
		this.hots = hots;
	}

	public List<BannerVO> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerVO> banners) {
		this.banners = banners;
	}

	public List<ConsultVO> getConsults() {
		return consults;
	}

	public void setConsults(List<ConsultVO> consults) {
		this.consults = consults;
	}
}
