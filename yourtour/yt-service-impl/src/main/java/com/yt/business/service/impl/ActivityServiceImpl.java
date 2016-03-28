package com.yt.business.service.impl;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;
import com.yt.business.bean.AdminAccountBean;
import com.yt.business.service.IActivityService;
import com.yt.core.utils.BeanUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceRefs;
import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/26.
 */
@Service
public class ActivityServiceImpl extends ServiceBase implements IActivityService {
    @Autowired
    private CrudOperate<ActivityBean> activityCrudOperate;

    @Autowired
    private CrudOperate<ActivityContentBean> activityContentCrudOperate;

    public ActivityServiceImpl(){}

    @Override
    public List<ActivityBean> queryActivityInfoes(Long nextCursor, int limit, int total, Map<String, Object> params) throws Exception {
        return this.activityCrudOperate.get();
    }

    @Override
    public void saveActivityInfo(ActivityBean activity, Long userId) throws Exception {
        ActivityBean existed = null;
        if(!activity.isNew()){
            existed = this.activityCrudOperate.get(activity.getId(), false);

            if(existed != null) {
                BeanUtils.merge(activity, existed);
            }
        }

        if(existed == null){
            existed = activity;
        }

        super.updateBaseInfo(existed, userId);
        this.activityCrudOperate.save(existed);
    }

    @Override
    public void saveActivityContentInfo(ActivityContentBean content, Long userId) throws Exception {
        ActivityContentBean existed = null;
        if(!content.isNew()){
            existed = this.activityContentCrudOperate.get(content.getId(), false);

            if(existed != null) {
                BeanUtils.merge(content, existed);
            }
        }

        if(existed == null){
            existed = content;
        }

        super.updateBaseInfo(existed, userId);
        this.activityContentCrudOperate.save(existed);

    }

    @Override
    public ActivityBean getActivityInfo(Long activityId) throws Exception {
        return this.activityCrudOperate.get(activityId);
    }

    @Override
    public void deleteActivityInfo(Long[] activityIds, Long userId) throws Exception {
        ActivityBean activityBean = null;

        for(Long activityId : activityIds){
            activityBean = this.activityCrudOperate.get(activityId, false);

            this.activityCrudOperate.delete(activityBean);
        }
    }

    @Override
    public ActivityContentBean getActivityConentInfo(Long contentId) throws Exception {
        return this.activityContentCrudOperate.get(contentId);
    }

    @Override
    public void deleteActivityContentInfo(Long contentId, Long userId) throws Exception {
        ActivityContentBean contentBean = this.activityContentCrudOperate.get(contentId, false);
        if(contentBean != null){
            this.activityContentCrudOperate.delete(contentBean);
        }
    }

    @Override
    public void saveActivityRouteInfoes(Long activityId, Long[] routeIds, Long userId) throws Exception {

    }

    @Override
    public void deleteActivityRouteInfo(Long activityId, Long[] routeIds, Long userId) throws Exception {

    }
}
