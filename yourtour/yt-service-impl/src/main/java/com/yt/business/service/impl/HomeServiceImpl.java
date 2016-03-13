package com.yt.business.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.BannerBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.repository.neo4j.HotPlayingBeanRepository;
import com.yt.business.repository.neo4j.BannerBeanRepository;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IHomeService;

@Service
public class HomeServiceImpl extends ServiceBase implements IHomeService {
	@Autowired
	private BannerBeanRepository bannerRepository;

	@Autowired
	private RouteMainBeanRepository routeRepository;

	@Autowired
	private HotPlayingBeanRepository hotPlayingRepository;

	@Override
	public Map<String, Object> getHomeData(Long userId, Long lastModifiedTime) throws Exception {
		Map<String, Object> recommends = new HashMap<String, Object>();
		List<BannerBean> banners = bannerRepository.getRecommendBanners(new Date().getTime(), 3);
		List<RouteMainBean> routes = routeRepository.getRecommendRoutes(3);
		List<HotPlayingBean> hotPlayings = hotPlayingRepository.getRecommendHotPlayings(3);

		recommends.put(IHomeService.KEY_BANNERS, banners);
		recommends.put(IHomeService.KEY_ROUTES, routes);
		recommends.put(IHomeService.KEY_HOTPLAYINGS, hotPlayings);
		return recommends;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IHomeService#launch(java.lang.String,
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public Map<String, Object> launch(String accessToken,String devType, String version) {
		if (accessToken == null || accessToken.isEmpty()) {
			// 第一次运行本系统
			accessToken = UUID.randomUUID().toString();
		}

		// TODO Auto-generated method stub
		return null;
	}

}
