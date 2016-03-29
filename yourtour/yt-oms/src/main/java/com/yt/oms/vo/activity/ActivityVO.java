package com.yt.oms.vo.activity;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;
import com.yt.core.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林平 on 2016/3/26.
 */
public class ActivityVO {
    private Long    id;
    private String 	title;			//标题
    private String 	startTime;		//开始时间
    private String 	endTime;		//结束时间
    private String  createdTime;    //创建日期
    private String 	imageUrl;		//图片
    private String	brief;			//概述
    private	String  feature;		//特色
    private String 	tags;           //活动标签
    private int		recommendIndex; //推荐指数
    private int     homeRecommend = 0; //是否首页推荐
    private int     placeRecommend = 0; //是否目的地推荐

    private List<ActivityContentVO> contents = null;

    public static ActivityVO transform(ActivityBean bean) throws Exception{
        if(bean == null){
            return null;
        }

        ActivityVO vo = new ActivityVO();
        vo.setId(bean.getId());
        vo.setImageUrl(bean.getImageUrl());
        vo.setFeature(bean.getFeature());
        vo.setBrief(bean.getBrief());
        vo.setEndTime(DateUtils.formatDate(bean.getEndTime()));
        vo.setStartTime(DateUtils.formatDate(bean.getStartTime()));
        vo.setTitle(bean.getTitle());
        vo.setHomeRecommend(bean.getHomeRecommend());
        vo.setPlaceRecommend(bean.getPlaceRecommend());
        vo.setCreatedTime(DateUtils.formatDate(bean.getCreatedTime()));

        List<ActivityContentBean> contentBeans = bean.getContents();
        if(contentBeans != null){
            List<ActivityContentVO> contents = new ArrayList<>();
            for(ActivityContentBean contentBean : contentBeans){
                contents.add(ActivityContentVO.transform(contentBean));
            }

            vo.setContents(contents);
        }

        return vo;
    }

    public static ActivityBean transform(ActivityVO vo) throws Exception{
        if(vo == null) return null;

        ActivityBean bean = new ActivityBean();
        bean.setId(vo.getId());
        bean.setTitle(vo.getTitle());
        bean.setFeature(vo.getFeature());
        bean.setBrief(vo.getBrief());
        bean.setHomeRecommend(vo.getHomeRecommend());
        bean.setPlaceRecommend(vo.getPlaceRecommend());
        bean.setEndTime(DateUtils.getDateAsLong(vo.getEndTime()));
        bean.setStartTime(DateUtils.getDateAsLong(vo.getStartTime()));
        bean.setTags(vo.getTags());

        return bean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(int recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public List<ActivityContentVO> getContents() {
        return contents;
    }

    public void setContents(List<ActivityContentVO> contents) {
        this.contents = contents;
    }

    public int getHomeRecommend() {
        return homeRecommend;
    }

    public void setHomeRecommend(int homeRecommend) {
        this.homeRecommend = homeRecommend;
    }

    public int getPlaceRecommend() {
        return placeRecommend;
    }

    public void setPlaceRecommend(int placeRecommend) {
        this.placeRecommend = placeRecommend;
    }
}
