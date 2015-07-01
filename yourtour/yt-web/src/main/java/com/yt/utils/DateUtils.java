package com.yt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final String FULL_YMD = "yyyy-MM-dd";
	
	/**
	 * 转日期为字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String format(Date date, String formatter){
		if (date == null)
			return "";

		try {
			return new SimpleDateFormat(formatter).format(date);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 转日期为字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String format(Date date){
		if (date == null)
			return "";

		try {
			return new SimpleDateFormat(FULL_YMD).format(date);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}
}
