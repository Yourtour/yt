package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.business.bean.SceneResourceBean;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;

public class SceneRepositoryImpl extends CrudGeneralOperate implements
		SceneRepository {
	private static final Log LOG = LogFactory.getLog(SceneRepositoryImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.SceneRepository#getSceneByGraphId(java.lang
	 * .Long)
	 */
	@Override
	public SceneResourceBean getSceneByGraphId(Long graphId) throws Exception {
		return super.template.findOne(graphId, SceneResourceBean.class);
	}

}
