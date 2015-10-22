package com.yt.core.utils;

public class StringUtils {
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullOrBlank(String value){
		return value == null || value.trim().length() == 0;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNullOrBlank(String value){
		return isNullOrBlank(value);
	}
}
