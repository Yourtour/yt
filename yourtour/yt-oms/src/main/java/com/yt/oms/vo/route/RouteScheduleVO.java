package com.yt.oms.vo.route;

import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.oms.vo.resource.ResourceVO;

import java.io.Serializable;

public class RouteScheduleVO implements Serializable {
    private Long    id;
    private Long    parentId;
    private Long    routeId;
    private Long    resourceId;
    private int     index = 1;
    private String 	name;   //名称
    private String  type;
    private String  reason; //推荐理由
    private String  lineName; //当天线路
    private String 	place;  //目的地
    private Long    date;   //日程时间
    private String  startTime; //开始时间
    private String  endTime;    //结束时间
    private float   duration;   //持续时间
    private String  memo;   //描述

    private ResourceVO resource = null;

    public static RouteScheduleVO transform(RouteScheduleBean bean) {
        if (bean == null) {
            return null;
        }

        RouteScheduleVO schedulevo = new RouteScheduleVO();
        schedulevo.setParentId(bean.getParentId());
        schedulevo.setId(bean.getId());
        schedulevo.setName(bean.getName());
        schedulevo.setIndex(bean.getIndex());
        schedulevo.setLineName(bean.getName());
        schedulevo.setPlace(bean.getPlaces());
        schedulevo.setDate(bean.getDate());
        schedulevo.setStartTime(bean.getStartTime());
        schedulevo.setEndTime(bean.getEndTime());
        schedulevo.setDuration(bean.getDuration());
        schedulevo.setType(bean.getType().code);
        schedulevo.setReason(bean.getReason());

        ResourceBean resource = bean.getResource();
        if(resource != null){
            schedulevo.setResource(ResourceVO.transform(resource));
        }

        return schedulevo;
    }

    public static RouteScheduleBean transform(RouteScheduleVO vo) {
        if (vo == null) {
            return null;
        }

        RouteScheduleBean bean = new RouteScheduleBean();

        bean.setParentId(vo.getParentId());
        bean.setId(vo.getId());
        bean.setName(vo.getName());
        bean.setDate(vo.getDate());
        bean.setStartTime(vo.getStartTime());
        bean.setEndTime(vo.getEndTime());
        bean.setDuration(vo.getDuration());
        bean.setReason(vo.getReason());
        bean.setMemo(vo.getMemo());
        bean.setPlaces(vo.getPlace());
        bean.setType(RouteScheduleBean.ScheduleType.getType(vo.getType()));

        if(vo.getResourceId() != null){
            bean.setResource(new ResourceBean(vo.getResourceId()));
        }

        if(vo.getRouteId() != null){
            bean.setRouteMain(new RouteMainBean(vo.getRouteId()));
        }

        bean.setMemo(vo.getMemo());

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceVO getResource() {
        return resource;
    }

    public void setResource(ResourceVO resource) {
        this.resource = resource;
    }
}