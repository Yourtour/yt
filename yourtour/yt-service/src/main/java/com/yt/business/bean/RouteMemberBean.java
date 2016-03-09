package com.yt.business.bean;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

@NodeEntity
public class RouteMemberBean extends BaseBeanImpl {
	private static final long serialVersionUID = -2071225440268179136L;

	private String role;

	private UserProfileBean profile;

	public RouteMemberBean() {
		super();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserProfileBean getProfile() {
		return profile;
	}

	public void setProfile(UserProfileBean profile) {
		this.profile = profile;
	}
}
