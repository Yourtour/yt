package com.yt.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.NewsBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.repository.neo4j.HotPlayingBeanRepository;
import com.yt.business.repository.neo4j.NewsBeanRepository;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IHomeService;

@Service
public class HomeServiceImpl extends BaseServiceImpl implements IHomeService {
	@Autowired
	private NewsBeanRepository newsRepository;

	@Autowired
	private RouteMainBeanRepository routeRepository;

	@Autowired
	private HotPlayingBeanRepository hotPlayingRepository;

	@Override
	public Map<String, Object> getRecommends() throws Exception {
		Map<String, Object> recommends = new HashMap<String, Object>();
		List<NewsBean> news = newsRepository.getRecommendNews(
				new Date().getTime(), 3);
		List<RouteMainBean> routes = routeRepository.getRecommendRoutes(3);
		List<HotPlayingBean> hotPlayings = hotPlayingRepository
				.getRecommendHotPlayings(3);

		recommends.put(IHomeService.KEY_NEWSBEAN, news);
		recommends.put(IHomeService.KEY_ROUTES, routes);
		recommends.put(IHomeService.KEY_HOTPLAYINGS, hotPlayings);
		return recommends;
	}

}
