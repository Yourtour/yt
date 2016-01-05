package com.yt.rest.resource;

import com.yt.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseRestResource {
	public BaseRestResource() {
	}
	
	protected String getCurrentUserId(HttpServletRequest request) throws Exception{
		return request.getHeader("User-Token");
	}

	protected String getCurrentUserId() throws Exception{
		return WebUtils.getCurrentLoginUser();
	}
}
