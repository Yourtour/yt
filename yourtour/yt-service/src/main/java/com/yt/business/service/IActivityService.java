package com.yt.business.service;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;

import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/26.
 */
public interface IActivityService {
    /**
     * 保存活动基本信息
     * @param activity
     * @param userId
     * @throws Exception
     */
    public void saveActivityInfo(ActivityBean activity, Long userId) throws Exception;

    /**
     * 活动活动内容信息
     * @param content
     * @param userId
     * @throws Exception
     */
    public void saveActivityContentInfo(ActivityContentBean content, Long userId) throws Exception;

    /**
     * 获取活动信息
     * @param activityId
     * @return
     * @throws Exception
     */
    public ActivityBean getActivityInfo(Long activityId) throws Exception;

    /**
     * 活动查询
     * @param nextCursor
     * @param limit
     * @param total
     * @param params
     * @return
     * @throws Exception
     */
    public List<ActivityBean> queryActivityInfoes(Long nextCursor, int limit, int total, Map<String, Object> params) throws Exception;

    /**
     * 删除活动信息
     * @param activityIds=
     * @param userId
     * @throws Exception
     */
    public void deleteActivityInfo(Long[] activityIds, Long userId) throws Exception;
}
