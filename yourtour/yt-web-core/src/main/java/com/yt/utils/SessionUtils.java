package com.yt.utils;

import com.yt.business.bean.UserProfileBean;
import com.yt.core.utils.Neo4jUtils;
import com.yt.core.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static final String USER_TOKEN = "User-Token";

	/**
	 * 获取用户访问令牌
	 * 
	 * @param request
	 * @return
	 */
	public static String getCurrentLoginUser(HttpServletRequest request) {
		String value = request.getHeader(USER_TOKEN);

		if (StringUtils.isNull(value)) {
			HttpSession session = request.getSession(true);
			UserProfileBean user = (UserProfileBean) session.getAttribute(USER_TOKEN);
			if(user == null)
				value = "0";
			else
				value = user.getId().toString();
		}

		return value;
	}

	/**
	 * 获取用户访问令牌。
	 * 
	 * @return
	 */
	public static Long getCurrentLoginUser() {
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		return Neo4jUtils.getGraphIDFromString(getCurrentLoginUser(request));
	}

	/**
	 * 缓存用户信息
	 * @param user
	 */
	public static void setCurrentLoginUser(UserProfileBean user) {
		setAttribute(USER_TOKEN, user);
	}

	/**
	 * 设置缓存数据
	 * 
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
	 * 
	 * @param key
	 */
	public static void removeAttribute(String key) {
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		HttpSession session = request.getSession(true);
		session.removeAttribute(key);
	}

	public static void clear(){
	}
}
