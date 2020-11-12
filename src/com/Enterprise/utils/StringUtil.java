package com.Enterprise.utils;

import java.util.Calendar;

public class StringUtil {
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null || "".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static String DateChangeToString(int num) {
		String str;
		if(num<9) {
			str="0"+num;
		}else {
			str=num+"";
		}
		return str;
	}
	
	public static String GetUserNun() {
		Calendar c = Calendar.getInstance();// 获取当前时间
		String year = c.get(Calendar.YEAR) + "";

		int month = c.get(Calendar.MONTH) + 1;
		String monthS =DateChangeToString(month);

		int date = c.get(Calendar.DATE);
		String dates = DateChangeToString(date);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		String hours = DateChangeToString(hour);
		int minute = c.get(Calendar.MINUTE);
		String minutes = DateChangeToString(minute);
		int second = c.get(Calendar.SECOND);
		String seconds = DateChangeToString(second);
		
		String str=year + monthS + dates + hours + minutes + seconds;
		return str;
	}
}
