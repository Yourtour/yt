package com.yt.vo.route;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RestaurantResourceBean;
import com.yt.business.bean.RouteActivityBean;
import com.yt.business.bean.RouteScheduleBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.vo.BaseVO;
import com.yt.vo.resource.HotelResourceVO;
import com.yt.vo.resource.ResourceVO;
import com.yt.vo.resource.RestaurantResourceVO;
import com.yt.vo.resource.SceneResourceVO;

public class RouteActivityVO extends BaseVO {
	private String name, memo;
	private int index;
	private long startTime, endTime;
	private RouteScheduleVO schedule;
	private ResourceVO resource;

	public static RouteActivityVO transform(RouteActivityBean bean) {
		if (bean == null) {
			return null;
		}
		RouteActivityVO vo = new RouteActivityVO();
		vo.fromBean(bean);
		vo.setName(bean.getName());
		vo.setMemo(bean.getMemo());
		vo.setIndex(bean.getIndex());
		vo.setStartTime(bean.getStartTime());
		vo.setEndTime(bean.getEndTime());
		if (bean.getSchedule() != null) {
			RouteScheduleVO scheduleVO = new RouteScheduleVO();
			scheduleVO.setId(bean.getSchedule().getGraphId());
			scheduleVO.setDate(bean.getSchedule().getDate());
			scheduleVO.setDescription(bean.getSchedule().getDescription());
			vo.setSchedule(scheduleVO);
		}
		ResourceBean resourceBean = bean.getResource();
		if (resourceBean != null) {
			ResourceVO resourceVO = null;
			if (resourceBean instanceof SceneResourceBean) {
				resourceVO = new SceneResourceVO();
			} else if (resourceBean instanceof HotelResourceBean) {
				resourceVO = new HotelResourceVO();
			} else if (resourceBean instanceof RestaurantResourceBean) {
				resourceVO = new RestaurantResourceVO();
			}
			if (resourceVO != null) {
				resourceVO.setId(resourceBean.getGraphId());
				resourceVO.setCode(resourceBean.getCode());
				resourceVO.setName(resourceBean.getName());
				vo.setResource(resourceVO);
			}
		}
		return vo;
	}

	public static RouteActivityBean transform(RouteActivityVO vo) {
		if (vo == null) {
			return null;
		}
		RouteActivityBean bean = new RouteActivityBean();
		vo.toBean(bean);
		bean.setName(vo.getName());
		bean.setMemo(vo.getMemo());
		bean.setIndex(vo.getIndex());
		bean.setStartTime(vo.getStartTime());
		bean.setEndTime(vo.getEndTime());
		if (vo.getSchedule() != null) {
			RouteScheduleBean schedule = new RouteScheduleBean();
			schedule.setGraphId(vo.getSchedule().getId());
			bean.setSchedule(schedule);
		}
		ResourceVO resourceVO = vo.getResource();
		if (resourceVO != null) {
			ResourceBean resourceBean = null;
			if (resourceVO instanceof SceneResourceVO) {
				resourceBean = new SceneResourceBean();
			} else if (resourceVO instanceof HotelResourceVO) {
				resourceBean = new HotelResourceBean();
			} else if (resourceVO instanceof RestaurantResourceVO) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
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

}
