package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.LineBean;

public interface LineRepository {

	public void relateLine2Scene(String lineId, String sceneId, boolean isAdd)
			throws Exception;

	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception;
	
	public List<LineBean> queryRecommandLine2(String[] places, int dayNum,
			String[] scenes) throws Exception;
}
