package com.yt.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.core.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {
	private static final Log LOG = LogFactory.getLog(WebUtils.class);
	public static final String LOGIN_USERNAME = "login.username";

	public static HttpServletRequest getHttpServletRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getCurrentLoginUser() {
		HttpServletRequest request = getHttpServletRequest();
		return getCurrentLoginUser(request);
	}

	public static String getCurrentLoginUser(HttpServletRequest request) {
		String value = request.getHeader("User-Token");
		
		if(StringUtils.isNull(value)){
			HttpSession session = request.getSession(true);
			value = (String) session.getAttribute(LOGIN_USERNAME);
		}
		
		return value;
	}

	public static void setCurrentLoginUser(String username){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		setCurrentLoginUser(request, username);
	}

	public static void setCurrentLoginUser(HttpServletRequest request,
			String username) {
		HttpSession session = request.getSession(true);
		if (username != null) {
			session.setAttribute(LOGIN_USERNAME, username);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Set session[%s] data success, '%s' = '%s'.",
						session.getId(), LOGIN_USERNAME, username));
			}
		} else {
			session.removeAttribute(LOGIN_USERNAME);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Remove session[%s] data success, key: %s.",
						session.getId(), LOGIN_USERNAME));
			}
		}
	}
}
