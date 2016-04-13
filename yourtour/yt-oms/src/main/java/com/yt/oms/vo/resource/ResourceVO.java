package com.yt.oms.vo.resource;

import java.util.Map;

import com.yt.business.BaseBeanImpl;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.ResourceBean.ResourceType;
import com.yt.core.utils.BeanUtils;
import com.yt.vo.SocialVO;

public class ResourceVO extends SocialVO {
	private String code;
	private String name;
	private String intro;
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

	private Map<String, Object> specialty;

	public static ResourceVO transform(ResourceBean bean) throws Exception {
		if (bean == null) {
			return null;
		}

		ResourceVO vo = new ResourceVO();
		vo.fromBean(bean);
		vo.setCode(bean.getCode());
		vo.setName(bean.getName());
		vo.setAddress(bean.getAddress());
		vo.setIntro(bean.getIntro());
		vo.setArriveNum(bean.getArriveNum());
		vo.setBookingMemo(bean.getBookingMemo());
		vo.setFavoriteNum(bean.getFavoriteNum());
		vo.setImageUrl(bean.getImageUrl());
		vo.setMember(bean.isMember());
		vo.setOpenTime(bean.getOpenTime());
		vo.setPayment(bean.getPayment());
		vo.setPhone(bean.getPhone());
		vo.setPosition(bean.getPosition());
		vo.setPostCode(bean.getPostCode());
		vo.setShareNum(bean.getShareNum());
		vo.setStar(bean.getStar());
		vo.setTips(bean.getTips());
		vo.setTrafficIntro(bean.getTrafficIntro());
		vo.setType(bean.getType());
		vo.setWebsite(bean.getWebsite());

		vo.setTags(bean.getTags());
		vo.setFeature(bean.getFeature());
		vo.setCommentNum(bean.getCommentNum());
		vo.setGoodNum(bean.getGoodNum());
		vo.setBadNum(bean.getBadNum());
		vo.setMediumNum(bean.getMediumNum());
		vo.setImageNum(bean.getImageNum());

		vo.setCommentScore(bean.getCommentScore());
		vo.setHealthScore(bean.getHealthScore());
		vo.setTrafficScore(bean.getTrafficScore());
		vo.setFacilityScore(bean.getFacilityScore());
		vo.setEnvironmentScore(bean.getEnvironmentScore());
		vo.setServiceScore(bean.getServiceScore());

		vo.setSpecialty(BeanUtils.deserializeAsMap(bean.getSpecialty()));

		// 从目的地对象中获取ID和名称，便于前端显示
		PlaceBean place = bean.getPlace();
		if (place != null) {
			vo.setPlace(place.getName());
			vo.setPlaceId(place.getId());
		} else {
			vo.setPlace("");
			vo.setPlaceId(null);
		}
		return vo;
	}

	public static ResourceBean transform(ResourceVO vo) throws Exception {
		if (vo == null) {
			return null;
		}

		ResourceBean bean = new ResourceBean();
		vo.toBean(bean);
		bean.setCode(vo.getCode());
		bean.setName(vo.getName());
		bean.setAddress(vo.getAddress());
		bean.setArriveNum(vo.getArriveNum());
		bean.setBookingMemo(vo.getBookingMemo());
		bean.setCommentNum(vo.getCommentNum());
		bean.setCommentScore(vo.getCommentScore());
		bean.setFavoriteNum(vo.getFavoriteNum());
		bean.setImageUrl(vo.getImageUrl());
		bean.setMember(vo.isMember());
		bean.setOpenTime(vo.getOpenTime());
		bean.setPayment(vo.getPayment());
		bean.setPhone(vo.getPhone());
		bean.setPosition(vo.getPosition());
		bean.setPostCode(vo.getPostCode());
		bean.setShareNum(vo.getShareNum());
		bean.setStar(vo.getStar());
		bean.setTips(vo.getTips());
		bean.setTrafficIntro(vo.getTrafficIntro());
		bean.setType(vo.getType());
		bean.setWebsite(vo.getWebsite());
		bean.setSpecialty(BeanUtils.serialize(vo.getSpecialty()));

		// 从VO中取出目的地的ID，并设置到PlaceBean中，便于后续建立关联关系
		PlaceBean place = new PlaceBean();
		place.setId(vo.getPlaceId());
		bean.setPlace(place);
		return bean;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Map<String, Object> getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Map<String, Object> specialty) {
		this.specialty = specialty;
	}
}
