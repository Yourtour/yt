package com.yt.vo.route;

import java.util.List;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.vo.BaseVO;
import com.yt.vo.basedata.PlaceVO;

public class RouteVO extends BaseVO {
	private String name; // 行程名称
	private long startDate; // 行程开始日期
	private long endDate; // 行程结束日期
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
		vo.setStartDate(bean.getStartDate());
		vo.setEndDate(bean.getEndDate());
		if (bean.getFromPlace() != null) {
			PlaceVO placeVO = new PlaceVO();
			placeVO.setId(bean.getFromPlace().getGraphId());
			placeVO.setCode(bean.getFromPlace().getCode());
			placeVO.setShorter(bean.getFromPlace().getShorter());
			placeVO.setMemo(bean.getFromPlace().getMemo());
			vo.setFromPlace(placeVO);
		}
		if (bean.getSchedules() != null && bean.getSchedules().size() > 0) {
			for (RouteScheduleBean scheduleBean : bean.getSchedules()) {
				if (scheduleBean == null) {
					continue;
				}
				RouteScheduleVO scheduleVO = new RouteScheduleVO();
				scheduleVO.setId(scheduleBean.getGraphId());
				scheduleVO.setDate(scheduleBean.getDate());
				scheduleVO.setIndex(scheduleBean.getIndex());
				scheduleVO.setDescription(scheduleBean.getDescription());
				vo.getSchedules().add(scheduleVO);
			}
		}
		if (bean.getProvisions() != null && bean.getProvisions().size() > 0) {
			for (RouteProvisionBean provisionBean : bean.getProvisions()) {
				if (provisionBean == null) {
					continue;
				}
				RouteProvisionVO provisionVO = new RouteProvisionVO();
				provisionVO.setId(provisionBean.getGraphId());
				provisionVO.setIndex(provisionBean.getIndex());
				provisionVO.setName(provisionBean.getName());
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
		bean.setEndDate(vo.getEndDate());
		if (vo.getFromPlace() != null) {
			PlaceBean placeBean = new PlaceBean();
			placeBean.setGraphId(vo.getFromPlace().getId());
			bean.setFromPlace(placeBean);
		}
		if (vo.getSchedules() != null && vo.getSchedules().size() > 0) {
			for (RouteScheduleVO scheduleVO : vo.getSchedules()) {
				if (scheduleVO == null) {
					continue;
				}
				RouteScheduleBean scheduleBean = new RouteScheduleBean();
				scheduleBean.setGraphId(scheduleVO.getId());
				bean.getSchedules().add(scheduleBean);
			}
		}
		if (vo.getProvisions() != null && vo.getProvisions().size() > 0) {
			for (RouteProvisionVO provisionVO : vo.getProvisions()) {
				if (provisionVO == null) {
					continue;
				}
				RouteProvisionBean provisionBean = new RouteProvisionBean();
				provisionBean.setGraphId(provisionVO.getId());
				bean.getProvisions().add(provisionBean);
			}
		}
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
