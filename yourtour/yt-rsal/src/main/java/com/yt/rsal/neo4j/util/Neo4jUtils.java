package com.yt.rsal.neo4j.util;

import java.util.Map;

import org.neo4j.graphdb.Node;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.rsal.neo4j.bean.INeo4JBaseBean;
import com.yt.rsal.neo4j.repository.ICrudOperate;

public class Neo4jUtils {

	public static long getGraphIDFromString(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception ex) {
			return -1;
		}
	}

	public static void maintainRelateion(Neo4jTemplate template,
			ICrudOperate crudOperate, NodeRelationshipEnum relateEnum,
			String srcId, Class<? extends INeo4JBaseBean> srcClass,
			String tarId, Class<? extends INeo4JBaseBean> tarClass,
			Map<String, Object> properties, boolean isAdd, boolean isBoth)
			throws Exception {
		long gsId = getGraphIDFromString(srcId);
		long gtId = getGraphIDFromString(tarId);
		if (gsId == -1) {
			INeo4JBaseBean src = crudOperate.get(srcClass, srcId);
			gsId = src.getGraphId();
		}
		if (gtId == -1) {
			INeo4JBaseBean tar = crudOperate.get(tarClass, tarId);
			gtId = tar.getGraphId();
		}
		Node srcNode = template.getNode(gsId), tarNode = template.getNode(gtId);
		String relation = relateEnum.name();
		if (isAdd) {
			if (template.getRelationshipBetween(srcNode, tarNode, relation) == null) {
				template.createRelationshipBetween(srcNode, tarNode, relation,
						properties);
			}
			if (isBoth) {
				if (template.getRelationshipBetween(tarNode, srcNode, relation) == null) {
					template.createRelationshipBetween(tarNode, srcNode,
							relation, properties);
				}
			}
		} else {
			template.deleteRelationshipBetween(srcNode, tarNode, relation);
			if (isBoth) {
				template.deleteRelationshipBetween(tarNode, srcNode, relation);
			}
		}
	}
}
