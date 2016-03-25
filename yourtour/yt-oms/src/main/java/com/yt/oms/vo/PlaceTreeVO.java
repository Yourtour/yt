package com.yt.oms.vo;

import com.yt.business.bean.PlaceBean;
import com.yt.vo.TreeVO;

public class PlaceTreeVO extends TreeVO {
	public static PlaceTreeVO transform(PlaceBean bean) {
		if (bean == null) {
			return null;
		}

		PlaceTreeVO vo = new PlaceTreeVO();
		vo.setId(bean.getId().toString());
		vo.setParent(bean.getParentId() == null ? "#" : bean.getParentId().toString());
		vo.setText(bean.getName());

		return vo;
	}
}
