package com.yt.vo.maintain.basedata;

import java.util.List;
import java.util.Vector;

public class DivisionVO {
	private Long graphId = null;
	private String code, text, fullCode;
	private boolean expanded = false, leaf = false;
	private List<DivisionVO> children;

	public DivisionVO() {
		super();
		this.children = new Vector<DivisionVO>();
	}

	public Long getId() {
		return graphId;
	}

	public void setId(Long graphId) {
		this.graphId = graphId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFullCode() {
		return fullCode;
	}

	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
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
