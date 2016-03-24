package com.yt.business.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.yt.core.utils.StringUtils;
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

	@Autowired
	private ActivityBeanRepository activityRepository;

	@Autowired
	private CrudOperate<LaunchBean> launchCrud;

	@Autowired
	private CrudOperate<VersionBean> versionCrud;

	@Override
	public Map<String, Object> getHomeData(Long userId, Long lastModifiedTime)
			throws Exception {
		Map<String, Object> recommends = new HashMap<String, Object>();
		List<BannerBean> banners = bannerRepository.getRecommendBanners(
				new Date().getTime(), 3);
		List<RouteMainBean> routes = routeRepository.getRecommendRoutes(3);
		List<HotPlayingBean> hotPlayings = hotPlayingRepository
				.getRecommendHotPlayings(3);

		recommends.put(IHomeService.KEY_BANNERS, banners);
		recommends.put(IHomeService.KEY_ROUTES, routes);
		recommends.put(IHomeService.KEY_HOTPLAYINGS, hotPlayings);
		return recommends;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IHomeService#launch(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> launch(Long userId, String accessToken,
			String devType, String appType, String version) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		LaunchBean launch = new LaunchBean();
		if (StringUtils.isNull(accessToken)) { // 第一次运行本系统
			launch.setId(null);
			launch.setAccessToken(UUID.randomUUID().toString());
			launchCrud.save(launch);
		} else {
			launch.setAccessToken(accessToken);
		}
		launch.setSessionToken(UUID.randomUUID().toString());
		map.put(IHomeService.KEY_LAUNCHBEAN, launch);

		// 版本检查
		if (!"0.0.0.0".equalsIgnoreCase(version)) {
			List<VersionBean> versions = versionCrud.get();
			Collections.sort(versions);
			if (!versions.isEmpty()) {
				for (VersionBean vBean : versions) {
					if (devType.equalsIgnoreCase(vBean.getDevType().name())
							&& appType.equalsIgnoreCase(vBean.getAppType()
									.name())) {

						// 两个版本都不为空
						String[] src = version.split("\\."), tar = vBean
								.getVersion().split("\\.");
						int len = Math.min(src.length, tar.length);
						for (int i = 0; i < len; i++) {
							int result = Integer.valueOf(tar[i])
									- Integer.valueOf(src[i]);
							if (result > 0) {
								map.put(IHomeService.KEY_VERSIONBEAN, vBean);
								break;
							} else if (result < 0) {
								break;
							}
						}
					}
				}
			}
		}

		if (!map.containsKey(IHomeService.KEY_VERSIONBEAN)) {
			// 没有升级版本，则获取当前活动
			List<ActivityBean> activities = activityRepository
					.getReleasedActivities();
			if (!activities.isEmpty()) {
				map.put(IHomeService.KEY_ACTIVITYBEAN, activities.get(0));
			}
		}
		return map;
	}
}
