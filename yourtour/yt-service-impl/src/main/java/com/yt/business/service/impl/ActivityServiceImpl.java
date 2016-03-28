package com.yt.business.service.impl;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;
import com.yt.business.service.IActivityService;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceRefs;

/**
 * Created by 林平 on 2016/3/26.
 */
@Service
public class ActivityServiceImpl extends ServiceBase implements IActivityService {
    public ActivityServiceImpl(){}

    @Override
    public void saveActivityInfo(ActivityBean activity, Long userId) throws Exception {

    }

    @Override
    public void saveActivityContentInfo(ActivityContentBean content, Long userId) throws Exception {

    }

    @Override
    public ActivityBean getActivityInfo(Long activityId) throws Exception {
        return null;
    }

    @Override
    public void deleteActivityInfo(Long[] activityIds, Long userId) throws Exception {

    }

    @Override
    public void deleteActivityContentInfo(Long contentIds, Long userId) throws Exception {

    }

    @Override
    public void saveActivityRouteInfoes(Long activityId, Long[] routeIds, Long userId) throws Exception {

    }

    @Override
    public void deleteActivityRouteInfo(Long activityId, Long[] routeIds, Long userId) throws Exception {

    }
}
