package com.yt.business.bean;

import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义达人申请信息
 */
@HbaseTable(name = "T_EXPERT_APPLY_INFO")
public class ExpertApplyBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	private 	@HbaseColumn(name = "alid")	String applyUserId = ""; //申请人ID
	private	@HbaseColumn(name = "aldt")	long applyDate; //申请时间
	private	@HbaseColumn(name = "img")		String imageUrl; //申请附件
	private	@HbaseColumn(name = "memo")		String memo; //申请备注
	
	private 	@HbaseColumn(name = "arid")	String approveUserId = ""; //审批人ID
	private	@HbaseColumn(name = "ardt")	long approveDate; //审批时间
	private	@HbaseColumn(name = "ardt")	long approveReuslt; //审批时间

	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")	Status	status;
	
	public ExpertApplyBean() {
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(long applyDate) {
		this.applyDate = applyDate;
	}

	public String getMemo() {
		return memo;
	}
 
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}

	public long getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}

	public long getApproveReuslt() {
		return approveReuslt;
	}

	public void setApproveReuslt(long approveReuslt) {
		this.approveReuslt = approveReuslt;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
