package com.yt.business.repository;

import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.RouteBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.rsal.neo4j.bean.INeo4JBaseBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;
import com.yt.rsal.neo4j.util.Neo4jUtils;

@Component
public class RouteRepositoryImpl implements RouteRepository {

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private ICrudOperate crudOperate;

	public RouteRepositoryImpl() {
		super();
	}

	@Override
	public void relateRoute2Line(String routeId, String lineId, boolean isAdd)
			throws Exception {
		long grID = Neo4jUtils.getGraphIDFromString(routeId);
		long glID = Neo4jUtils.getGraphIDFromString(lineId);
		if (grID == -1) {
			INeo4JBaseBean route = crudOperate.get(RouteBean.class, routeId);
			grID = route.getGraphId();
		}
		if (glID == -1) {
			INeo4JBaseBean line = crudOperate.get(LineBean.class, lineId);
			glID = line.getGraphId();
		}
		Node nodeRoute = template.getNode(grID), nodeLine = template
				.getNode(glID);
		String relation = NodeRelationshipEnum.RELATED.name();
		Neo4jUtils.maintainRelation(template, nodeRoute, nodeLine, relation,
				null, isAdd, true);
	}

	@Override
	public void relateRoute2Scene(String routeId, String sceneId, boolean isAdd)
			throws Exception {
		long grID = Neo4jUtils.getGraphIDFromString(routeId);
		long gsID = Neo4jUtils.getGraphIDFromString(sceneId);
		if (grID == -1) {
			INeo4JBaseBean route = crudOperate.get(RouteBean.class, routeId);
			grID = route.getGraphId();
		}
		if (gsID == -1) {
			INeo4JBaseBean line = crudOperate.get(LineBean.class, sceneId);
			gsID = line.getGraphId();
		}
		Node nodeRoute = template.getNode(grID), nodeScene = template
				.getNode(gsID);
		String relation = NodeRelationshipEnum.CONTAIN.name();
		Neo4jUtils.maintainRelation(template, nodeRoute, nodeScene, relation,
				null, isAdd, false);
	}

}
