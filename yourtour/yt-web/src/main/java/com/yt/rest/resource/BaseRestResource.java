package com.yt.rest.resource;

import com.yt.business.bean.UserAccountBean;

public class BaseRestResource {

	public BaseRestResource() {
		// TODO Auto-generated constructor stub
	}
	
	protected UserAccountBean getCurrentUserAccount(String userId) throws Exception{
		UserAccountBean account = new UserAccountBean();
		
		return account;
	}

}
