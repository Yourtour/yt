package com.yt.core.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtils {
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static boolean isEmpty(Collection values){
		return values == null || values.isEmpty();
	}
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static boolean isNotEmpty(Collection values){
		return ! isEmpty(values);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static boolean isEmpty(Map values){
		return values == null || values.isEmpty();
	}
	
	public static boolean isNotEmpty(Map values){
		return ! isEmpty(values);
	}
}
