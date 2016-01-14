package com.yt.vo.route;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.vo.BaseVO;
import com.yt.vo.member.ExpertVO;
import com.yt.vo.member.UserProfileVO;

public class RouteRecommendVO extends BaseVO {
	private String 	name; // 行程名称
	private String 	lineName;
	private String 	imageUrl;

	private float rankScore;
	private int thumbupNum;
	private int favoriteNum;
	private int shareNum;
	private int commentNum;

	private String 	reason; //推荐理由
	private String 	feature; //行程特点
	private String 	chargeMemo; //费用说明
	private String	withdrawMemo; //退改说明
	private String  useMemo; //使用说明
	private String  orderMemo; //预订须知

	private ExpertVO expert;

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

		ExpertBean expert  = bean.getExpert();
		if(expert != null){
			routeVO.setExpert(ExpertVO.transform(expert));
		}

		return routeVO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getRankScore() {
		return rankScore;
	}

	public void setRankScore(float rankScore) {
		this.rankScore = rankScore;
	}

	public int getThumbupNum() {
		return thumbupNum;
	}

	public void setThumbupNum(int thumbupNum) {
		this.thumbupNum = thumbupNum;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getShareNum() {
		return shareNum;
	}

	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public ExpertVO getExpert() {
		return expert;
	}

	public void setExpert(ExpertVO expert) {
		this.expert = expert;
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
