package com.yt.business.repository;

import java.util.List;

import com.yt.business.bean.SceneResourceBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface SceneRepository extends ICrudOperate {
	public SceneResourceBean getSceneByGraphId(Long graphId) throws Exception;

	public List<SceneResourceBean> getScenesByPage(int start, int limit)
			throws Exception;
}
