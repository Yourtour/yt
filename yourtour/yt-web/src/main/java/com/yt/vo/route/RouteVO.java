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
import com.yt.core.utils.StringUtils;
import com.yt.vo.BaseVO;
import com.yt.vo.basedata.PlaceVO;

public class RouteVO extends BaseVO {
	private String 	name; // 行程名称
	private String 	lineName;
	private String 	imageUrl;
	private long 	startDate; // 行程开始日期
	private long    endDate;

	private int  adultNum;
	private int  childNum;
	private int  olderNum;

	private	String 	impression;
	private String 	feature;

	//作为请求参数时，格式为id, 作为相应参数时格式为：id,name
	private String fromPlace; // 行程出发地点

	//作为请求参数时，格式为id1,id2..., 作为相应参数时格式为：id1,id2|name1,name2
	private String toPlaces;

	private List<RouteScheduleVO> schedules; // 行程日程列表

	public static RouteVO transform(RouteMainBean bean) {
		if (bean == null) {
			return null;
		}
		
		RouteVO vo = new RouteVO();
		vo.fromBean(bean);
		vo.setName(bean.getName());
		vo.setLineName(bean.getLineName());
		vo.setStartDate(new Date(bean.getStartDate()).getTime());

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
		bean.setEndDate(vo.getEndDate());
		bean.setFromPlace(vo.getFromPlace());
		bean.setToPlaces(vo.getToPlaces());

		bean.setAdultNum(vo.getAdultNum());
		bean.setChildNum(vo.getChildNum());
		bean.setOlderNum(vo.getOlderNum());

		if (StringUtils.isNotNull(vo.getFromPlace())) {
			PlaceBean placeBean = new PlaceBean();
			placeBean.setGraphId(Long.valueOf(vo.getFromPlace().split(",")[0]));
			bean.setFromPlaceBean(placeBean);
		}

		Set<PlaceBean> destinations = new HashSet<>();
		String[] places = vo.getToPlaces().split("[|]");
		for(String place : places){
			PlaceBean destination = new PlaceBean();
			destination.setGraphId(Long.valueOf(place.split(",")[0]));
			destinations.add(destination);
		}

		bean.setToPlaceBeans(new ArrayList<>(destinations));
		
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

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public List<RouteScheduleVO> getSchedules() {
		if(schedules == null){
			schedules = new ArrayList<>();
		}
		return schedules;
	}

	public int getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(int adultNum) {
		this.adultNum = adultNum;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public int getOlderNum() {
		return olderNum;
	}

	public void setOlderNum(int olderNum) {
		this.olderNum = olderNum;
	}

	public String getToPlaces() {
		return toPlaces;
	}

	public void setToPlaces(String toPlaces) {
		this.toPlaces = toPlaces;
	}

	public void setSchedules(List<RouteScheduleVO> schedules) {
		this.schedules = schedules;
	}
}
