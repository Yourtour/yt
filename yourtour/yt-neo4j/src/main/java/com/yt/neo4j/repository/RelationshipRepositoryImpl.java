package com.yt.neo4j.repository;

import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.neo4j.Neo4jBaseBean;

@Service
public class RelationshipRepositoryImpl<S extends Neo4jBaseBean, T extends Neo4jBaseBean> implements RelationshipRepository<S,T> {
	@Autowired
	RelationshipCrudOperate operator;

	public RelationshipRepositoryImpl(){}

	@Override
	public void createRelation(S src, T tar, String relationship, Direction direction) throws Exception {
		operator.createRelation(src, tar, relationship, direction);
	}

	@Override
	public void createRelation(S src, T tar, String relationship, Direction direction, Map<String, Object> propertes) throws Exception {
		operator.createRelation(src, tar, relationship, direction, propertes);
	}

	@Override
	public Map<String, Object> getRelation(S src, T tar, String relationshipType) throws Exception {
		return operator.getRelation(src, tar, relationshipType);
	}

	@Override
	public void deleteRelation(S src, T tar, String relationship) throws Exception {
		operator.deleteRelation(src,tar,relationship);
	}
}
