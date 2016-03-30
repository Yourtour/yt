package com.yt.business.bean.pack;

import com.yt.business.bean.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 林平 on 2016/3/19.
 */
public class PlacePackBean implements Serializable {
    private PlaceBean place;
    private List<BannerBean> banners;
    private List<RouteMainBean> routes;
    private List<ExpertBean> experts;
    private List<InfoBean> infoes;

    public PlacePackBean(){

    }

    public PlaceBean getPlace() {
        return place;
    }

    public void setPlace(PlaceBean place) {
        this.place = place;
    }

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public List<RouteMainBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteMainBean> routes) {
        this.routes = routes;
    }

    public List<ExpertBean> getExperts() {
        return experts;
    }

    public void setExperts(List<ExpertBean> experts) {
        this.experts = experts;
    }

    public List<InfoBean> getInfoes() {
        return infoes;
    }

    public void setInfoes(List<InfoBean> infoes) {
        this.infoes = infoes;
    }
}
