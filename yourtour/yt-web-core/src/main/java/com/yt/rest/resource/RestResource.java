package com.yt.rest.resource;

import com.yt.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;

public class RestResource {


	public RestResource() {
	}
	
	protected String getCurrentUserId(HttpServletRequest request) throws Exception{
		return request.getHeader("User-Token");
	}

	protected String getCurrentUserId() throws Exception{
		return SessionUtils.getCurrentLoginUser();
	}
}
