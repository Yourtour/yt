package com.yt.oms.vo.content;

import com.yt.business.bean.*;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.route.RouteVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林平 on 2016/3/26.
 */
public class ContentVO {
    private Long    id;
    private String 	title;			//标题
    private String  subTitle;       //副标题
    private String  createdTime;    //创建日期
    private String  placeInfo;         //目的地
    private String 	imageUrl;		//图片
    private String	brief;			//概述
    private	String  feature;		//特色
    private String 	tagInfo;           //活动标签
    private String  userInfo;       //内容提供者信息
    private String  content;        //活动内容
    private int     homeRecommend = 0; //是否首页推荐
    private int     placeRecommend = 0; //是否目的地推荐
    private List<RouteVO>  routes = null;   //关联行程
    private String routeInfo = null;

    public void fromBean(ActivityBean bean) throws Exception{
        this.setId(bean.getId());
        this.setImageUrl(bean.getImageUrl());
        this.setFeature(bean.getFeature());
        this.setBrief(bean.getBrief());
        this.setContent(bean.getContent());
        this.setTitle(bean.getTitle());
        this.setSubTitle(bean.getSubTitle());
        this.setUserInfo(bean.getUserInfo());
        this.setPlaceInfo(bean.getPlaceInfo());
        this.setTagInfo(bean.getTagInfo());
        this.setHomeRecommend(bean.getHomeRecommend());
        this.setPlaceRecommend(bean.getPlaceRecommend());
        this.setCreatedTime(DateUtils.formatDate(bean.getCreatedTime()));

        List<RouteMainBean> routeMainBeans = bean.getRoutes();
        if(CollectionUtils.isNotEmpty(routeMainBeans)){
            List<RouteVO> routevoes = new ArrayList<>();
            for(RouteMainBean routeMainBean : routeMainBeans){
                routevoes.add(RouteVO.transform(routeMainBean));
            }
            this.setRoutes(routevoes);
        }
    }

    public void toBean(ContentBean bean) throws Exception{
        bean.setId(this.getId());
        bean.setTitle(this.getTitle());
        bean.setSubTitle(this.getSubTitle());
        bean.setPlaceInfo(this.getPlaceInfo());
        bean.setFeature(this.getFeature());
        bean.setBrief(this.getBrief());
        bean.setHomeRecommend(this.getHomeRecommend());
        bean.setPlaceRecommend(this.getPlaceRecommend());
        bean.setTagInfo(this.getTagInfo());
        bean.setContent(this.getContent());
        bean.setUserInfo(this.getUserInfo());

        if(StringUtils.isNotNull(this.getRouteInfo())){
            List<RouteMainBean> routebeans = new ArrayList<>();
            String[] routeIds = this.getRouteInfo().split(",");
            for(String routeId : routeIds){
                RouteMainBean routebean = new RouteMainBean(Long.parseLong(routeId));

                routebeans.add(routebean);
            }

            bean.setRoutes(routebeans);
        }

        if(StringUtils.isNotNull(this.getUserInfo())){
            UserProfileBean user = new UserProfileBean(Long.valueOf(this.getUserInfo().split(",")[0]));
            bean.setUser(user);
        }

        if(StringUtils.isNotNull(this.getPlaceInfo())){
            String[] places = this.getPlaceInfo().split("[|]");
            List<PlaceBean> placeBeans = new ArrayList<>();
            for(String place : places){
                placeBeans.add(new PlaceBean(Long.parseLong(place.split(",")[0])));
            }

            bean.setPlaceList(placeBeans);
        }
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public String getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(String tagInfo) {
        this.tagInfo = tagInfo;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    public List<RouteVO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteVO> routes) {
        this.routes = routes;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
