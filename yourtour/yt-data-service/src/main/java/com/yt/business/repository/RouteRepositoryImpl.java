package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.RouteBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.RouteBeanRepository;
import com.yt.business.utils.Neo4jUtils;

@Component
public class RouteRepositoryImpl extends CrudAllInOneOperateImpl implements
		RouteRepository {
	private static final Log LOG = LogFactory.getLog(RouteRepositoryImpl.class);

	@Autowired
	private RouteBeanRepository repository;

	public RouteRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#relateLine(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void relateLine(String routeId, String lineId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.RELATED;
		Neo4jUtils.maintainRelateion(super.template, this, relationship,
				routeId, RouteBean.class, lineId, LineBean.class, null, true,
				false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Create a relationship: RouteBean[%s] =%s=> LineBean[%s].",
					routeId, relationship.name(), lineId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#unrelateLine(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void unrelateLine(String routeId, String lineId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.RELATED;
		Neo4jUtils.maintainRelateion(super.template, this, relationship,
				routeId, RouteBean.class, lineId, LineBean.class, null, false,
				false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Remove a relationship: RouteBean[%s] =%s=> LineBean[%s].",
					routeId, relationship.name(), lineId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#containScene(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void containScene(String routeId, String sceneId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.CONTAIN;
		Neo4jUtils.maintainRelateion(super.template, this, relationship,
				routeId, RouteBean.class, sceneId, SceneResourceBean.class,
				null, true, false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create a relationship: RouteBean[%s] =%s=> SceneResourceBean[%s].",
							routeId, relationship.name(), sceneId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.RouteRepository#uncontainScene(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public void uncontainScene(String routeId, String sceneId) throws Exception {
		NodeRelationshipEnum relationship = NodeRelationshipEnum.CONTAIN;
		Neo4jUtils.maintainRelateion(super.template, this, relationship,
				routeId, RouteBean.class, sceneId, SceneResourceBean.class,
				null, false, false);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Remove a relationship: RouteBean[%s] =%s=> SceneResourceBean[%s].",
							routeId, relationship.name(), sceneId));
		}
	}
}
