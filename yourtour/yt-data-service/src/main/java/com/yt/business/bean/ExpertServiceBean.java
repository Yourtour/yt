package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

@HbaseTable(name = "T_EXPERT_SERVICE")
@NodeEntity
public class ExpertServiceBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	@HbaseColumn(name = "title")
	private String title; // 服务名称
	private String free;
	private String feeIncluding;
	private String feeExcluding;
	private String withdraw;

	@HbaseColumn(name = "memo")
	private String memo; // 服务描述

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean user = null; // 服务相关达人

	public ExpertServiceBean() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeeIncluding() {
		return feeIncluding;
	}

	public void setFeeIncluding(String feeIncluding) {
		this.feeIncluding = feeIncluding;
	}

	public String getFeeExcluding() {
		return feeExcluding;
	}

	public void setFeeExcluding(String feeExcluding) {
		this.feeExcluding = feeExcluding;
	}

	public String getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}
