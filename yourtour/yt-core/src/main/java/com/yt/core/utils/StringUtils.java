package com.yt.core.utils;

public class StringUtils {
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value){
		return value == null || value.trim().length() == 0;
	}

	/**
	 * 判断是否空串
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if(value == null) return false;

		return value.trim().equalsIgnoreCase("");
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String value){
		return ! isNull(value);
	}
}
