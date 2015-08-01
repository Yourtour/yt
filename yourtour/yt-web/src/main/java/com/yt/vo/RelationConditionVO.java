package com.yt.vo;

public class RelationConditionVO {
	private String srcId, tarId;
	boolean add = true;

	public RelationConditionVO() {
		super();
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getTarId() {
		return tarId;
	}

	public void setTarId(String tarId) {
		this.tarId = tarId;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}
}