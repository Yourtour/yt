package com.yt.business.repository.neo4j;

import com.yt.business.bean.*;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@QueryResult
public class OrderTuple {
	@ResultColumn("order")
	private OrderBean order;

	@ResultColumn("user")
	private UserProfileBean  user;

	public OrderTuple(){
	}

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	public UserProfileBean getUser() {
		return user;
	}

	public void setUser(UserProfileBean user) {
		this.user = user;
	}
}