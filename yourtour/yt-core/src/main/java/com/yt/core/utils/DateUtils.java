package com.yt.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final Log logger = LogFactory.getLog(DateUtils.class);

	public static final String DATE_FORMAT_FULL="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DEFAULT="yyyy-MM-dd";

	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}

	/**
	 * 将字符串格式的日期数据转换成Long型
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Long getDateAsLong(String date) throws Exception{
		return getDateAsLong(date, "yyyyMMdd");
	}

	/**
	 * 将字符串格式的日期数据转换成Long型
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static long getDateAsLong(String date, String format) throws Exception{
		if(StringUtils.isNull(date)) return 0;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date).getTime();
	}

	public static Date parseDate(String value) throws Exception{
		if(StringUtils.isNull(value)) return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(value);
	}

	public static int formatDateAsInteger(long millisecond){
		return Integer.parseInt(formatDate(millisecond, "yyyyMMdd"));
	}

	public static String formatDate(Long millisecond){
		return formatDate(millisecond, DATE_FORMAT_DEFAULT);
	}

	public static String formatDate(Long millisecond, String format){
		if(millisecond == null) return "";
			
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
