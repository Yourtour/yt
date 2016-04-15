package com.yt.oms.vo.content;

import com.yt.business.bean.ActivityBean;
import com.yt.core.utils.DateUtils;

/**
 * Created by 林平 on 2016/3/26.
 */
public class ActivityVO extends ContentVO{
    private String 	startTime;		//开始时间
    private String 	endTime;		//结束时间

    public static ActivityVO transform(ActivityBean bean) throws Exception{
        if(bean == null){
            return null;
        }

        ActivityVO vo = new ActivityVO();
        vo.fromBean(bean);

        vo.setEndTime(DateUtils.formatDate(bean.getEndTime()));
        vo.setStartTime(DateUtils.formatDate(bean.getStartTime()));

        return vo;
    }

    public static ActivityBean transform(ActivityVO vo) throws Exception{
        if(vo == null) return null;

        ActivityBean bean = new ActivityBean();
        vo.toBean(bean);

        bean.setEndTime(DateUtils.getDateAsLong(vo.getEndTime()));
        bean.setStartTime(DateUtils.getDateAsLong(vo.getStartTime()));

        return bean;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
