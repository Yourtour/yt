package com.yt.oms.vo.resource;

import com.yt.business.BaseBeanImpl;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.vo.SocialVO;

public class ResourceVO extends SocialVO {
	private String code;
	private String name;
	private String imageUrl; // 图片
	private ResourceType type; // 类型
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
	private String bookingMemo; // 预订须知
	private String tips; // 贴士
	private String tags;
	private String feature;
	private String placeName; // 目的地名称
	private Long placeId; // 目的地对象ID

	private int goodNum; // 好评
	private int mediumNum; // 中评
	private int badNum; // 差评
	private int imageNum; // 晒图
	private double healthScore;
	private double trafficScore;
	private double facilityScore;
	private double environmentScore;
	private double serviceScore;

	public static ResourceVO transform(ResourceBean bean) {
		if (bean == null) {
			return null;
		}

		ResourceVO vo = new ResourceVO();
		vo.fromBean(bean);
		return vo;
	}

	public static ResourceBean transform(ResourceVO vo) {
		if (vo == null) {
			return null;
		}

		ResourceBean bean = new ResourceBean();
		vo.toBean(bean);
		return bean;
	}

	public ResourceVO() {
		super();
	}

	@Override
	public void fromBean(BaseBeanImpl bean) {
		if (bean == null) {
			return;
		}
		super.fromBean(bean);
	
		ResourceBean resource = (ResourceBean) bean;
		this.setCode(resource.getCode());
		this.setName(resource.getName());
		this.setAddress(resource.getAddress());
		this.setArriveNum(resource.getArriveNum());
		this.setBookingMemo(resource.getBookingMemo());
		this.setFavoriteNum(resource.getFavoriteNum());
		this.setImageUrl(resource.getImageUrl());
		this.setMember(resource.isMember());
		this.setOpenTime(resource.getOpenTime());
		this.setPayment(resource.getPayment());
		this.setPhone(resource.getPhone());
		this.setPosition(resource.getPosition());
		this.setPostCode(resource.getPostCode());
		this.setShareNum(resource.getShareNum());
		this.setStar(resource.getStar());
		this.setTips(resource.getTips());
		this.setTrafficIntro(resource.getTrafficIntro());
		this.setType(resource.getType());
		this.setWebsite(resource.getWebsite());
	
		this.setTags(resource.getTags());
		this.setFeature(resource.getFeature());
		this.setCommentNum(resource.getCommentNum());
		this.setGoodNum(resource.getGoodNum());
		this.setBadNum(resource.getBadNum());
		this.setMediumNum(resource.getMediumNum());
		this.setImageNum(resource.getImageNum());
	
		this.setCommentScore(resource.getCommentScore());
		this.setHealthScore(resource.getHealthScore());
		this.setTrafficScore(resource.getTrafficScore());
		this.setFacilityScore(resource.getFacilityScore());
		this.setEnvironmentScore(resource.getEnvironmentScore());
		this.setServiceScore(resource.getServiceScore());
	
		// 从目的地对象中获取ID和名称，便于前端显示
		PlaceBean place = resource.getPlace();
		if (place != null) {
			this.setPlace(place.getName());
			this.setPlaceId(place.getId());
		} else {
			this.setPlace("");
			this.setPlaceId(null);
		}
	}

	@Override
	public void toBean(BaseBeanImpl bean) {
		toBean(bean);
		ResourceBean resource = (ResourceBean) bean;
		resource.setCode(getCode());
		resource.setName(getName());
		resource.setAddress(getAddress());
		resource.setArriveNum(getArriveNum());
		resource.setBookingMemo(getBookingMemo());
		resource.setCommentNum(getCommentNum());
		resource.setCommentScore(getCommentScore());
		resource.setFavoriteNum(getFavoriteNum());
		resource.setImageUrl(getImageUrl());
		resource.setMember(isMember());
		resource.setOpenTime(getOpenTime());
		resource.setPayment(getPayment());
		resource.setPhone(getPhone());
		resource.setPosition(getPosition());
		resource.setPostCode(getPostCode());
		resource.setShareNum(getShareNum());
		resource.setStar(getStar());
		resource.setTips(getTips());
		resource.setTrafficIntro(getTrafficIntro());
		resource.setType(getType());
		resource.setWebsite(getWebsite());
	
		// 从VO中取出目的地的ID，并设置到PlaceBean中，便于后续建立关联关系
		PlaceBean place = new PlaceBean();
		place.setId(getPlaceId());
		resource.setPlace(place);
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

	public ResourceType getType() {
		return type;
	}

	protected void setType(ResourceType type) {
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

	public String getPlace() {
		return placeName;
	}

	public void setPlace(String place) {
		this.placeName = place;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public double getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(double healthScore) {
		this.healthScore = healthScore;
	}

	public double getTrafficScore() {
		return trafficScore;
	}

	public void setTrafficScore(double trafficScore) {
		this.trafficScore = trafficScore;
	}

	public double getFacilityScore() {
		return facilityScore;
	}

	public double getEnvironmentScore() {
		return environmentScore;
	}

	public void setEnvironmentScore(double environmentScore) {
		this.environmentScore = environmentScore;
	}

	public double getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(double serviceScore) {
		this.serviceScore = serviceScore;
	}

	public void setFacilityScore(double facilityScore) {
		this.facilityScore = facilityScore;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public int getMediumNum() {
		return mediumNum;
	}

	public void setMediumNum(int mediumNum) {
		this.mediumNum = mediumNum;
	}

	public int getBadNum() {
		return badNum;
	}

	public void setBadNum(int badNum) {
		this.badNum = badNum;
	}

	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
}
