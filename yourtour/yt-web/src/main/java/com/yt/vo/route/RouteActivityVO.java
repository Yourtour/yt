package com.yt.vo.route;

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
	private String startTime, endTime;
	private RouteScheduleVO schedule = new RouteScheduleVO();
	private ResourceVO resource = new ResourceVO();

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
		bean.setTitle(vo.getTitle());
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

}
