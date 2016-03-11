package com.yt.vo.home;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.NewsBean;
import com.yt.business.bean.NewsBean.Status;
import com.yt.business.bean.RouteMainBean;
import com.yt.vo.BaseVO;
import com.yt.vo.member.ExpertVO;
import com.yt.vo.route.RouteVO;

public class NewsVO extends BaseVO {
	private String imageUrl, title, subTitle, content;
	private long startTime, endTime;
	private Status status = Status.DRAFT;
	private List<RouteVO> routes;
	private List<ExpertVO> experts;

	public NewsVO() {
		super();
		this.routes = new Vector<RouteVO>();
		this.experts = new Vector<ExpertVO>();
	}
	
	public static NewsVO transform(NewsBean bean) {
		if (bean == null) {
			return null;
		}
		NewsVO vo = new NewsVO();
		vo.fromBean(bean);
		vo.content = bean.getContent();
		vo.endTime = bean.getEndTime();
		vo.imageUrl = bean.getImageUrl();
		vo.startTime = bean.getStartTime();
		vo.status = bean.getStatus();
		vo.subTitle = bean.getSubTitle();
		vo.title = bean.getTitle();
		for (RouteMainBean route : bean.getRoutes()) {
			if (route == null) {
				continue;
			}
			vo.routes.add(RouteVO.transform(route));
		}
		for (ExpertBean expert : bean.getExperts()) {
			if (expert == null) {
				continue;
			}
			vo.experts.add(ExpertVO.transform(expert));
		}
		return vo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getSubTitile() {
		return subTitle;
	}

	public String getContent() {
		return content;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public String getStatus() {
		return status.name();
	}

	public List<RouteVO> getRoutes() {
		return routes;
	}

	public List<ExpertVO> getExperts() {
		return experts;
	}

}
