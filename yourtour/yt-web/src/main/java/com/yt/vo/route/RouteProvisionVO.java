package com.yt.vo.route;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.vo.BaseVO;

public class RouteProvisionVO extends BaseVO {
	private String name, memo;
	private int index;
	private RouteVO route;

	public static RouteProvisionVO transform(RouteProvisionBean bean) {
		if (bean == null) {
			return null;
		}
		RouteProvisionVO vo = new RouteProvisionVO();
		vo.fromBean(bean);
		vo.setName(bean.getName());
		vo.setMemo(bean.getMemo());
		vo.setIndex(bean.getIndex());
		if (bean.getRouteMain() != null) {
			RouteVO routeVO = new RouteVO();
			routeVO.setId(bean.getRouteMain().getGraphId());
			routeVO.setName(bean.getRouteMain().getName());
			routeVO.setStartDate(new Date(bean.getRouteMain().getStartDate()));
			routeVO.setEndDate(new Date(bean.getRouteMain().getEndDate()));
			vo.setRoute(routeVO);
		}
		return vo;
	}

	public static RouteProvisionBean transform(RouteProvisionVO vo) {
		if (vo == null) {
			return null;
		}
		RouteProvisionBean bean = new RouteProvisionBean();
		vo.toBean(bean);
		bean.setName(vo.getName());
		bean.setMemo(vo.getMemo());
		bean.setIndex(vo.getIndex());
		if (vo.getRoute() != null) {
			RouteMainBean route = new RouteMainBean();
			route.setGraphId(vo.getRoute().getId());
			bean.setRouteMain(route);
		}
		return bean;
	}

	public RouteProvisionVO() {
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

	public RouteVO getRoute() {
		return route;
	}

	public void setRoute(RouteVO route) {
		this.route = route;
	}
	
	public static void main(String[] args) throws Exception{
		String s = "Wed Nov 11 2015 22:14:41 GMT+0800 (中国标准时间)";
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.parse(s);
	}
}
