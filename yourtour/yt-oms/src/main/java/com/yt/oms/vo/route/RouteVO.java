package com.yt.oms.vo.route;

import com.yt.business.bean.*;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.DateUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.UserVO;
import com.yt.oms.vo.activity.ActivityContentVO;
import com.yt.utils.SessionUtils;
import com.yt.vo.SocialVO;

import java.io.Serializable;
import java.util.*;

public class RouteVO implements Serializable {
    private Long    id;
    private String 	name; // 行程名称
    private String 	lineName;
    private String 	imageUrl;
    private Long 	startDate; // 行程开始日期
    private Long    endDate;
    private int		duration;

    private int  charge; //费用
    private String chargeIncludes;
    private String chargeExcludes;
    private String tags;

    private String 	feature; //行程特点
    private String 	routeMemo;

    //作为请求参数时，格式为id1,id2..., 作为相应参数时格式为：id1,id2|name1,name2
    private String toPlaces;

    private UserVO user;

    private List<RouteScheduleVO> schedules = null;

    public static RouteVO transform(RouteMainBean bean) {
        if (bean == null) {
            return null;
        }

        RouteVO routeVO = new RouteVO();
        routeVO.setId(bean.getId());
        routeVO.setName(bean.getName());
        routeVO.setLineName(bean.getLineName());
        routeVO.setStartDate(bean.getStartDate());
        routeVO.setEndDate(bean.getEndDate());
        routeVO.setImageUrl(bean.getImageUrl());
        routeVO.setToPlaces(bean.getToPlaces());
        routeVO.setDuration(bean.getDuration());
        routeVO.setTags(bean.getTags());
        routeVO.setCharge(bean.getCharge());
        routeVO.setChargeExcludes(bean.getChargeExcludes());
        routeVO.setChargeIncludes(bean.getChargeIncludes());
        routeVO.setFeature(bean.getFeature());

        List<RouteScheduleBean> scheduleBeans = bean.getSchedules();
        if(CollectionUtils.isNotEmpty(scheduleBeans)){
            Collections.sort(scheduleBeans, new Comparator<RouteScheduleBean>() {
                @Override
                public int compare(RouteScheduleBean o1, RouteScheduleBean o2) {
                    String day1 = DateUtils.formatDate(o1.getDate(), "yyyy-MM-dd"),time1 = StringUtils.isNull(o1.getStartTime())? "00:00:00" : o1.getStartTime();
                    String day2 = DateUtils.formatDate(o2.getDate(), "yyyy-MM-dd"),time2 = StringUtils.isNull(o1.getStartTime())? "00:00:00" : o2.getStartTime();

                    try{
                        return DateUtils.parseDate(day1 + " " + time1).compareTo(DateUtils.parseDate(day2 + " " + time2));
                    }catch(Exception exc){
                        exc.printStackTrace();
                        return 0;
                    }
                }
            });

            List<RouteScheduleVO> voes = new ArrayList<>();
            for(RouteScheduleBean scheduleBean : scheduleBeans){
                voes.add(RouteScheduleVO.transform(scheduleBean));
            }

            routeVO.setSchedules(voes);
        }

        return routeVO;
    }


    public static RouteMainBean transform(RouteVO vo) {
        if (vo == null) {
            return null;
        }
        RouteMainBean bean = new RouteMainBean();

        bean.setId(vo.getId());
        bean.setName(vo.getName());
        bean.setLineName(vo.getLineName());
        bean.setStartDate(vo.getStartDate());
        bean.setEndDate(vo.getEndDate());
        bean.setDuration(vo.getDuration());
        bean.setToPlaces(vo.getToPlaces());
        bean.setCharge(vo.getCharge());
        bean.setFeature(vo.getFeature());
        bean.setChargeExcludes(vo.getChargeExcludes());
        bean.setChargeIncludes(vo.getChargeIncludes());

        //保存目的地
        Set<PlaceBean> destinations = new HashSet<>();
        String[] places = vo.getToPlaces().split("[|]");
        for(String place : places){
            PlaceBean destination = new PlaceBean();
            destination.setId(Long.valueOf(place.split(",")[0]));
            destinations.add(destination);
        }
        bean.setToPlaceBeans(new ArrayList<>(destinations));

        //保存日程安排
        Date start = new Date(bean.getStartDate()), scheduleDate = null;
        int duration = DateUtils.getDaySub(bean.getStartDate(), bean.getEndDate()) + 2;

        List<RouteScheduleBean> scheduleBeans = new ArrayList<>();
        for(int index = 0; index < duration; index++){
            scheduleDate = DateUtils.add(start, index, Calendar.DATE);

            RouteScheduleBean scheduleBean = new RouteScheduleBean();
            scheduleBean.setIndex(DateUtils.formatDateAsInteger(scheduleDate.getTime()) * 1000);
            scheduleBean.setDate(scheduleDate.getTime());
            scheduleBean.setName(String.format("第%s天-%s", index+1, DateUtils.formatDate(scheduleDate.getTime())));
            scheduleBean.setCreatedUserId(SessionUtils.getCurrentLoginUser());
            scheduleBean.setUpdatedUserId(SessionUtils.getCurrentLoginUser());
            scheduleBean.setCreatedTime(DateUtils.getCurrentTimeMillis());
            scheduleBean.setUpdatedTime(DateUtils.getCurrentTimeMillis());

            scheduleBeans.add(scheduleBean);
        }
        bean.setSchedules(scheduleBeans);

        return bean;
    }

    public RouteVO() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getChargeIncludes() {
        return chargeIncludes;
    }

    public void setChargeIncludes(String chargeIncludes) {
        this.chargeIncludes = chargeIncludes;
    }

    public String getChargeExcludes() {
        return chargeExcludes;
    }

    public void setChargeExcludes(String chargeExcludes) {
        this.chargeExcludes = chargeExcludes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getToPlaces() {
        return toPlaces;
    }

    public void setToPlaces(String toPlaces) {
        this.toPlaces = toPlaces;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRouteMemo() {
        return routeMemo;
    }

    public void setRouteMemo(String routeMemo) {
        this.routeMemo = routeMemo;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public List<RouteScheduleVO> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<RouteScheduleVO> schedules) {
        this.schedules = schedules;
    }
}