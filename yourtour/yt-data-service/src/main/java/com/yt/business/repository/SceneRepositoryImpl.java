package com.yt.business.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.SceneResourceBean;
import com.yt.business.neo4j.repository.SceneResourceBeanRepository;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;

@Component
public class SceneRepositoryImpl extends CrudGeneralOperate implements
		SceneRepository {
	private static final Log LOG = LogFactory.getLog(SceneRepositoryImpl.class);

	@Autowired
	private SceneResourceBeanRepository sceneRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.SceneRepository#getSceneByGraphId(java.lang
	 * .Long)
	 */
	@Override
	public SceneResourceBean getSceneByGraphId(Long graphId) throws Exception {
		SceneResourceBean bean = super.template.findOne(graphId,
				SceneResourceBean.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Get scene resource by graph ID success, the resource %s.",
					bean != null ? "Found" : "Not Existed"));
		}
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.repository.SceneRepository#getScenesByPage(int, int)
	 */
	@Override
	public List<SceneResourceBean> getScenesByPage(int start, int limit)
			throws Exception {
		List<SceneResourceBean> list = sceneRepo.getScenesByPage(start, limit);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Get scene resource success, request: start(%d), limit(%d); total: %d.",
							start, limit, list.size()));
		}
		return list;
	}
}
