package com.msghub.msghub.utils;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	/**
	 * 前端的类型
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	 * 应用程序的格式化符
	 */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMDI = "yyyy/MM/dd";
	/**
	 * 短日期格式HH:mm:ss
	 */
	public static final String DATE_FORMAT_HMS = "HH:mm:ss";
	/**
	 * 短日期格式不带分隔符
	 */
	public static final String DATE_FORMAT_YMD_II = "yyyyMMdd";

	public static final String DATE_FORMAT_DAY_YEAR_I = "yyyy-MM";

	public static final String DATE_FORMAT_DAY_YEAR_II = "yyyyMM";
	/**
	 * 时间格式不带分隔符
	 */
	public static final String DATE_FORMAT_FULL_II = "yyyyMMddHHmmss";

	private static final Log logger = LogFactory.getLog(DateUtil.class);
	
	public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}
	public static String datesplit(String a){
		return a.replace("T"," ");
	}

	/**
	 * 把源日历的年月日设置到目标日历对象中
	 * 
	 * @param destCal
	 * @param sourceCal
	 */
	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DAY_OF_MONTH, sourceCal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 格式化日期为
	 * 
	 * @return
	 */
	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	/**
	 * 获取当月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getEndday(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date parseDate = parseDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate);
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		return day_last;
	}

	public static Date parseDate(String dateString) {
		if (StringUtil.isEmpty(dateString)) {
			return null;
		}
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString,
					new String[] { DATE_FORMAT_YMDI, DATE_FORMAT_FULL, DATE_FORMAT_FULL_II, DATE_FORMAT_YMD,
							DATE_FORMAT_HMS, DATE_FORMAT_YMD_II, DATE_FORMAT_DAY_YEAR_II });
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:" + ex.getMessage());
		}
		return date;
	}

	/**
	 * 日期加一天
	 * 
	 * @param date
	 * @return
	 */
	public static String addOneDay(String date) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date parseDate = parseDate(date);
		calendar.setTime(parseDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String tmpDate = format.format(calendar.getTime());
		return tmpDate.substring(5, 7) + "/" + tmpDate.substring(8, 10) + "/" + tmpDate.substring(0, 4);
	}

	/**
	 * 日期加一天通用方法
	 * 
	 * @param date
	 * @return
	 */
	public static String addOneDayNormal(String date) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date parseDate = parseDate(date);
		calendar.setTime(parseDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String tmpDate = format.format(calendar.getTime());
		return tmpDate;
	}

	/**
	 * 加一小时
	 * 
	 * @param date
	 * @return
	 */
	public static String addOneHour(String date) {

		String amPm = date.substring(20, 22);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();

		int hour = Integer.parseInt(date.substring(11, 13));
		try {
			if ("PM".equals(amPm)) {
				hour += 12;
			}
			date = date.substring(0, 11) + (hour >= 10 ? hour : "0" + hour) + date.substring(13, 19);
			Date dd = format.parse(date);
			calendar.setTime(dd);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tmpDate = format.format(calendar.getTime());

		hour = Integer.parseInt(tmpDate.substring(11, 13));
		amPm = hour >= 12 && hour != 0 ? "PM" : "AM";
		if ("PM".equals(amPm)) {
			hour -= 12;
		}
		tmpDate = tmpDate.substring(5, 7) + "/" + tmpDate.substring(8, 10) + "/" + tmpDate.substring(0, 4) + " "
				+ (hour >= 10 ? hour : "0" + hour) + tmpDate.substring(13, tmpDate.length()) + " " + amPm;

		return tmpDate;
	}

	/**
	 * 标准时间格式转为时间字符格式
	 * 
	 * @param timeStr
	 *            eg:Mon Feb 06 00:00:00 CST 2012
	 * @return
	 */
	public static String timeStrToDateStr(String timeStr) {

		String dateStr = timeStr.substring(24, 28) + "-";

		String mon = timeStr.substring(4, 7);
		if ("Jan".equals(mon)) {
			dateStr += "01";
		} else if ("Feb".equals(mon)) {
			dateStr += "02";
		} else if ("Mar".equals(mon)) {
			dateStr += "03";
		} else if ("Apr".equals(mon)) {
			dateStr += "04";
		} else if ("May".equals(mon)) {
			dateStr += "05";
		} else if ("Jun".equals(mon)) {
			dateStr += "06";
		} else if ("Jul".equals(mon)) {
			dateStr += "07";
		} else if ("Aug".equals(mon)) {
			dateStr += "08";
		} else if ("Sep".equals(mon)) {
			dateStr += "09";
		} else if ("Oct".equals(mon)) {
			dateStr += "10";
		} else if ("Nov".equals(mon)) {
			dateStr += "11";
		} else if ("Dec".equals(mon)) {
			dateStr += "12";
		}

		dateStr += "-" + timeStr.substring(8, 10);

		return dateStr;
	}

	/**
	 * 根据日期得到星期多余天数
	 * 
	 * @param sDate
	 * @return
	 */
	public static int getExtraDayOfWeek(String sDate) {
		try {

			String formater = "yyyy-MM-dd";
			SimpleDateFormat format = new SimpleDateFormat(formater);
			Date date = format.parse(sDate);
			String weekday = date.toString().substring(0, 3);
			if ("Mon".equals(weekday)) {
				return 1;
			} else if ("Tue".equals(weekday)) {
				return 2;
			} else if ("Wed".equals(weekday)) {
				return 3;
			} else if ("Thu".equals(weekday)) {
				return 4;
			} else if ("Fri".equals(weekday)) {
				return 5;
			} else if ("Sat".equals(weekday)) {
				return 6;
			} else {
				return 0;
			}

		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 判断时间格式 格式必须为“YYYY-MM-dd” 2004-2-30 是无效的 2003-2-29 是无效的
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String str, String parttem) {
		// String str = "2007-01-02";
		DateFormat formatter = new SimpleDateFormat(parttem);
		try {
			Date date = (Date) formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据日期得到星期多余天数
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getDateWeekDay(String sDate) {
		try {

			String formater = "yyyy-MM-dd";
			SimpleDateFormat format = new SimpleDateFormat(formater);
			Date date = format.parse(sDate);
			String weekday = date.toString().substring(0, 3);
			// format.format(date)+" "+
			return weekday;

		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 取得上下五年
	 * 
	 * @param cal
	 * @return
	 */
	public static List<String> getUpDownFiveYear(Calendar cal) {
		List<String> yearlist = new ArrayList<String>();

		int curyear = cal.get(Calendar.YEAR);
		yearlist.add(String.valueOf(curyear - 2));
		yearlist.add(String.valueOf(curyear - 1));
		yearlist.add(String.valueOf(curyear));
		yearlist.add(String.valueOf(curyear + 1));
		yearlist.add(String.valueOf(curyear + 2));

		return yearlist;
	}

	/**
	 * 取得12个月
	 * 
	 * @param cal
	 * @return
	 */
	public static List<String> getTwelveMonth() {
		List<String> monthlist = new ArrayList<String>();

		for (int idx = 1; idx <= 12; idx++) {
			monthlist.add(String.valueOf(idx));
		}

		return monthlist;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String startTime, String endTime) throws ParseException {
		Date smdate = DateUtil.parseDate(startTime);
		Date bdate = DateUtil.parseDate(endTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 得到两日期间所有日期
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static String[] getDaysBetweenDate(String startTime, String endTime) {

		String[] dateArr = null;
		try {

			String stime = timeStrToDateStr(startTime);
			String etime = timeStrToDateStr(endTime);

			// 日期相减算出秒的算法
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(stime);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(etime);

			long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0
					? (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
					: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);

			dateArr = new String[Integer.valueOf(String.valueOf(day + 1))];
			for (int idx = 0; idx < dateArr.length; idx++) {
				if (idx == 0) {
					dateArr[idx] = stime;
				} else {
					stime = addOneDay(stime);
					stime = stime.substring(6, 10) + "-" + stime.substring(0, 2) + "-" + stime.substring(3, 5);
					dateArr[idx] = stime;
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateArr;
	}

	public static String getNow(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	public static String formatDate(String pattern, Date date) {
		if (date == null) {
            return null;
        }
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * @param dateStr
	 *            时间字符串
	 * @param fm1
	 *            原时间格式
	 * @param fm2
	 *            新时间格式
	 * @return 新格式时间字符串
	 * @throws Exception
	 */
	public static String formatDateStrToStr(String dateStr, String fm1, String fm2) throws Exception {
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat(fm1);
			SimpleDateFormat sdf2 = new SimpleDateFormat(fm2);
			return sdf2.format(sdf1.parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * 获取当月的最后一天
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthLastDate(String startDate) {
		String lastMonthDate = "";
		Calendar cDay1 = Calendar.getInstance();
		Date date = DateUtil.parseDate(startDate);
		cDay1.setTime(date);
		final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		lastMonthDate = DateUtil.formatDate(DateUtil.DATE_FORMAT_YMD, lastDate);
		return lastMonthDate;
	}

	/**
	 * 获取日前距今天相差天数
	 * 
	 * @param startDate
	 * @return
	 */
	public static int getDifferFromNowDay(String useDate) {
		Date date = parseDate(useDate);
		int differTime = 0;
		Date now = new Date();
		long intervalMilli = now.getTime() - date.getTime();
		differTime = (int) (intervalMilli / (24 * 60 * 60 * 1000));
		return differTime;
	}

	/**
	 * 获取日前距月底相差天数
	 * 
	 * @param startDate
	 * @return
	 */
	public static int getDifferEndDay(String useDate) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		Date date = parseDate(useDate);
		Date now = parseDate(last);
		int differTime = 0;
		long intervalMilli = now.getTime() - date.getTime();
		differTime = (int) (intervalMilli / (24 * 60 * 60 * 1000));
		return differTime;
	}

	/**
	 * 获取日前距今天相差天数
	 * 
	 * @param startDate
	 * @return
	 */
	public static int getDifferNowDay(String useDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
		int differTime = 0;
		try {
			Date date = sdf.parse(useDate);
			Date now = new Date();
			long intervalMilli = now.getTime() - date.getTime();
			differTime = (int) (intervalMilli / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return differTime;
	}

	/**
	 * 获取月份差值 cai
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static int getMonth(String startString, String endString) throws ParseException {
		Date start = parseDate(startString);
		Date end = parseDate(endString);
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 获取日期相月数
	 * 
	 * @param add
	 *            by cai 此方法只是单纯相减，所得值加1
	 * @return 日期类型时间
	 * @throws ParseException
	 */
	public static int getDiffMonth(String beginDate, String endDate) {
		Date dbeginDate = parseDate(beginDate);
		Date dendDate = parseDate(endDate);
		return getDiffMonth(dbeginDate, dendDate);
	}

	public static int getDiffMonth(Date beginDate, Date endDate) {
		Calendar calbegin = Calendar.getInstance();
		Calendar calend = Calendar.getInstance();
		calbegin.setTime(beginDate);
		calend.setTime(endDate);
		int m_begin = calbegin.get(Calendar.MONTH) + 1; // 获得合同开始日期月份
		int m_end = calend.get(Calendar.MONTH) + 1;
		// 获得合同结束日期月份
		int checkmonth = m_end - m_begin + (calend.get(Calendar.YEAR) - calbegin.get(Calendar.YEAR)) * 12;
		// 获得合同结束日期于开始的相差月份
		return checkmonth;
	}

	/**
	 * 将月份加月数 cai
	 * 
	 * @param date
	 * @param months
	 * @return
	 * @throws ParseException
	 */
	public final static String addMonthsToDate(String dateString, int months) throws ParseException {
		Date date = parseDate(dateString);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);// 加3个月
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置月份的月初
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 设置时间格式
		String defaultStartDate = sdf.format(c.getTime()); // 格式化前3月的时间
		return defaultStartDate;
	}

	/**
	 * 将月份加月数 返回指定模板
	 * 
	 * @param dateString
	 * @param months
	 * @param parttem
	 * @return
	 * @throws ParseException
	 */
	public final static String addMonthsToDate(String dateString, int months, String parttem) throws ParseException {
		if (StringUtil.isEmpty(parttem)) {
			parttem = DATE_FORMAT_YMD_II;
		}
		Date date = parseDate(dateString);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);// 加3个月
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置月份的月初
		SimpleDateFormat sdf = new SimpleDateFormat(parttem); // 设置时间格式
		String defaultStartDate = sdf.format(c.getTime()); // 格式化前3月的时间
		return defaultStartDate;
	}

	/**
	 * 获取月份差值
	 * 
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws Exception
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}

	public static String getDateWeek() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
         
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, + 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
		return day;
	}

	/**当前时间加一年*/
	public static String timeAddYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);//设置起时间
		//System.out.println("111111111::::"+cal.getTime());
		cal.add(Calendar.YEAR, 1);//增加一年
		//cd.add(Calendar.DATE, n);//增加一天  
		//cd.add(Calendar.DATE, -10);//减10天  
		//cd.add(Calendar.MONTH, n);//增加一个月   
		Date date2 = cal.getTime();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(date2);
		  return dateString;
	}
	
	/**获取当前时间 (年月日)*/
	public static String getReqDate() {
		return SHORT_DATE_FORMAT.format(new Date());
		}
	
	/**比较时间
	 * @throws ParseException **/ //,String date2,String format
	public static boolean equalDate(String date1,String date2,String format) throws ParseException {
			Date a = DateUtils.parseDate(date1,new String[] {format});
			Date b = DateUtils.parseDate(date2,new String[] {format});
			System.out.println(a );
			System.out.println(b );
			Calendar a1 = Calendar.getInstance();
			a1.setTime(a);
			Calendar a2 = Calendar.getInstance();
			a2.setTime(b);
			if(a1.after(a2)) {
				return true;
			}else {
				return false;
			}
	}
	
	public static void main(String[] args) throws Exception {

		System.out.println(
				equalDate("2019-09-03 09:23:08","2019-09-03 09:23:09",DATE_FORMAT_FULL)
		);
		/*System.out.println(getDifferNowDay("2018-02-02"));
		System.out.println(getDifferFromNowDay("20180202"));*/
		
	}

	public static boolean isCellDateFormatted(Cell cell) {
		// TODO Auto-generated method stub
		return false;
	}
}
