package com.yt.vo.home;

import com.yt.business.bean.HotPlayingBean;
import com.yt.vo.BaseVO;
import com.yt.vo.member.ExpertVO;
import com.yt.vo.place.PlaceVO;

public class HotPlayingVO extends BaseVO {
	private String imageUrl, feature, content;
	private PlaceVO place;
	private ExpertVO expert;
	
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
		if (bean.getPlace() != null) {
			vo.place = PlaceVO.transform(bean.getPlace());
		}
		if (bean.getExpert() != null) {
			vo.expert = ExpertVO.transform(bean.getExpert());
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

	public PlaceVO getPlace() {
		return place;
	}

	public ExpertVO getExpert() {
		return expert;
	}

}
