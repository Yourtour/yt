package com.yt.oms.vo.activity;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;

/**
 * Created by 林平 on 2016/3/26.
 */
public class ActivityContentVO {
    private Long    id;
    private Long 	activityId;		//标题
    private String  type;
    private String 	imageUrl;		//图片
    private String	content;			//概述
    private	String  title;		    //特色
    private String 	subTitle;       //文字描述
    private int     index;          //显示顺序

    public static ActivityContentVO transform(ActivityContentBean bean) throws Exception{
        if(bean == null){
            return null;
        }

        ActivityContentVO vo = new ActivityContentVO();
        vo.setId(bean.getId());
        vo.setImageUrl(bean.getImageUrl());
        vo.setTitle(bean.getTitle());
        vo.setSubTitle(bean.getSubTitle());
        vo.setContent(bean.getContent());
        vo.setIndex(bean.getIndex());
        vo.setType(bean.getType().toString());

        return vo;
    }

    public static ActivityContentBean transform(ActivityContentVO vo) throws Exception{
        if(vo == null) return null;

        ActivityContentBean bean = new ActivityContentBean();
        bean.setId(vo.getId());
        bean.setTitle(vo.getTitle());
        bean.setSubTitle(vo.getSubTitle());
        bean.setContent(vo.getContent());
        bean.setType(ActivityContentBean.Type.valueOf(vo.getType()));

        ActivityBean activityBean = new ActivityBean();
        activityBean.setId(vo.getActivityId());
        bean.setActivity(activityBean);

        return bean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
