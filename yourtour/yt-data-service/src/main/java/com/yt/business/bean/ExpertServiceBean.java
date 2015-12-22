package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

@HbaseTable(name = "T_ROUTE_SERVICE")
@NodeEntity
public class ExpertServiceBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	@HbaseColumn(name = "title")
	private String title; // 行程活动名称

	@HbaseColumn(name = "memo")
	private String memo; // 行程活动备注

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = UserProfileBean.class, direction = Direction.INCOMING)
	private transient UserProfileBean user = null; // 行程活动关联的行程日程

	public ExpertServiceBean() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}
