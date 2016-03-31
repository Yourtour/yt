package com.yt.core.utils;

public class StringUtils {
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		return value == null || value.trim().length() == 0;
	}

	/**
	 * 判断是否空串
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value == null)
			return false;

		return value.trim().equalsIgnoreCase("");
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String value) {
		return !isNull(value);
	}

	/**
	 * 根据指定的长度去尾，如该小于指定长度，则返回源字符串，否则去尾后添加”...“标志。
	 * 
	 * @param src
	 *            字符串
	 * @param length
	 *            指定的长度
	 * @return 去尾后的字符串
	 */
	public static String truncate(String src, int length) {
		if (src == null) {
			return src;
		}
		if (length <= 0) {
			length  = src.length();
		}
		if (src.length() <= length) {
			return src;
		}
		return src.substring(0, length) + "...";
	}
}
