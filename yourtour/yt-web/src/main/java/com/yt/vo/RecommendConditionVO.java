package com.yt.vo;

public class RecommendConditionVO {
	private String places, scenes;
	private int dayNum;

	public RecommendConditionVO() {
		super();
	}

	public String getPlaces() {
		return this.places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public String getScenes() {
		return this.scenes;
	}

	public void setScenes(String scenes) {
		this.scenes = scenes;
	}

	public int getDayNum() {
		return this.dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}
}