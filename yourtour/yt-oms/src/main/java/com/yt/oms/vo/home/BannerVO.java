package com.yt.oms.vo.home;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.BannerBean;
import com.yt.business.bean.BannerBean.Status;
import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.RouteMainBean;

/**
 * 首页Banner的数据对象
 * 
 * @author John.Peng
 * 
 */
public class BannerVO {
	private String title, subTitle, imageUrl, content;
	private long id, startTime, endTime;
	private BannerBean.Status status = Status.DRAFT;
	private List<RouteVO> routes;
	private List<ExpertVO> experts;
	private List<ActivityVO> activities;

	public class RouteVO {
		private long id = -1;
		private String name, imageUrl;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	}

	public class ExpertVO {
		private long id = -1;
		private String nickName, imageUrl;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String name) {
			this.nickName = name;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	}

	public class ActivityVO {
		private long id = -1;
		private String title, imageUrl;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	}

	public BannerVO() {
		super();
	}

	public static BannerVO transform(BannerBean bean) {
		if (bean == null) {
			return null;
		}
		BannerVO vo = new BannerVO();
		vo.setId(bean.getId());
		vo.setTitle(bean.getTitle());
		vo.setSubTitle(bean.getSubTitle());
		vo.setContent(bean.getContent());
		vo.setImageUrl(bean.getImageUrl());
		vo.setStatus(bean.getStatus());
		vo.setStartTime(bean.getStartTime());
		vo.setEndTime(bean.getEndTime());
		vo.transformRoutes(bean.getRoutes());
		vo.transformExperts(bean.getExperts());
		vo.transformActivities(bean.getActivities());
		return vo;
	}

	public static BannerBean transform(BannerVO vo) {
		if (vo == null) {
			return null;
		}
		BannerBean bean = new BannerBean();
		bean.setId(vo.getId());
		bean.setTitle(vo.getTitle());
		bean.setSubTitle(vo.getSubTitle());
		bean.setContent(vo.getContent());
		bean.setImageUrl(vo.getImageUrl());
		bean.setStatus(vo.getStatus());
		bean.setStartTime(vo.getStartTime());
		bean.setEndTime(vo.getEndTime());
		if (vo.getRoutes() != null) {
			for (RouteVO routeVO : vo.getRoutes()) {
				if (routeVO == null) {
					continue;
				}
				RouteMainBean routeBean = new RouteMainBean();
				routeBean.setId(routeVO.getId());
				bean.getRoutes().add(routeBean);
			}
		}
		if (vo.getExperts() != null) {
			for (ExpertVO expertVO : vo.getExperts()) {
				if (expertVO == null) {
					continue;
				}
				ExpertBean expertBean = new ExpertBean();
				expertBean.setId(expertVO.getId());
				bean.getExperts().add(expertBean);
			}
		}
		if (vo.getActivities() != null) {
			for (ActivityVO activityVO : vo.getActivities()) {
				if (activityVO == null) {
					continue;
				}
				ActivityBean activityBean = new ActivityBean();
				activityBean.setId(activityVO.getId());
				bean.getActivities().add(activityBean);
			}
		}
		return bean;
	}

	private void transformRoutes(List<RouteMainBean> routeBeans) {
		List<RouteVO> routes = new Vector<RouteVO>();
		for (RouteMainBean bean : routeBeans) {
			if (bean == null) {
				continue;
			}
			RouteVO vo = new RouteVO();
			vo.setId(bean.getId());
			vo.setName(bean.getName());
			vo.setImageUrl(bean.getImageUrl());
			routes.add(vo);
		}
		this.setRoutes(routes);
	}

	private void transformExperts(List<ExpertBean> expertBeans) {
		List<ExpertVO> experts = new Vector<ExpertVO>();
		for (ExpertBean bean : expertBeans) {
			if (bean == null) {
				continue;
			}
			ExpertVO vo = new ExpertVO();
			vo.setId(bean.getId());
			vo.setNickName(bean.getProfile().getNickName());
			vo.setImageUrl(bean.getProfile().getImageUrl());
			experts.add(vo);
		}
		this.setExperts(experts);
	}

	private void transformActivities(List<ActivityBean> activityBeans) {
		List<ActivityVO> activities = new Vector<ActivityVO>();
		for (ActivityBean bean : activityBeans) {
			if (bean == null) {
				continue;
			}
			ActivityVO vo = new ActivityVO();
			vo.setId(bean.getId());
			vo.setTitle(bean.getTitle());
			vo.setImageUrl(bean.getImageUrl());
			activities.add(vo);
		}
		this.setActivities(activities);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public BannerBean.Status getStatus() {
		return status;
	}

	public void setStatus(BannerBean.Status status) {
		this.status = status;
	}

	public List<RouteVO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteVO> routes) {
		this.routes = routes;
	}

	public List<ExpertVO> getExperts() {
		return experts;
	}

	public void setExperts(List<ExpertVO> experts) {
		this.experts = experts;
	}

	public List<ActivityVO> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityVO> activities) {
		this.activities = activities;
	}

}
