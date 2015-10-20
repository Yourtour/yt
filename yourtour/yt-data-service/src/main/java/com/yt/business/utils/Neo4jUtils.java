package com.yt.business.utils;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Node;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.business.BaseBeanImpl;
import com.yt.business.CrudAllInOneOperate;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.neo4j.bean.Neo4jBaseBean;

public class Neo4jUtils {
	private static final Log LOG = LogFactory.getLog(Neo4jUtils.class);

	public static long getGraphIDFromString(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public static void maintainRelation(Neo4jTemplate template,
			NodeRelationshipEnum relateEnum, BaseBeanImpl src,
			BaseBeanImpl tar, Map<String, Object> properties, boolean isAdd,
			boolean isBoth) throws Exception {
		if (src == null || tar == null) {
			throw new NullPointerException();
		}
		Node nodeSrc = template.getNode(src.getGraphId()), nodeTar = template
				.getNode(tar.getGraphId());
		String relation = relateEnum.name();
		if (isAdd) {
			if (template.getRelationshipBetween(nodeSrc, nodeTar, relation) == null) {
				template.createRelationshipBetween(nodeSrc, nodeTar, relation,
						properties);
			}
			if (isBoth) {
				if (template.getRelationshipBetween(nodeTar, nodeSrc, relation) == null) {
					template.createRelationshipBetween(nodeTar, nodeSrc,
							relation, properties);
				}
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Create a relationship success, %s[%d] %s-[:%s]->%s[%d].",
								src.getClass().getName(), src.getGraphId(),
								isBoth ? "<" : "", relation, tar.getClass()
										.getName(), tar.getGraphId()));
			}
		} else {
			template.deleteRelationshipBetween(nodeSrc, nodeTar, relation);
			if (isBoth) {
				template.deleteRelationshipBetween(nodeTar, nodeSrc, relation);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Remove a relationship success, %s[%d] %s-[:%s]->%s[%d].",
								src.getClass().getName(), src.getGraphId(),
								isBoth ? "<" : "", relation, tar.getClass()
										.getName(), tar.getGraphId()));
			}
		}
	}

	public static void maintainRelateion(Neo4jTemplate template,
			CrudAllInOneOperate crudOperate, NodeRelationshipEnum relateEnum,
			String srcId, Class<? extends BaseBeanImpl> srcClass, String tarId,
			Class<? extends BaseBeanImpl> tarClass,
			Map<String, Object> properties, boolean isAdd, boolean isBoth)
			throws Exception {
		long gsId = getGraphIDFromString(srcId);
		long gtId = getGraphIDFromString(tarId);
		if (gsId == -1) {
			Neo4jBaseBean src = crudOperate.get(srcClass, "rowKey", srcId);
			gsId = src.getGraphId();
		}
		if (gtId == -1) {
			Neo4jBaseBean tar = crudOperate.get(tarClass, "rowKey", tarId);
			gtId = tar.getGraphId();
		}
		Node nodeSrc = template.getNode(gsId), nodeTar = template.getNode(gtId);
		String relation = relateEnum.name();
		if (isAdd) {
			if (template.getRelationshipBetween(nodeSrc, nodeTar, relation) == null) {
				template.createRelationshipBetween(nodeSrc, nodeTar, relation,
						properties);
			}
			if (isBoth) {
				if (template.getRelationshipBetween(nodeTar, nodeSrc, relation) == null) {
					template.createRelationshipBetween(nodeTar, nodeSrc,
							relation, properties);
				}
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Create a relationship success, %s[%d] %s-[:%s]->%s[%d].",
								srcClass.getName(), gsId, isBoth ? "<" : "",
								relation, tarClass.getName(), gtId));
			}
		} else {
			template.deleteRelationshipBetween(nodeSrc, nodeTar, relation);
			if (isBoth) {
				template.deleteRelationshipBetween(nodeTar, nodeSrc, relation);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String
						.format("Remove a relationship success, %s[%d] %s-[:%s]->%s[%d].",
								srcClass.getName(), gsId, isBoth ? "<" : "",
								relation, tarClass.getName(), gtId));
			}
		}
	}
}
