package com.tuitanews.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {
	public static String dateFormatStr = "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);

	public static String dateTimeFormatStr = dateFormatStr + " HH:mm:ss";
	public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateTimeFormatStr);

	public static String dateTimeNumFormatStr = "yyyyMMddHHmmss";
	private static SimpleDateFormat dateTimeNumFormat = new SimpleDateFormat(dateTimeNumFormatStr);

	public Object convert(Class arg0, Object arg1) {
		if (arg1 == null) {
			return null;
		}
		String className = arg1.getClass().getName();
		// java.sql.Timestamp
		if (arg1 instanceof Date) {
			return (arg1);
		}
		if ("java.sql.Timestamp".equalsIgnoreCase(className)) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
				return df.parse(dateTimeFormat.format(arg1));
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
					return df.parse(dateFormat.format(arg1));
				} catch (ParseException ex) {
					e.printStackTrace();
					return null;
				}
			}
		} else {// java.util.Date,java.sql.Date
			String p = (String) arg1;
			if (p == null || p.trim().length() == 0) {
				return null;
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
				return df.parse(p.trim());
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
					return df.parse(p.trim());
				} catch (ParseException ex) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	public static String formatDate(Object obj) {
		if (obj != null)
			return dateFormat.format(obj);
		else
			return "";
	}

	public static String formatDateTime(Object obj) {
		if (obj != null)
			return dateTimeFormat.format(obj);
		else
			return "";
	}

	public static String formatNumDateTime(Object obj) {
		if (obj != null)
			return dateTimeNumFormat.format(obj);
		else
			return "";
	}

	public static Date parseDate(String dateStr) {
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date parseDateTime(String dateStr) {
		try {
			return dateTimeFormat.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 获取从今天往前n天的日期，如：2015-05-20 00:00:00
	 * 
	 * @return
	 */
	public static Date getRecentBeforeDays(Integer n) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // clear hour
		cal.set(Calendar.MINUTE, 0);// clear minute
		cal.set(Calendar.SECOND, 0);// clear second
		cal.add(Calendar.DATE, -n);// 减去天数
		return cal.getTime();
	}
	
	/**
	 * 判断是否是合法的时间格式
	 * @param str
	 * @return
	 */
	public static boolean isValidDateTime(String str) {
        boolean convertSuccess=true;
         try {
            dateTimeFormat.setLenient(false);
            dateTimeFormat.parse(str);
         } catch (ParseException e) {
             convertSuccess=false;
         } 
         return convertSuccess;
  }
}
