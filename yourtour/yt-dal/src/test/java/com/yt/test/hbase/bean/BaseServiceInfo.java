package com.yt.test.hbase.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_BASESERVICEINFO", namespace = "service")
public class BaseServiceInfo extends BaseBean {
	private static final long serialVersionUID = -706371483532376012L;

	@HbaseColumn
	private String code="", name="", type="", memo="", mode="";

	@HbaseColumn(name = "prep")
	private boolean prepayment;

	@HbaseColumn(family = "d", name = "cuid")
	private String createdUserId="";

	@HbaseColumn(name = "uuid")
	private String updatedUserId="";

	@HbaseColumn(name = "ut")
	private long updatedTime;

	@HbaseColumn(name = "stat")
	private String status="";

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isPrepayment() {
		return this.prepayment;
	}

	public void setPrepayment(boolean prepayment) {
		this.prepayment = prepayment;
	}

	public String getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
