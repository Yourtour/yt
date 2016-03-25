package com.yt.vo.place;

import com.yt.business.bean.PlaceBean;

public class PlaceVO {
	private Long id = -1l;
	private Long parentId; //父级目的地
	private String code;  //目的地编码，
	private String name;
	private String intro; //简介
	private String feature; //特色
	private String traffic; //交通
	private String imageUrl; //图片， 可以有多张
	private String specialty; //特产
	private String memo = ""; // 备注
	private int goneNum = 0; // 去过人数
	private int goingNum = 0; // 想去人数

	public static PlaceBean transform(PlaceVO vo) {
		if (vo == null) {
			return null;
		}

		PlaceBean bean = new PlaceBean();
		bean.setCode(vo.getCode());
		if (vo.getId() != null && vo.getId().longValue() != -1l) {
			bean.setId(vo.getId());
		}
		bean.setParentId(vo.getParentId());
		bean.setName(vo.getName());
		bean.setCode(vo.getCode());
		bean.setFeature(vo.getFeature());
		bean.setIntro(vo.getIntro());
		bean.setTraffic(vo.getTraffic());
		bean.setSpecialty(vo.getSpecialty());
		bean.setMemo(vo.getMemo());

		return bean;
	}

	public static PlaceVO transform(PlaceBean bean) {
		if (bean == null) {
			return null;
		}

		PlaceVO vo = new PlaceVO();
		vo.setCode(bean.getCode());
		vo.setId(bean.getId());
		vo.setName(bean.getName());
		vo.setMemo(bean.getMemo());
		vo.setImageUrl(bean.getImageUrl());
		vo.setGoingNum(bean.getGoingNum());
		vo.setGoneNum(bean.getGoneNum());
		vo.setSpecialty(bean.getSpecialty());
		vo.setTraffic(bean.getTraffic());
		vo.setIntro(bean.getIntro());
		vo.setParentId(bean.getParentId());
		vo.setFeature(bean.getFeature());

		return vo;
	}

	public PlaceVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getGoneNum() {
		return goneNum;
	}

	public void setGoneNum(int goneNum) {
		this.goneNum = goneNum;
	}

	public int getGoingNum() {
		return goingNum;
	}

	public void setGoingNum(int goingNum) {
		this.goingNum = goingNum;
	}
}
