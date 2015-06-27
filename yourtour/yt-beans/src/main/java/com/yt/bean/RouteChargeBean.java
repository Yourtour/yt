package com.yt.bean;

import com.yt.common.Constants.ChargeType;
import com.yt.common.Constants.Status;
import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 
 * @author Tony.Zhang
 *  行程费用实体BEAN，记录行程费用信息
 *  
 */
@HbaseTable(name = "T_ROUTE_CHARGE_INFO")
public class RouteChargeBean extends BaseBean {
	private static final long serialVersionUID = 4796045583657054183L;
	
	private	@HbaseColumn(name = "rid")		String routeId = ""; //行程ID
	private 	@HbaseColumn(name = "type")	ChargeType  type;  //费用类型
	private 	@HbaseColumn(name = "name")	String  name; //费用名称
	private	@HbaseColumn(name = "amount")	double amount; //j金额
	private	@HbaseColumn(name = "memo")	String	memo; //备注
	private	@HbaseColumn(name = "img")		String imgUrl = "";  //图片
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		Status	status;
	
	public RouteChargeBean() {
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public ChargeType getType() {
		return type;
	}

	public void setType(ChargeType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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