package org.ebaysf.web.cors;

import javax.servlet.http.HttpServletRequest;


public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
	public HttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		if(name.equals(CORSFilter.REQUEST_HEADER_ORIGIN)){
			String origin = super.getHeader(name);
			if(origin == null || origin.equalsIgnoreCase("null")){
				return "http://localhost:8080";
			}else{
				return origin;
			}
		}
		
		return super.getHeader(name);
	}
}
