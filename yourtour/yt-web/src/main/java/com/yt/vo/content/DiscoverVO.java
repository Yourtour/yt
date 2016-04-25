package com.yt.vo.content;

import com.yt.business.bean.DiscoverBean;

public class DiscoverVO extends ContentVO {
	public DiscoverVO() {
		super();
	}

	public static DiscoverVO transform(DiscoverBean bean) {
		if (bean == null) {
			return null;
		}
		DiscoverVO vo = new DiscoverVO();
		vo.fromBean(bean);

		return vo;
	}
}
