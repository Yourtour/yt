package com.yt.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yt.business.bean.LaunchBean;
import com.yt.vo.place.PlaceVO;

public class LaunchVO implements Serializable {
	private static final long serialVersionUID = 7353580902980438916L;
	
	List<PlaceVO> places = null;

	public LaunchVO() {
	}

	public static LaunchVO transform(LaunchBean bean){
		LaunchVO vo = new LaunchVO();

		return vo;
	}

	public void setPlaces(List<PlaceVO> places){
		this.places = places;
	}
	
	public List<PlaceVO> getPlaces(){
		return this.places;
	}

	public List<SimpleVO> getTags(){
		List<SimpleVO> tags = new ArrayList<>();

		tags.add(new SimpleVO(1l, "1", "标签一"));
		tags.add(new SimpleVO(2l, "2", "标签二"));
		tags.add(new SimpleVO(3l, "3", "标签三"));
		tags.add(new SimpleVO(4l, "4", "标签四"));
		tags.add(new SimpleVO(5l, "5", "标签五"));
		tags.add(new SimpleVO(6l, "6", "标签六"));
		tags.add(new SimpleVO(7l, "7", "标签七"));
		tags.add(new SimpleVO(8l, "8", "标签八"));
		tags.add(new SimpleVO(9l, "9", "标签九"));
		return tags;
	}
}
