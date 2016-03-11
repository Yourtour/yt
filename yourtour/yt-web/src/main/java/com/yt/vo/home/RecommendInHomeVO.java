package com.yt.vo.home;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.NewsBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.vo.route.RouteVO;

/**
 * 首页推荐界面的VO对象
 * 
 * @author John.Peng
 * 
 */
public class RecommendInHomeVO {
	private List<NewsVO> news;
	private List<RouteVO> routes;
	private List<HotPlayingVO> hotPlayings;

	public RecommendInHomeVO() {
		super();
		this.news = new Vector<NewsVO>();
		this.routes = new Vector<RouteVO>();
		this.hotPlayings = new Vector<HotPlayingVO>();
	}

	public static RecommendInHomeVO transform(List<NewsBean> newBeans,
			List<RouteMainBean> routeBeans, List<HotPlayingBean> hotPlayingBeans) {
		RecommendInHomeVO vo = new RecommendInHomeVO();
		for (NewsBean info : newBeans) {
			if (info == null) {
				continue;
			}
			vo.news.add(NewsVO.transform(info));
		}
		for (RouteMainBean route : routeBeans) {
			if (route == null) {
				continue;
			}
			vo.routes.add(RouteVO.transform(route));
		}
		for (HotPlayingBean hot : hotPlayingBeans) {
			if (hot == null) {
				continue;
			}
			vo.hotPlayings.add(HotPlayingVO.transform(hot));
		}
		return vo;
	}

	public List<NewsVO> getNews() {
		return news;
	}

	public List<RouteVO> getRoutes() {
		return routes;
	}

	public List<HotPlayingVO> getHotPlayings() {
		return hotPlayings;
	}

}
