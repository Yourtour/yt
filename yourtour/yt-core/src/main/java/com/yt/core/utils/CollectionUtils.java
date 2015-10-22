package com.yt.core.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtils {
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static boolean isNullOrEmpty(Collection values){
		return values == null || values.isEmpty();
	}
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static boolean isNotNullOrEmpty(Collection values){
		return ! isNullOrEmpty(values);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static boolean isNullOrEmpty(Map values){
		return values == null || values.isEmpty();
	}
	
	public static boolean isNotNullOrEmpty(Map values){
		return ! isNullOrEmpty(values);
	}
}
