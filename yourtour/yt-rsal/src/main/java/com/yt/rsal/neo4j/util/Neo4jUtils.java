package com.yt.rsal.neo4j.util;

import java.util.Map;

import org.neo4j.graphdb.Node;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class Neo4jUtils {

	public static long getGraphIDFromString(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception ex) {
			return -1;
		}
	}

	public static void maintainRelation(Neo4jTemplate template, Node srcNode,
			Node tarNode, String relation, Map<String, Object> properties,
			boolean isAdd, boolean isBoth) throws Exception {
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
