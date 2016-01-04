package com.yt.vo.route;

import com.yt.business.bean.RouteMainBean;

public class RouteRecommendVO extends RouteRecommendItemVO {
	public static RouteRecommendVO transform(RouteMainBean bean) {
		if (bean == null) {
			return null;
		}

		RouteRecommendVO routeVO = new RouteRecommendVO();
		routeVO.fromBean(bean);
		routeVO.setName(bean.getName());
		routeVO.setLineName(bean.getLineName());
		routeVO.setCommentNum(bean.getCommentNum());
		routeVO.setThumbupNum(bean.getThumbupNum());
		routeVO.setFavoriteNum(bean.getFavoriteNum());
		routeVO.setShareNum(bean.getShareNum());




		return routeVO;
	}

	public RouteRecommendVO() {
		super();
	}

}
