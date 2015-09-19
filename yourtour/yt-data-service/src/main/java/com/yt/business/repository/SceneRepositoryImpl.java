package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.SceneResourceBeanRepository;
import com.yt.business.neo4j.repository.SceneResourcePlaceTuple;
import com.yt.business.utils.Neo4jUtils;
import com.yt.rsal.neo4j.bean.INeo4JBaseBean;
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
		SceneResourcePlaceTuple tuple = sceneRepo.getSceneByGraphId(graphId);
		SceneResourceBean scene = null;
		if (tuple != null) {
			scene = tuple.getScene();
			scene.setPlace(tuple.getPlace());
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Get scene resource by graph ID success, the resource %s.",
					scene != null ? "Found" : "Not Existed"));
		}
		return scene;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.repository.SceneRepository#getScenesByPage(int, int)
	 */
	@Override
	public List<SceneResourceBean> getScenesByPage(int start, int limit)
			throws Exception {
		List<SceneResourcePlaceTuple> tuples = sceneRepo.getScenesByPage(start,
				limit);
		List<SceneResourceBean> list = new Vector<SceneResourceBean>(
				tuples.size());
		for (SceneResourcePlaceTuple tuple : tuples) {
			SceneResourceBean scene = tuple.getScene();
			scene.setPlace(tuple.getPlace());
			list.add(scene);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Get scene resource success, request: start(%d), limit(%d); total: %d.",
							start, limit, list.size()));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.CrudGeneralOperate#save(com.yt.rsal.neo4j
	 * .bean.INeo4JBaseBean, java.lang.String, boolean)
	 */
	@Override
	public INeo4JBaseBean save(INeo4JBaseBean neo4jBean, String operator,
			boolean saveFail2Hbase) throws Exception {
		SceneResourceBean bean = (SceneResourceBean) super.save(neo4jBean,
				operator, saveFail2Hbase);
		SceneResourceBean scene = (SceneResourceBean) neo4jBean;
		// 建立景点和目的地的关联关系
		if (scene.getPlace() != null && scene.getPlace().getGraphId() != null) {
			// 建立线路到目的地的关系
			PlaceBean place = super.template.findOne(scene.getPlace()
					.getGraphId(), PlaceBean.class);
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.AT, bean, place, null, true, false);
		}
		return bean;
	}
}
