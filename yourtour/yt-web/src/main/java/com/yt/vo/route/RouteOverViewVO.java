package com.yt.vo.route;

import com.yt.business.bean.RouteMainBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.BaseVO;

public class RouteOverViewVO extends BaseVO {
	private RouteMainBean route;
	
	public RouteOverViewVO(RouteMainBean route){
		this.route = route;
	}

	public String getName() {
		return route.getName();
	}

	public String getStartDate() {
		return DateUtils.formatDate(route.getStartDate());
	}

	public String getEndDate() {
		return DateUtils.formatDate(route.getEndDate());
	}
	
	public String getImageUrl(){
		return route.getImageUrl();
	}
}
