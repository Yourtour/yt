package com.yt.vo.home;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.HotPlayingBean;
import com.yt.business.bean.PlaceBean;
import com.yt.vo.BaseVO;
import com.yt.vo.member.UserProfileVO;
import com.yt.vo.place.PlaceVO;

public class HotPlayingVO extends BaseVO {
	private String imageUrl, feature, content;
	private List<PlaceVO> place;
	private UserProfileVO user;

	public HotPlayingVO() {
		super();
	}

	public static HotPlayingVO transform(HotPlayingBean bean) {
		if (bean == null) {
			return null;
		}
		HotPlayingVO vo = new HotPlayingVO();
		vo.fromBean(bean);
		vo.content = bean.getContent();
		vo.feature = bean.getFeature();
		vo.imageUrl = bean.getImageUrl();
		if (bean.getPlaces() != null && !bean.getPlaces().isEmpty()) {
			List<PlaceVO> places = new Vector<PlaceVO>();
			for (PlaceBean place : bean.getPlaces()) {
				PlaceVO placeVO = PlaceVO.transform(place);
				if (placeVO == null) {
					continue;
				}
				places.add(placeVO);
			}
			vo.place = places;
		}
		if (bean.getUser() != null) {
			vo.user = UserProfileVO.transform(bean.getUser());
		}
		return vo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getFeature() {
		return feature;
	}

	public String getContent() {
		return content;
	}

	public List<PlaceVO> getPlace() {
		return place;
	}

	public UserProfileVO getExpert() {
		return user;
	}

}
