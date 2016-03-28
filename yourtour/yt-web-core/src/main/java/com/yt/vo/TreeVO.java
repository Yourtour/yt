package com.yt.vo;

/**
 * 前端树控件数据结构
 */
public class TreeVO {
	private String id; //当前节点ID
	private String parent;  //父节点ID
	private String text;  //节点显示名称
	private String icon; //节点图标
	private String state; //节点状态, opened, disabled, selected

	public TreeVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}