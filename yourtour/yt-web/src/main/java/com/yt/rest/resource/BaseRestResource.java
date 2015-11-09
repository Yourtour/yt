package com.yt.rest.resource;

import javax.servlet.http.HttpServletRequest;

public class BaseRestResource {
	public BaseRestResource() {
	}
	
	protected String getCurrentUserId(HttpServletRequest request) throws Exception{
		return request.getHeader("User-Token");
	}
}
