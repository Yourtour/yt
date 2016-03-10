package com.yt.vo.place;

import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.core.utils.CollectionUtils;
import com.yt.vo.resource.ResourceVO;

public class PlaceVO {
	private Long graphId = -1l, parentId = null;
	private String parentCode, code, shorter, text, memo, imageUrl, name;
	private boolean expandable = false, leaf = false;
	private int favoiteNum = 0; //关注人数
	private int goneNum = 0;  //去过人数
	private int goingNum = 0;  //想去人数
	private int num = 0;  //下辖目的地个数
	private int alongNum = 0;

	private List<ResourceVO> resources;

	public static PlaceBean transform(PlaceVO vo) {
		if (vo == null) {
			return null;
		}
		PlaceBean bean = new PlaceBean();
		bean.setCode(vo.getCode());
		if (vo.getGraphId() != null && vo.getGraphId().longValue() != -1l) {
			bean.setId(vo.getGraphId());
		}
		bean.setName(vo.getText());
		bean.setMemo(vo.getMemo());
		bean.setShorter(vo.getShorter());
		if (vo.getParentId() != null && vo.getParentId().longValue() != -1l) {
			PlaceBean parent = new PlaceBean();
			parent.setId(vo.getParentId());
			bean.setParent(parent);
		}

		return bean;
	}

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
		vo.setNum(bean.getSubs().size());
		vo.setExpandable(CollectionUtils.isNotEmpty(bean.getSubs()));
		vo.setAlongNum(bean.getAlongNum());

		if(bean.getParent() != null){
			vo.setParentCode(bean.getParent().getCode());
		}

		List<? extends ResourceBean> resources = bean.getResources();
		if(CollectionUtils.isNotEmpty(resources)){
			List<ResourceVO> voes = new ArrayList<>();
			for(ResourceBean resource : resources){
				voes.add(ResourceVO.transform(resource));
			}

			vo.setResources(voes);
		}

		return vo;
	}

	public PlaceVO() {
		super();
	}

	public Long getGraphId() {
		return graphId;
	}

	public Long getId() {
		return graphId;
	}

	public void setId(Long graphId) {
		this.graphId = graphId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResourceVO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceVO> resources) {
		this.resources = resources;
	}
}
