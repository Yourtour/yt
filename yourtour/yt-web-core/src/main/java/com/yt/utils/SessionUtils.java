package com.yt.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.core.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtils {
	public static final String USER_TOKEN = "User-Token";

	/**
	 * 获取用户访问令牌。
	 * @return
	 */
	public static String getCurrentLoginUser() {
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		String value = request.getHeader(USER_TOKEN);

		if(StringUtils.isNull(value)){
			HttpSession session = request.getSession(true);
			value = (String) session.getAttribute(USER_TOKEN);
		}

		return value;
	}

	/**
	 * 缓存用户访问令牌
	 * @param username
	 */
	public static void setCurrentLoginUser(String username){
		setAttribute(USER_TOKEN, username);
	}

	/**
	 * 设置缓存数据
	 * @param key
	 * @param value
	 */
	public static void setAttribute(String key, Object value) {
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(key, value);
	}

	/**
	 * 删除缓存数据
	 * @param key
	 */
	public static void removeAttribute(String key){
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		HttpSession session = request.getSession(true);
		session.removeAttribute(key);
	}

	public static void clear(){
	}
}
