package com.yt.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final Log logger = LogFactory.getLog(DateUtils.class);

	public static final String DATE_FORMAT_DEFAULT="yyyy-MM-dd";

	//private static SimpleDateFormat formatter = new java.text.SimpleDateFormat(DATE_FORMAT_DEFAULT);

	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}

	public static String formatDate(long millisecond){
		return formatDate(millisecond, DATE_FORMAT_DEFAULT);
	}

	public static int getDateNumber(long millisecond){
		if(millisecond == 0) return 0;

		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return Integer.valueOf(sdf.format(date));
	}

	public static Date parseDate(String value) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(value);
	}

	public static String formatDate(long millisecond, String format){
		if(millisecond == 0) return "";
			
		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static int getDaySub(long beginDate,long endDate){
		int day=0;
		try {
			day=(int)((endDate-beginDate)/(24*60*60*1000));
		} catch (Exception e) {
			logger.error("System Exception", e);
		}

		return day;
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

		calendar.add(field, value);

		return calendar.getTime();
	}

	public static Calendar getCalendar(long milliseconds){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);

		return calendar;
	}

	public static void main(String[] args) throws Exception{
		System.out.println(System.currentTimeMillis());
	}
}
