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
public class Account extends Neo4jBaseDictBeanImpl {

	private static final long serialVersionUID = 7217816942777913868L;

	private static final String INDEX_NAME = "account";

	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String memo;

	@Neo4jRelationship(relationship = "ASSIGNED", type = Person.class, direction=Direction.INCOMING)
	private transient Person person;

	@Neo4jRelationship(relationship = "HAS", type = Role.class, direction = Direction.BOTH, isSet = true)
	private transient List<Role> roles;

	public Account() {
		super();
		roles = new Vector<Role>();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
}
