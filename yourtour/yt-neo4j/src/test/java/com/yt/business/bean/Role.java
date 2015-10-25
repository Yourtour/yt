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
public class Role extends Neo4jBaseDictBeanImpl {

	private static final long serialVersionUID = 2494119306076474497L;
	
	private static final String INDEX_NAME = "role";

	@Indexed(indexName=INDEX_NAME, indexType=IndexType.FULLTEXT)
	private String memo;

	@Neo4jRelationship(relationship = "HAS", type = Account.class, direction = Direction.BOTH, isList = true)
	private transient List<Account> accounts;
	
	@Neo4jRelationship(relationship = "HAS", type = Privilege.class, direction = Direction.BOTH, isList = true)
	private transient List<Privilege> privileges;

	public Role() {
		super();
		accounts = new Vector<Account>();
		privileges = new Vector<Privilege>();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

}
