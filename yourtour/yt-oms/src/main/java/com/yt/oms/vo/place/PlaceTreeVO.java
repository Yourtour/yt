package com.yt.oms.vo.place;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.PlaceBean;

public class PlaceTreeVO {
	private Long id = -1l, parentId = null;
	private String shorter, text, imageUrl, name;
	private boolean expandable = false, leaf = false;
	private int subPlaceNum;
	private List<PlaceTreeVO> subPlaces;

	public static PlaceTreeVO transform(PlaceBean bean) {
		if (bean == null) {
			return null;
		}

		PlaceTreeVO vo = new PlaceTreeVO();
		vo.setId(bean.getId());
		vo.setLeaf(bean.isLeaf());
		vo.setName(bean.getName());
		vo.setShorter(bean.getShorter());
		vo.setText(bean.getName());
		vo.setImageUrl(bean.getImageUrl());
		vo.setSubPlaceNum(bean.getSubPlaces().size());
		vo.setExpandable(vo.getSubPlaceNum() > 0);

		if (bean.getParent() != null) {
			vo.setParentId(bean.getParent().getId());
		}
		List<PlaceBean> subPlaces = bean.getSubPlaces();
		for (PlaceBean subBean : subPlaces) {
			PlaceTreeVO subVO = PlaceTreeVO.transform(subBean);
			vo.getSubPlaces().add(subVO);
		}

		return vo;
	}

	public PlaceTreeVO() {
		super();
		this.subPlaces = new Vector<PlaceTreeVO>();
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

	public String getShorter() {
		return shorter;
	}

	public void setShorter(String shorter) {
		this.shorter = shorter;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubPlaceNum() {
		return subPlaceNum;
	}

	public void setSubPlaceNum(int subPlaceNum) {
		this.subPlaceNum = subPlaceNum;
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

	public List<PlaceTreeVO> getSubPlaces() {
		return subPlaces;
	}

	public void setSubPlaces(List<PlaceTreeVO> subPlaces) {
		this.subPlaces = subPlaces;
	}

}
