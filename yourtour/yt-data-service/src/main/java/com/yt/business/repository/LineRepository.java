package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.LineBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface LineRepository extends ICrudOperate {

	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception;

	public List<LineBean> queryRecommandLine2(String[] places, int dayNum,
			String[] scenes) throws Exception;
}
