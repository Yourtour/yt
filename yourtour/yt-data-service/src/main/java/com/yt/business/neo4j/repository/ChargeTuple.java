package com.yt.business.neo4j.repository;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.RouteChargeBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

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