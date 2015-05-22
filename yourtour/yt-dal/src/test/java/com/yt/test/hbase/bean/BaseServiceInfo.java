package com.yt.test.hbase.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_BASE_SERVICE_INFO")
public class BaseServiceInfo extends BaseBean {
	private static final long serialVersionUID = -706371483532376012L;

	@HbaseColumn
	private String code, name, type, memo, mode;

	@HbaseColumn(name = "prep")
	private boolean prepayment;

	@HbaseColumn(family="d", name = "cuid")
	private String createdUserId;

	@HbaseColumn(name = "uuid")
	private String updatedUserId;

	@HbaseColumn(name = "ut")
	private long updatedTime;

	@HbaseColumn(name = "stat")
	private String status;
}
