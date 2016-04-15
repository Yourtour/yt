package com.yt.oms.vo.resource;

import java.util.Map;

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
	private String openTime; // 开放时间
	private String trafficIntro; // 公交信息
	private String price, currency, payment; // 价格，货币，支付信息
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
	private String place; // 目的地ID/名称对，比如： id1,name1

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
		vo.setPrice(bean.getPrice());
		vo.setCurrency(bean.getCurrency());
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

		vo.setSpecialty(BeanUtils.deserializeAsMap(bean.getSpecialty()));

		// 从目的地对象中获取ID和名称，便于前端显示
		PlaceBean place = bean.getPlace();
		if (place != null) {
			vo.setPlace(String.format("%d,%s", place.getId(), place.getName()));
		} else {
			vo.setPlace("");
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
		bean.setIntro(vo.getIntro());
		bean.setAddress(vo.getAddress());
		bean.setArriveNum(vo.getArriveNum());
		bean.setBookingMemo(vo.getBookingMemo());
		bean.setImageUrl(vo.getImageUrl());
		bean.setMember(vo.isMember());
		bean.setOpenTime(vo.getOpenTime());
		bean.setPrice(vo.getPrice());
		bean.setCurrency(vo.getCurrency());
		bean.setPayment(vo.getPayment());
		bean.setPhone(vo.getPhone());
		bean.setPosition(vo.getPosition());
		bean.setPostCode(vo.getPostCode());
		bean.setStar(vo.getStar());
		bean.setTips(vo.getTips());
		bean.setTrafficIntro(vo.getTrafficIntro());
		bean.setType(vo.getType());
		bean.setWebsite(vo.getWebsite());
		bean.setSpecialty(BeanUtils.serialize(vo.getSpecialty()));

		// 从VO中取出目的地的ID，并设置到PlaceBean中，便于后续建立关联关系
		if (vo.getPlace() != null && !vo.getPlace().isEmpty()) {
			PlaceBean place = new PlaceBean();
			place.setId(Long.valueOf(vo.getPlace().split(",")[0]));
			bean.setPlace(place);
		}
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public Map<String, Object> getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Map<String, Object> specialty) {
		this.specialty = specialty;
	}
}
