package com.yt.test.hbase.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

@HbaseTable(name="T_NO", namespace="")
public class NoNamespaceBean extends BaseBean {
	private static final long serialVersionUID = 6595274284231965268L;
	
	@HbaseColumn
	private String name, code;
	
	@HbaseColumn(family="info", name="stud")
	private boolean student;
}
