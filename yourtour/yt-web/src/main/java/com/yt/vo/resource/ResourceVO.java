package com.yt.vo.resource;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.common.Constants.ResType;
import com.yt.business.common.Constants.Status;
import com.yt.vo.BaseVO;

public class ResourceVO extends BaseVO {
	private String code;
	private String name;
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

	private String place; // 目的地
	private Long placeId; // 目的地对象ID

	public void fromBean(ResourceBean bean) {
		if (bean == null) {
			return;
		}
		super.fromBean(bean);
		setCode(bean.getCode());
		setName(bean.getName());
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

		// 从目的地对象中获取ID和名称，便于前端显示
		PlaceBean place = bean.getPlace();
		if (place != null) {
			setPlace(place.getName());
			setPlaceId(place.getGraphId());
		} else {
			setPlace("");
			setPlaceId(null);
		}
	}

	public void toBean(ResourceBean bean) {
		if (bean == null) {
			return;
		}
		super.toBean(bean);
		bean.setCode(getCode());
		bean.setName(getName());
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

		// 从VO中取出目的地的ID，并设置到PlaceBean中，便于后续建立关联关系
		PlaceBean place = new PlaceBean();
		place.setGraphId(getPlaceId());
		bean.setPlace(place);
	}

	public ResourceVO() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
}
