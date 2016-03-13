package com.yt.rest.resource;

/**
 * 所有基于Jersey Restful 接口的基础类。每个具体接口定义时注意以下几点：
 * 1）每个接口方法必须抛出异常，然后有应用统一的异常处理AOP接口进行捕获并处理，
 * 2）在给接口中，涉及到数据修改保存的接口，第一步必须先从数据存储中获取已经存在的数据，然后根据每个接口提交的数据进行修改，防止数据破坏
 *
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yt.core.common.ThreadCurrentUser;
import com.yt.utils.SessionUtils;

public class RestResource {
	public RestResource() {
		super();
	}

	protected void fillUserInfo(HttpServletRequest request) {
		String userId = request.getHeader("userId");
		String userName = request.getHeader("userName");
		ThreadCurrentUser.setUserInfor(Long.valueOf(userId), userName);
	}

	protected void fillUserInfor() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		this.fillUserInfo(request);
	}

	protected String getCurrentUserId(HttpServletRequest request)
			throws Exception {
		return request.getHeader("User-Token");
	}

	protected Long getCurrentUserId() throws Exception {
		return SessionUtils.getCurrentLoginUser();
	}
}
