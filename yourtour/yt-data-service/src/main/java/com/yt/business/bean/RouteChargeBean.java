package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@HbaseTable(name = "T_ROUTE_CHARGE")
@NodeEntity
@JsonRootName("charge")
public class RouteChargeBean extends BaseBeanImpl {
	private static final long serialVersionUID = -2071225440268179136L;

	private String  name;
	private String 	item; //
	private String	type;
	private double 	amount;
	private double 	payment;
	private int		percent;
	private Long 	chargeDate;
	private String 	memo;
	private String 	imageUrl;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = RouteMainBean.class, direction = Direction.OUTGOING)
	private transient RouteMainBean route = null; // 行程出发地点

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean owner = null; // 行程所有者

	public RouteChargeBean() {
		super();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Long chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public RouteMainBean getRoute() {
		return route;
	}

	public void setRoute(RouteMainBean route) {
		this.route = route;
	}

	public UserProfileBean getOwner() {
		return owner;
	}

	public void setOwner(UserProfileBean owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPayment() {
		return payment == 0 ? amount : payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
