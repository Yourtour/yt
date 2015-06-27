package com.yt.business.bean;

import com.yt.business.common.Constants.EquityType;
import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 * 该实体定义了用户权益信息
 */
@HbaseTable(name = "T_USER_EQUITY_INFO")
public class UserEquityBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	public static enum TYPE{CASH, POINT, COUPON} //现金，积分，优惠券
	
	private 	@HbaseColumn(name = "uid")		String 	userId; 	//用户ID
	private 	@HbaseColumn(name = "type")		EquityType 	type; 	//类型
	private 	@HbaseColumn(name = "amount")	int		amount; 	//数量
	private 	@HbaseColumn(name = "src")			String 	source; 	//来源
	
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		Status	status;

	public UserEquityBean() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public EquityType getType() {
		return type;
	}

	public void setType(EquityType type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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
