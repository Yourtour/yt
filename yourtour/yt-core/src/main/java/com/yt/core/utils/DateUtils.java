package com.yt.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final String DATE_FORMAT_DEFAULT="yyyy-MM-dd";
	
	public static String formatDate(long millisecond){
		return formatDate(millisecond, DATE_FORMAT_DEFAULT);
	}
	
	public static String formatDate(long millisecond, String format){
		if(millisecond == 0) return "";
			
		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
