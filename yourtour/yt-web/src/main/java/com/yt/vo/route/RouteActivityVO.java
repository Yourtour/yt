package com.yt.vo.route;

import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants.ResType;
import com.yt.vo.BaseVO;
import com.yt.vo.resource.HotelResourceVO;
import com.yt.vo.resource.ResourceVO;
import com.yt.vo.resource.RestaurantResourceVO;
import com.yt.vo.resource.SceneResourceVO;

public class RouteActivityVO extends BaseVO {
	private String title, memo;
	private int index;
	private long date;
	private String startTime, endTime;
	private RouteScheduleVO schedule = new RouteScheduleVO();
	private ResourceVO resource = new ResourceVO();
	private List<RouteServiceVO> services = new ArrayList<>();

	public static RouteActivityVO transform(RouteActivityBean bean) {
		if (bean == null) {
			return null;
		}
		RouteActivityVO vo = new RouteActivityVO();
		vo.fromBean(bean);
		vo.setTitle(bean.getTitle());
		vo.setMemo(bean.getMemo());
		vo.setIndex(bean.getIndex());
		vo.setStartTime(bean.getStartTime());
		vo.setEndTime(bean.getEndTime());
		vo.setDate(bean.getDate());
		if (bean.getSchedule() != null) {
			RouteScheduleVO scheduleVO = new RouteScheduleVO();
			scheduleVO.setId(bean.getSchedule().getGraphId());
			scheduleVO.setDate(bean.getSchedule().getDate());
			vo.setSchedule(scheduleVO);
		}
		
		ResourceBean resourceBean = bean.getResource();
		if (resourceBean != null) {
			ResourceVO resourceVO = null;
			if (resourceBean.getType() == ResType.SCENE) {
				resourceVO = new SceneResourceVO();
			} else if (resourceBean.getType() == ResType.HOTEL) {
				resourceVO = new HotelResourceVO();
			} else if (resourceBean.getType() == ResType.FOOD) {
				resourceVO = new RestaurantResourceVO();
			}
			if (resourceVO != null) {
				resourceVO.setId(resourceBean.getGraphId());
				resourceVO.setCode(resourceBean.getCode());
				resourceVO.setName(resourceBean.getName());
				resourceVO.setImageUrl(resourceBean.getImageUrl());
				resourceVO.setAddress(resourceBean.getAddress());
				resourceVO.setPhone(resourceBean.getPhone());
				vo.setResource(resourceVO);
			}
		}
		
		for(int index = 0; index < 3; index++){
			RouteServiceVO service = new RouteServiceVO();
			service.setId(Long.valueOf(String.valueOf(index + 1)));
			service.setTitle("服务项目- " + index);
			vo.services.add(service);
		}
		
		/*List<RouteServiceBean> serviceBeans = bean.getServices();
		if(serviceBeans != null){
			for(RouteServiceBean service : serviceBeans){
				vo.services.add(ExpertServiceVO.transform(service));
			}
		}*/
		
		return vo;
	}

	public static RouteActivityBean transform(RouteActivityVO vo) {
		if (vo == null) {
			return null;
		}
		RouteActivityBean bean = new RouteActivityBean();
		vo.toBean(bean);
		bean.setTitle(vo.getTitle());
		bean.setMemo(vo.getMemo());
		bean.setIndex(vo.getIndex());
		bean.setStartTime(vo.getStartTime());
		bean.setEndTime(vo.getEndTime());
		bean.setDate(vo.getDate());
		if (vo.getSchedule() != null) {
			RouteScheduleBean schedule = new RouteScheduleBean();
			schedule.setGraphId(vo.getSchedule().getId());
			bean.setSchedule(schedule);
		}
		ResourceVO resourceVO = vo.getResource();
		if (resourceVO != null) {
			ResourceBean resourceBean = null;
			if (resourceVO.getType() == ResType.SCENE) {
				resourceBean = new SceneResourceBean();
			} else if (resourceVO.getType() == ResType.HOTEL) {
				resourceBean = new HotelResourceBean();
			} else if (resourceVO.getType() == ResType.FOOD) {
				resourceBean = new RestaurantResourceBean();
			}
			if (resourceBean != null) {
				resourceBean.setGraphId(resourceVO.getId());
				bean.setResource(resourceBean);
			}
		}
		return bean;
	}

	public RouteActivityVO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
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

	public RouteScheduleVO getSchedule() {
		return schedule;
	}

	public void setSchedule(RouteScheduleVO schedule) {
		this.schedule = schedule;
	}

	public ResourceVO getResource() {
		return resource;
	}

	public void setResource(ResourceVO resource) {
		this.resource = resource;
	}

	public List<RouteServiceVO> getServices() {
		return services;
	}

	public void setServices(List<RouteServiceVO> services) {
		this.services = services;
	}
}
