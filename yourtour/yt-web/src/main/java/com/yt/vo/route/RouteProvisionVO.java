package com.yt.vo.route;

import java.text.SimpleDateFormat;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteProvisionBean;
import com.yt.vo.BaseVO;

public class RouteProvisionVO extends BaseVO {
	private String title, memo;
	private int index;
	private Long routeId;

	public static RouteProvisionBean transform(RouteProvisionVO vo) {
		if (vo == null) {
			return null;
		}
		RouteProvisionBean bean = new RouteProvisionBean();
		vo.toBean(bean);
		bean.setTitle(vo.getTitle());
		bean.setMemo(vo.getMemo());
		bean.setIndex(vo.getIndex());
		
		RouteMainBean route = new RouteMainBean();
		route.setId(vo.getRouteId());
		bean.setRouteMain(route);
		
		return bean;
	}

	public RouteProvisionVO() {
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
	
	
	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public static void main(String[] args) throws Exception{
		String s = "Wed Nov 11 2015 22:14:41 GMT+0800 (中国标准时间)";
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.parse(s);
	}
}
