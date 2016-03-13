package com.yt.business.service.impl;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
>>>>>>> 2e51e1ee7f1bc2c3cf4126351b6534d4636dac1d

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.BannerBean;
import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.VersionBean;
import com.yt.business.repository.neo4j.ActivityBeanRepository;
import com.yt.business.repository.neo4j.BannerBeanRepository;
import com.yt.business.repository.neo4j.HotPlayingBeanRepository;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IHomeService;
import com.yt.neo4j.repository.CrudOperate;

@Service
public class HomeServiceImpl extends ServiceBase implements IHomeService {
	private static final Log LOG = LogFactory.getLog(HomeServiceImpl.class);
	@Autowired
	private BannerBeanRepository bannerRepository;

	@Autowired
	private RouteMainBeanRepository routeRepository;

	@Autowired
	private HotPlayingBeanRepository hotPlayingRepository;

<<<<<<< HEAD
=======
	@Autowired
	private ActivityBeanRepository activityRepository;

	@Autowired
	private CrudOperate<LaunchBean> launchCrud;

	@Autowired
	private CrudOperate<VersionBean> versionCrud;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IHomeService#getRecommends()
	 */
>>>>>>> 2e51e1ee7f1bc2c3cf4126351b6534d4636dac1d
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
	 * java.lang.String)
	 */
	@Override
<<<<<<< HEAD
	public Map<String, Object> launch(String accessToken,String devType, String version) {
		if (accessToken == null || accessToken.isEmpty()) {
			// 第一次运行本系统
			accessToken = UUID.randomUUID().toString();
		}

		// TODO Auto-generated method stub
		return null;
=======
	public Map<String, Object> launch(String accessToken, String version) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			LaunchBean launch = launchCrud.get("accessToken", accessToken);
			if (launch == null) {
				// 第一次运行
				launch = new LaunchBean();
				launch.setAccessToken(UUID.randomUUID().toString());
			}
			launch.setLastAccessDate(new Date().getTime());
			launchCrud.save(launch);
			map.put(IHomeService.KEY_LAUNCHBEAN, launch);

			List<VersionBean> versions = versionCrud.get();
			Collections.sort(versions);
			if (!versions.isEmpty()) {
				VersionBean versionBean = versions.get(0);
				if (!version.equals(versionBean.getVersion())) {
					map.put(IHomeService.KEY_VERSIONBEAN, versionBean);
				}
			}

			// 获取当前活动
			List<ActivityBean> activities = activityRepository
					.getReleasedActivities();
			if (!activities.isEmpty()) {
				map.put(IHomeService.KEY_ACTIVITYBEAN, activities.get(0));
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("launch the app fail.", ex);
			}
		}
		return map;
>>>>>>> 2e51e1ee7f1bc2c3cf4126351b6534d4636dac1d
	}

}
