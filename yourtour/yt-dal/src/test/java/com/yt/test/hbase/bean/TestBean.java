package com.yt.test.hbase.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_TestBean")
public class TestBean extends BaseBean {
	private static final long serialVersionUID = -4950235076755223621L;
	@HbaseColumn(family = "if", name = "tn")
	private String tableName;
	@HbaseColumn(family = "if", name = "f")
	private String family;
	@HbaseColumn(family = "if", name = "q", version = "1.1")
	private String qualifier;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
}
