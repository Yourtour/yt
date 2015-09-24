package com.yt.vo;

public class AbbrVO {
	private String abbr = "", name = "";

	public AbbrVO() {
		super();
	}

	public AbbrVO(String abbr, String name) {
		this();
		this.abbr = abbr;
		this.name = name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
