package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.bean.Neo4jBaseDictBeanImpl;

@NodeEntity
public class Privilege extends Neo4jBaseDictBeanImpl {

	private static final long serialVersionUID = -2573028268939178299L;
	
	private static final String INDEX_NAME = "privilege";

	@Indexed(indexName=INDEX_NAME, indexType=IndexType.FULLTEXT)
	private String memo;

	@Neo4jRelationship(relationship = "HAS", type = Role.class, direction = Direction.BOTH, isList = true)
	private transient List<Role> roles;

	public Privilege() {
		super();
		roles = new Vector<Role>();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<Role> getRoles() {
		return roles;
	}

}
