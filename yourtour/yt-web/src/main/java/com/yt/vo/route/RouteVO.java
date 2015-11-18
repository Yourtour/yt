package com.yt.vo.route;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.vo.BaseVO;
import com.yt.vo.basedata.PlaceVO;

public class RouteVO extends BaseVO {
	private String 	name; // 行程名称
	private String 	lineName;
	private String 	imageUrl;
	private long 	startDate; // 行程开始日期
	private int 	duration;
	private	String 	impression;
	private String 	feature;
	
	private PlaceVO fromPlace; // 行程出发地点
	private List<RouteScheduleVO> schedules; // 行程日程列表
	private List<RouteProvisionVO> provisions; // 行程准备列表

	public static RouteVO transform(RouteMainBean bean) {
		if (bean == null) {
			return null;
		}
		
		RouteVO vo = new RouteVO();
		vo.fromBean(bean);
		vo.setName(bean.getName());
		vo.setLineName(bean.getLineName());
		vo.setStartDate(new Date(bean.getStartDate()));
		vo.setDuration(bean.getDuration());
		
		if (bean.getFromPlace() != null) {
			PlaceVO placeVO = new PlaceVO();
			placeVO.setId(bean.getFromPlace().getGraphId());
			vo.setFromPlace(placeVO);
		}
		
		if (bean.getSchedules() != null && bean.getSchedules().size() > 0) {
			for (RouteScheduleBean scheduleBean : bean.getSchedules()) {
				if (scheduleBean == null) {
					continue;
				}
				RouteScheduleVO scheduleVO = RouteScheduleVO.transform(scheduleBean);
				vo.getSchedules().add(scheduleVO);
			}
		}
		
		if (bean.getProvisions() != null && bean.getProvisions().size() > 0) {
			for (RouteProvisionBean provisionBean : bean.getProvisions()) {
				if (provisionBean == null) {
					continue;
				}
				RouteProvisionVO provisionVO = RouteProvisionVO.transform(provisionBean);
				vo.getProvisions().add(provisionVO);
			}
		}
		
		return vo;
	}

	public static RouteMainBean transform(RouteVO vo) {
		if (vo == null) {
			return null;
		}
		RouteMainBean bean = new RouteMainBean();
		
		vo.toBean(bean);
		bean.setName(vo.getName());
		bean.setStartDate(vo.getStartDate());
		bean.setDuration(vo.getDuration());

		if (vo.getFromPlace() != null) {
			PlaceBean placeBean = new PlaceBean();
			placeBean.setGraphId(vo.getFromPlace().getId());
			bean.setFromPlace(placeBean);
		}
		
		Set<PlaceBean> destinations = new HashSet<>();
		int duration = 0;
		if (vo.getSchedules() != null && vo.getSchedules().size() > 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(bean.getStartDate());
			
			for (RouteScheduleVO scheduleVO : vo.getSchedules()) {
				if (scheduleVO == null) {
					continue;
				}
				
				PlaceBean destination = new PlaceBean();
				destination.setGraphId(Long.valueOf(scheduleVO.getPlaceIds()));
				destinations.add(destination);
				
				duration += scheduleVO.getDays();
				for(int index = 0; index < scheduleVO.getDays(); index++){
					RouteScheduleBean scheduleBean = new RouteScheduleBean();
					
					scheduleBean.setGraphId(scheduleVO.getId());
					scheduleBean.setDate(calendar.getTimeInMillis());
					scheduleBean.setPlaces(scheduleVO.getPlaces());
					bean.getSchedules().add(scheduleBean);
					
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		
		bean.setDuration(duration);
		bean.setDestinations(new ArrayList<>(destinations));
		
		return bean;
	}

	public RouteVO() {
		super();
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

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}


	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate.getTime();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public PlaceVO getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(PlaceVO fromPlace) {
		this.fromPlace = fromPlace;
	}

	public List<RouteScheduleVO> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<RouteScheduleVO> schedules) {
		this.schedules = schedules;
	}

	public List<RouteProvisionVO> getProvisions() {
		return provisions;
	}

	public void setProvisions(List<RouteProvisionVO> provisions) {
		this.provisions = provisions;
	}
}
