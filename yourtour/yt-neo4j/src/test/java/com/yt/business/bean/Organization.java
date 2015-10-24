package com.yt.business.bean;

import java.util.List;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.bean.Neo4jBaseDictBeanImpl;

@NodeEntity
public class Organization extends Neo4jBaseDictBeanImpl {

	private static final long serialVersionUID = -9015892270107594069L;
	
	private static final String INDEX_NAME = "origanization";

	@Indexed(indexName=INDEX_NAME, indexType=IndexType.FULLTEXT)
	private String memo ;
	
	@Neo4jRelationship(relationship="MANAGED", type=Account.class, direction = Direction.OUTGOING)
	private transient Account manager;

	@Neo4jRelationship(relationship = "HAS", type = Role.class, direction = Direction.OUTGOING, isSet = true)
	private transient List<Role> roles;
	
	@Neo4jRelationship(relationship="HAS", type=Account.class, direction = Direction.INCOMING, isSet = true)
	private transient List<Account> accounts;

	public Organization() {
		super();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Account getManager() {
		return manager;
	}

	public void setManager(Account manager) {
		this.manager = manager;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

}
