package com.yt.vo.route;

import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
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
	private String status;
	private String startTime, endTime;
	private RouteScheduleVO schedule = new RouteScheduleVO();
	private ResourceVO resource = new ResourceVO();
	private List<RouteActivityItemVO> items = new ArrayList<>();

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
			scheduleVO.setId(bean.getSchedule().getId());
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
				resourceVO.setId(resourceBean.getId());
				resourceVO.setCode(resourceBean.getCode());
				resourceVO.setName(resourceBean.getName());
				resourceVO.setImageUrl(resourceBean.getImageUrl());
				resourceVO.setAddress(resourceBean.getAddress());
				resourceVO.setPhone(resourceBean.getPhone());
				vo.setResource(resourceVO);
			}
		}

		List<RouteActivityItemBean> items = bean.getItems();
		if(items != null){
			List<RouteActivityItemVO> voes = new ArrayList<>();
			for(RouteActivityItemBean item : items){
				voes.add(RouteActivityItemVO.transform(item));
			}
			vo.setItems(voes);
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
		bean.setDate(vo.getDate());
		bean.setStatus(Constants.Status.valueOf(vo.getStatus()));

		if (vo.getSchedule() != null) {
			RouteScheduleBean schedule = new RouteScheduleBean();
			schedule.setId(vo.getSchedule().getId());
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
				resourceBean.setId(resourceVO.getId());
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

	public List<RouteActivityItemVO> getItems() {
		return items;
	}

	public void setItems(List<RouteActivityItemVO> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
