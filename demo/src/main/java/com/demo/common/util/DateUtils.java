package com.demo.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.exception.DateParseException;

public abstract class DateUtils {
	
	private static Logger log = LoggerFactory.getLogger(DateUtils.class);
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * 一天内所有的小时
	 */
	public static final String[] ALL_TIME = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
	
	/**
	 * 获取一天所有的小时
	 * @return
	 */
	public static List<String> getAllTime() {
		return Arrays.asList(ALL_TIME);
	}
	
	/**
	 * 获取一天当中所有的小时
	 * @return
	 */
	public static List<Integer> getAllHour(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1; i<=24; i++){
			list.add(i);
		}
		
		return list;
	}
	
	/**
	 * 获取两个日期之间所有的日期
	 * 
	 * @param startStr
	 * @param endStr
	 * @return
	 */
	public static List<String> getAllDate(String startStr, String endStr) {

		List<String> result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			result = new ArrayList<String>();
			Date startDate = sdf.parse(startStr);
			Date endDate = sdf.parse(endStr);

			result.add(sdf.format(startDate));
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			Calendar temp = (Calendar) start.clone();
			temp.add(Calendar.DAY_OF_YEAR, 1);
			while (temp.before(end)) {
				result.add(sdf.format(temp.getTime()));
				temp.add(Calendar.DAY_OF_YEAR, 1);
			}
			result.add(sdf.format(endDate));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 转为yyyy-MM-dd格式的字符串 如果Number小于 946656000000 (2000年)就直接返回数字
	 * 
	 * @param value
	 *            Date|Number 类型
	 * @return
	 */
	public static String toDateStr(Object value) {
		if (value == null) {
			return "";
		}
		if (value instanceof Date) {
			return new SimpleDateFormat("yyyy-MM-dd").format((Date) value);
		}
		if (value instanceof Number) {
			long longValue = ((Number) value).longValue();
			if (longValue < 946656000000L) {
				return Long.toString(longValue + 1);
			}
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date(longValue));
		}
		if (value instanceof String) {
			return value.toString();
		} else {
			throw new RuntimeException("can not cast to date, value : " + value);
		}
	}
	
	/**
	 * 获取截止到某日期的指定数量的日期集合(格式为yyyy-MM-dd)
	 * */
	public static List<String> getSpecifyNumDaysToDate(String date, int num) {
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		if(date == null) {
			return null;
		}
		try {
			c.setTime(sdf.parse(date));
			for(int i = num-1; i >= 0; i--) {
				Calendar temp = (Calendar) c.clone();
				temp.add(Calendar.DATE,-i); 
				result.add(sdf.format(temp.getTime()));
			}
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 获取近一个月的日期列表
	 * */
	public static List<String> getNearlyMonthDateList(String date) {
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		if(date == null) {
			return null;
		}
		try {
			c.setTime(sdf.parse(date));
			c.add(Calendar.MONTH,-1);
			result = getAllDate(date, sdf.format(c.getTime()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Date parse(String str, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new DateParseException(str + " 转换成 " + format + " 格式的日期错误");
		}
	}
	
	//判断结束日期是否大于开始日期
	public static boolean isEndDateMoreStartDate(String startDate, String endDate) {
		long start = new Date(Long.parseLong(startDate)).getTime();
		long end = new Date(Long.parseLong(endDate)).getTime();
		if(end > start) {
			return true;
		}
		return false;
	}

}
