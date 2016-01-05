package com.yt.vo.route;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.vo.member.UserProfileVO;

public class RouteRecommendVO extends RouteRecommendItemVO {
	private String 	reason; //推荐理由
	private String 	feature; //行程特点
	private String 	chargeMemo; //费用说明
	private String	withdrawMemo; //退改说明
	private String  useMemo; //使用说明
	private String  orderMemo; //预订须知

	public RouteRecommendVO() {
		super();
	}

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
		routeVO.setImageUrl(bean.getImageUrl());
		routeVO.setReason(bean.getReason());
		routeVO.setFeature(bean.getFeature());
		routeVO.setChargeMemo(bean.getChargeMemo());
		routeVO.setWithdrawMemo(bean.getWithdrawMemo());
		routeVO.setOrderMemo(bean.getOrderMemo());
		routeVO.setUseMemo(bean.getUseMemo());

		UserProfileBean owner = bean.getOwner();
		if(owner != null){
			routeVO.setUser(new UserProfileVO(bean.getOwner()));
		}

		return routeVO;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getChargeMemo() {
		return chargeMemo;
	}

	public void setChargeMemo(String chargeMemo) {
		this.chargeMemo = chargeMemo;
	}

	public String getWithdrawMemo() {
		return withdrawMemo;
	}

	public void setWithdrawMemo(String withdrawMemo) {
		this.withdrawMemo = withdrawMemo;
	}

	public String getUseMemo() {
		return useMemo;
	}

	public void setUseMemo(String useMemo) {
		this.useMemo = useMemo;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
}
