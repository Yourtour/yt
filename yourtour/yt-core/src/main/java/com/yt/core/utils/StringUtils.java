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
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String value){
		return ! isNull(value);
	}
}
