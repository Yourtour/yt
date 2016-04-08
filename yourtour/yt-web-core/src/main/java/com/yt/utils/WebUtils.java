package com.yt.utils;

import com.yt.core.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	/**
	 * 获取访问请求
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 *
	 * @return
	 */
	public static String getContextPath(){
		return getHttpServletRequest().getContextPath();
	}

	/**
	 * 获取客户端上传的请求数据最后修改时间
	 * @return
	 */
	public static long getLastModifiedTime(){
		HttpServletRequest request = getHttpServletRequest();

		String lastModfieidTime = request.getHeader("LastModifiedTime");
		return StringUtils.isNull(lastModfieidTime)?0l:Long.valueOf(lastModfieidTime);
	}
}
