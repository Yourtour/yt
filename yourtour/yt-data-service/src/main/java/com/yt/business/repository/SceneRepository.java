package com.yt.business.repository;

import com.yt.business.bean.SceneResourceBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public interface SceneRepository extends ICrudOperate {
	public SceneResourceBean getSceneByGraphId(Long graphId) throws Exception;
}
