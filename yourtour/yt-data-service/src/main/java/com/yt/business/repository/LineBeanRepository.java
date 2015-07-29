package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.LineBean;

public interface LineBeanRepository {
	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception;
}
