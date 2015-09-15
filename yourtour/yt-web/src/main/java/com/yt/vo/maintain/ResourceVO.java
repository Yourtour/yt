package com.yt.vo.maintain;

import com.yt.business.bean.ResourceBean;
import com.yt.business.common.Constants.ResType;
import com.yt.business.common.Constants.Status;

public class ResourceVO extends BaseVO {
	private String imageUrl; // 图片
	private ResType type; // 类型
	private String openTime; // 开放时间 hh24:mi
	private String closeTime; // 关闭时间 hh24:mi
	private String trafficIntro; // 公交信息
	private String payment; // 支付信息
	private int star; // 星级
	private boolean member; // 是否会员
	private String phone; // 联系电话
	private String address; // 地址
	private String website; // 网址
	private String position; // 位置信息
	private String postCode; // 邮编
	private int arriveNum; // 到达人数
	private double commentScore; // 点评分数
	private int commentNum; // 点评数
	private int favoriteNum; // 收藏数
	private int shareNum; // 分享数
	private String bookingMemo; // 预订须知
	private String tips; // 贴士
	private Status status;

	public void fromBean(ResourceBean bean) {
		if (bean == null) {
			return;
		}
		super.fromBean(bean);
		setAddress(bean.getAddress());
		setArriveNum(bean.getArriveNum());
		setBookingMemo(bean.getBookingMemo());
		setCloseTime(bean.getCloseTime());
		setCommentNum(bean.getCommentNum());
		setCommentScore(bean.getCommentScore());
		setFavoriteNum(bean.getFavoriteNum());
		setImageUrl(bean.getImageUrl());
		setMember(bean.isMember());
		setOpenTime(bean.getOpenTime());
		setPayment(bean.getPayment());
		setPhone(bean.getPhone());
		setPosition(bean.getPosition());
		setPostCode(bean.getPostCode());
		setShareNum(bean.getShareNum());
		setStar(bean.getStar());
		setStatus(bean.getStatus());
		setTips(bean.getTips());
		setTrafficIntro(bean.getTrafficIntro());
		setType(bean.getType());
		setWebsite(bean.getWebsite());
	}

	public void toBean(ResourceBean bean) {
		if (bean == null) {
			return;
		}
		super.toBean(bean);
		bean.setAddress(getAddress());
		bean.setArriveNum(getArriveNum());
		bean.setBookingMemo(getBookingMemo());
		bean.setCloseTime(getCloseTime());
		bean.setCommentNum(getCommentNum());
		bean.setCommentScore(getCommentScore());
		bean.setFavoriteNum(getFavoriteNum());
		bean.setImageUrl(getImageUrl());
		bean.setMember(isMember());
		bean.setOpenTime(getOpenTime());
		bean.setPayment(getPayment());
		bean.setPhone(getPhone());
		bean.setPosition(getPosition());
		bean.setPostCode(getPostCode());
		bean.setShareNum(getShareNum());
		bean.setStar(getStar());
		bean.setStatus(getStatus());
		bean.setTips(getTips());
		bean.setTrafficIntro(getTrafficIntro());
		bean.setType(getType());
		bean.setWebsite(getWebsite());
	}

	public ResourceVO() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ResType getType() {
		return type;
	}

	protected void setType(ResType type) {
		this.type = type;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getTrafficIntro() {
		return trafficIntro;
	}

	public void setTrafficIntro(String trafficIntro) {
		this.trafficIntro = trafficIntro;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getArriveNum() {
		return arriveNum;
	}

	public void setArriveNum(int arriveNum) {
		this.arriveNum = arriveNum;
	}

	public double getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(double commentScore) {
		this.commentScore = commentScore;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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

	public String getBookingMemo() {
		return bookingMemo;
	}

	public void setBookingMemo(String bookingMemo) {
		this.bookingMemo = bookingMemo;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
