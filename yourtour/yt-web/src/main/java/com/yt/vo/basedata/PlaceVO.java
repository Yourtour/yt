package com.yt.vo.basedata;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.PlaceBean;
import com.yt.business.common.Constants.Status;

public class PlaceVO {
	private Long graphId = -1l, parentId = null;
	private String code, shorter, text, memo;
	private boolean expanded = false, leaf = false;
	private Status status = Status.ACTIVED;
	private List<PlaceVO> children;

	public static PlaceBean transform(PlaceVO vo) {
		if (vo == null) {
			return null;
		}
		PlaceBean bean = new PlaceBean();
		bean.setCode(vo.getCode());
		if (vo.getGraphId() != null && vo.getGraphId().longValue() != -1l) {
			bean.setGraphId(vo.getGraphId());
		}
		bean.setName(vo.getText());
		bean.setMemo(vo.getMemo());
		bean.setShorter(vo.getShorter());
		bean.setStatus(vo.getStatus());
		if (vo.getParentId() != null && vo.getParentId().longValue() != -1l) {
			PlaceBean parent = new PlaceBean();
			parent.setGraphId(vo.getParentId());
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
		vo.setExpanded(false);
		vo.setId(bean.getGraphId());
		vo.setLeaf(bean.isLeaf());
		vo.setMemo(bean.getMemo());
		vo.setShorter(bean.getShorter());
		vo.setStatus(bean.getStatus());
		vo.setText(bean.getName());
		
		if(bean.getSubs() != null){
			for(PlaceBean sub : bean.getSubs()){
				vo.children.add(transform(sub));
			}
		}
		return vo;
	}

	public PlaceVO() {
		super();
		this.children = new Vector<PlaceVO>();
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
		return shorter;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<PlaceVO> getChildren() {
		return children;
	}
	
	public int getNum(){
		return this.children.size();
	}
}
