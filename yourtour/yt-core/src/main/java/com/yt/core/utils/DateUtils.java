package com.yt.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String DATE_FORMAT_DEFAULT="yyyy-MM-dd";
	
	public static String formatDate(long millisecond){
		String s = formatDate(millisecond, DATE_FORMAT_DEFAULT);
		return s;
	}
	
	public static String formatDate(long millisecond, String format){
		if(millisecond == 0) return "";
			
		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 日期增加或减少
	 * @param milliseconds
	 * @param value
	 * @param field
	 * @return
	 */
	public static Date add(Long milliseconds, int value, int field){
		Date current = new Date(milliseconds);

		return add(current, value, field);
	}

	/**
	 * 日期增加或减少
	 * @param current
	 * @param value
	 * @param field
	 * @return
	 */
	public static Date add(Date current, int value, int field){
		if(current == null || value == 0) return current;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);

		calendar.add(value, field);

		return calendar.getTime();
	}
}
