package com.yt.business.service.impl;

import com.yt.business.bean.*;
import com.yt.business.repository.neo4j.ActivityBeanRepository;
import com.yt.business.repository.neo4j.BannerBeanRepository;
import com.yt.business.repository.neo4j.HotPlayingBeanRepository;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IHomeService;
import com.yt.core.utils.StringUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<String, Object> launch(String accessToken, String devType, String version) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNull(accessToken)) { // 第一次运行本系统
            accessToken = UUID.randomUUID().toString();

            LaunchBean launch = launchCrud.get("accessToken", accessToken);
            if (launch == null) {
                // 第一次运行
                launch = new LaunchBean();
                launch.setAccessToken(UUID.randomUUID().toString());
            }
            launch.setLastAccessDate(new Date().getTime());
            launchCrud.save(launch);

            map.put(IHomeService.KEY_LAUNCHBEAN, launch);
        }

        List<VersionBean> versions = versionCrud.get();
        Collections.sort(versions);
        if (!versions.isEmpty()) {
            VersionBean versionBean = versions.get(0);
            if (!version.equals(versionBean.getVersion())) {
                map.put(IHomeService.KEY_VERSIONBEAN, versionBean);
            }
        }

        // 获取当前活动
        List<ActivityBean> activities = activityRepository.getReleasedActivities();
        if (!activities.isEmpty()) {
            map.put(IHomeService.KEY_ACTIVITYBEAN, activities.get(0));
        }
        return map;
    }
}
