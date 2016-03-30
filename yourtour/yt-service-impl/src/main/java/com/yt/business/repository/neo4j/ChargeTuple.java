package com.yt.business.repository.neo4j;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.yt.business.bean.RouteChargeBean;
import com.yt.business.bean.UserProfileBean;

@QueryResult
public class ChargeTuple {
	@ResultColumn("charge")
	private RouteChargeBean charge;

	@ResultColumn("owner")
	private UserProfileBean owner;

	public ChargeTuple(){
	}

	public RouteChargeBean getCharge() {
		charge.setOwner(owner);

		return charge;
	}

	public void setCharge(RouteChargeBean charge) {
		this.charge = charge;
	}

	public UserProfileBean getOwner() {
		return owner;
	}

	public void setOwner(UserProfileBean owner) {
		this.owner = owner;
	}
}