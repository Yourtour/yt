package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

@HbaseTable(name = "T_EXPERT_APPROVEMENT")
@NodeEntity
public class ExpertApprovementBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6259294378320824143L;

	public static final String RESULT_PASS = "pass";
	public static final String RESULT_REJECT = "reject";

	private String result;

	@HbaseColumn(name = "memo")
	private String memo; // 描述

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_APPROVED, type = ExpertApplicationBean.class, direction = Direction.OUTGOING)
	private transient ExpertApplicationBean application = null; // 服务相关达人

	public ExpertApprovementBean() {
		super();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public ExpertApplicationBean getApplication() {
		return application;
	}

	public void setApplication(ExpertApplicationBean application) {
		this.application = application;
	}
}
