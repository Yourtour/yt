package com.yt.vo.maintain.basedata;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.PlaceBean;
import com.yt.business.common.Constants.Status;

public class DivisionVO {
	private Long graphId = null, parentId = null;
	private String code, shorter, text, memo, fullCode;
	private boolean expanded = false, leaf = false;
	private Status status = Status.ACTIVED;
	private List<DivisionVO> children;

	public static PlaceBean transform(DivisionVO vo) {
		if (vo == null) {
			return null;
		}
		PlaceBean bean = new PlaceBean();
		bean.setRowKey(vo.getCode());
		bean.setCode(vo.getCode());
		if (vo.getGraphId().longValue() != -1l) {
			bean.setGraphId(vo.getGraphId());
		}
		bean.setName(vo.getText());
		bean.setMemo(vo.getMemo());
		bean.setShorter(vo.getShorter());
		bean.setStatus(vo.getStatus());
		return bean;
	}

	public static DivisionVO transform(PlaceBean bean) {
		if (bean == null) {
			return null;
		}
		DivisionVO vo = new DivisionVO();
		vo.setCode(bean.getCode());
		vo.setExpanded(bean.hasChild());
		vo.setId(bean.getGraphId());
		vo.setLeaf(!bean.hasChild());
		vo.setMemo(bean.getMemo());
		vo.setShorter(bean.getShorter());
		vo.setStatus(bean.getStatus());
		vo.setText(bean.getName());
		return vo;
	}

	public DivisionVO() {
		super();
		this.children = new Vector<DivisionVO>();
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

	public String getFullCode() {
		return fullCode;
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

	public List<DivisionVO> getChildren() {
		return children;
	}
}
