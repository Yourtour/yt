package com.yt.oms.vo.place;

import com.yt.business.bean.PlaceBean;

public class PlaceVO {
	private Long id = -1l, parentId = null;
	private String code, shorter, text, memo, imageUrl, name;
	private boolean expandable = false, leaf = false;
	private int favoiteNum = 0; // 关注人数
	private int goneNum = 0; // 去过人数
	private int goingNum = 0; // 想去人数
	private int subPlaceNum = 0; // 下辖目的地个数
	private int alongNum = 0;
	private int resourceNum; // 关联资源个数

	public static PlaceVO transform(PlaceBean bean) {
		if (bean == null) {
			return null;
		}

		PlaceVO vo = new PlaceVO();
		vo.setCode(bean.getCode());
		vo.setId(bean.getId());
		vo.setLeaf(bean.isLeaf());
		vo.setName(bean.getName());
		vo.setMemo(bean.getMemo());
		vo.setShorter(bean.getShorter());
		vo.setText(bean.getName());
		vo.setImageUrl(bean.getImageUrl());
		vo.setGoingNum(bean.getGoingNum());
		vo.setGoneNum(bean.getGoneNum());
		vo.setSubPlaceNum(bean.getSubPlaces().size());
		vo.setExpandable(vo.getSubPlaceNum() > 0);
		vo.setAlongNum(bean.getAlongNum());

		if (bean.getParent() != null) {
			vo.setParentId(bean.getParent().getId());
		}
		if (bean.getSubPlaces() != null) {
			vo.setSubPlaceNum(bean.getSubPlaces().size());
		}
		if (bean.getResources() != null) {
			vo.setResourceNum(bean.getResources().size());
		}
		return vo;
	}

	public PlaceVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long graphId) {
		this.id = graphId;
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

	public String getShorter() {
		return shorter;
	}

	public void setShorter(String shorter) {
		this.shorter = shorter;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAlongNum() {
		return alongNum;
	}

	public void setAlongNum(int alongNum) {
		this.alongNum = alongNum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getFavoiteNum() {
		return favoiteNum;
	}

	public void setFavoiteNum(int favoiteNum) {
		this.favoiteNum = favoiteNum;
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

	public int getSubPlaceNum() {
		return subPlaceNum;
	}

	public void setSubPlaceNum(int num) {
		this.subPlaceNum = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResourceNum() {
		return resourceNum;
	}

	public void setResourceNum(int resourceNum) {
		this.resourceNum = resourceNum;
	}

}
