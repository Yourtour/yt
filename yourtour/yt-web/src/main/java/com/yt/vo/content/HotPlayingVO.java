package com.yt.vo.content;

import com.yt.business.bean.HotPlayingBean;

public class HotPlayingVO extends ContentVO {
	public HotPlayingVO() {
		super();
	}

	public static HotPlayingVO transform(HotPlayingBean bean) {
		if (bean == null) {
			return null;
		}
		HotPlayingVO vo = new HotPlayingVO();
		vo.fromBean(bean);

		return vo;
	}
}
