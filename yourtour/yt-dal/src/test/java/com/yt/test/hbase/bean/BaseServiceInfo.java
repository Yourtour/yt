package com.yt.test.hbase.bean;

import com.yt.dal.hbase.BaseDictBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_BASESERVICEINFO", namespace = "service")
public class BaseServiceInfo extends BaseDictBean {
	private static final long serialVersionUID = -706371483532376012L;

	@HbaseColumn
	private String code = "", name = "", type = "", memo = "", mode = "";

	@HbaseColumn(name = "prep")
	private boolean prepayment;

	@HbaseColumn(family = "d", name = "cuid")
	private String createdUserId = "";

	@HbaseColumn(name = "uuid")
	private String updatedUserId = "";

	@HbaseColumn(name = "ut")
	private long updatedTime;

	@HbaseColumn(name = "stat")
	private BaseServiceInfoEnum status = BaseServiceInfoEnum.DRAFT;

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
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

	@Override
	public String getCreatedUserId() {
		return this.createdUserId;
	}

	@Override
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Override
	public String getUpdatedUserId() {
		return this.updatedUserId;
	}

	@Override
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	@Override
	public long getUpdatedTime() {
		return this.updatedTime;
	}

	@Override
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public Enum<?> getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(Enum<?> status) {
		if (status instanceof BaseServiceInfoEnum) {
			this.status = (BaseServiceInfoEnum) status;
		}
	}

	public enum BaseServiceInfoEnum {
		DRAFT, VALIDATED, ACTIVED, CLOSED, CANCELED
	}

}
