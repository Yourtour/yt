package com.yt.vo;

import java.io.Serializable;
import java.util.List;

import com.yt.vo.basedata.PlaceVO;

public class LaunchVO implements Serializable {
	private static final long serialVersionUID = 7353580902980438916L;
	
	List<PlaceVO> places = null;
	
	public LaunchVO() {
	}

	public void setPlaces(List<PlaceVO> places){
		this.places = places;
	}
	
	public List<PlaceVO> getPlaces(){
		return this.places;
	}
}
