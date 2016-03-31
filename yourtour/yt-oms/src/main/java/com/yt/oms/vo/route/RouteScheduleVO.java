package com.yt.oms.vo.route;

import com.yt.business.bean.RouteMainBean;

import java.io.Serializable;

public class RouteScheduleVO implements Serializable {
    private Long    id;
    private String 	name;   //名称
    private String  lineName; //当天线路
    private String 	place;  //目的地
    private Long    date;   //时间
    private String  memo;   //描述

    public static RouteScheduleVO transform(RouteMainBean bean) {
        if (bean == null) {
            return null;
        }

        RouteScheduleVO routeVO = new RouteScheduleVO();
        routeVO.setId(bean.getId());
        routeVO.setName(bean.getName());

        return routeVO;
    }


    public static RouteMainBean transform(RouteScheduleVO vo) {
        if (vo == null) {
            return null;
        }
        RouteMainBean bean = new RouteMainBean();

        bean.setId(vo.getId());
        bean.setName(vo.getName());

        return bean;
    }

    public RouteScheduleVO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}