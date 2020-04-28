package cn.baisee.oa.importExcel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import cn.baisee.oa.importExcel.dto.StartEndDateDto;


public class DateUtil {

	/** 日期格式yyyyMMdd */
	private static final String DATE_FINAL_FORMAT_PATTERN = "yyyyMMdd";

	public static String converFormat(String str) {
		if (StringUtils.isEmpty(str))
			return str;

		SimpleDateFormat finalFmt = new SimpleDateFormat(
				DATE_FINAL_FORMAT_PATTERN);
		SimpleDateFormat fmt = null;
		try {
			for (RegexAndDateFormat s : RegexAndDateFormat.values()) {
				Pattern p = Pattern.compile(s.getRegex());
				Matcher m = p.matcher(str);
				fmt = new SimpleDateFormat(s.getFormat());
				if (m.matches())
					return finalFmt.format(fmt.parse(str));
			}
		} catch (ParseException e) {

		}

		return str;
	}

	public static Date formatDate(String dateStr) {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FINAL_FORMAT_PATTERN);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}

	public static boolean date1LtDate2(String dateStr1, String dateStr2) {
		return compareDate(dateStr1, dateStr2) < 0;
	}

	public static boolean date1GtDate2(String dateStr1, String dateStr2) {
		return compareDate(dateStr1, dateStr2) > 0;
	}

	private static int compareDate(String dateStr1, String dateStr2) {
		Date date1 = formatDate(dateStr1);
		Date date2 = formatDate(dateStr2);
		return date1.compareTo(date2);
	}

	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FINAL_FORMAT_PATTERN);
		return sdf.format(new Date());
	}

	public static String getEndOfCurrentMonth() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FINAL_FORMAT_PATTERN);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		String endDate = df.format(calendar.getTime());
		return endDate;
	}

	public static boolean hasOverlapDate(List<StartEndDateDto> list) {
		if (CollectionUtils.isEmpty(list))
			return false;

		Collections.sort(list);

		boolean flag = false;
		for (int i = 0; i < list.size();) {
			StartEndDateDto dto = list.get(i);
			if (i + 1 >= list.size())
				break;
			StartEndDateDto dto2 = list.get(i + 1);
			if (!date1LtDate2(dto.getEndDate(), dto2.getStartDate())) {
				flag = true;
				break;
			}
			i++;
		}

		return flag;
	}

	public static List<StartEndDateDto> splitByMonth(String startDateStr,
			String endDateStr) {
		List<StartEndDateDto> list = null;
		List<String> splitedDates = splitDateByMonthToStringList(startDateStr,
				endDateStr);

		if (CollectionUtils.isEmpty(splitedDates))
			return null;

		list = new ArrayList<>();
		StartEndDateDto sedDto = null;
		for (int i = 0; i < splitedDates.size(); i = i + 2) {
			sedDto = new StartEndDateDto(splitedDates.get(i),
					splitedDates.get(i + 1));
			list.add(sedDto);
		}
		return list;
	}

	private static List<String> splitDateByMonthToStringList(
			String startDateStr, String endDateStr) {
		Date dBegin = formatDate(startDateStr);
		Date dEnd = formatDate(endDateStr);

		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dBegin);

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);

		if (calStart.after(calEnd))
			return null;

		List<String> listDate = new ArrayList<>();
		listDate.add(formatCalendar(calStart));

		if (isSameMonth(calStart, calEnd)) { // 如果是同一个月
			listDate.add(formatCalendar(calEnd));
			return listDate;
		}

		setToMonthEnd(calStart);
		listDate.add(formatCalendar(calStart));

		while (true) {
			calStart.add(Calendar.MONTH, 1);
			setToMonthStart(calStart);

			if (calStart.after(calEnd))
				break;

			listDate.add(formatCalendar(calStart));

			setToMonthEnd(calStart);

			if (calStart.equals(calEnd) || calStart.after(calEnd))
				break;

			listDate.add(formatCalendar(calStart));
		}

		if (calStart.equals(calEnd) || calStart.after(calEnd))
			listDate.add(formatCalendar(calEnd));

		return listDate;
	}

	private static boolean isSameMonth(Calendar c1, Calendar c2) {
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
	}

	private static void setToMonthStart(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, 1);
	}

	private static void setToMonthEnd(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	private static String formatCalendar(Calendar c) {
		return new SimpleDateFormat(DATE_FINAL_FORMAT_PATTERN).format(c
				.getTime());
	}

	public static boolean monthBefore(String dateStr1, String dateStr2) {
		Date date1 = formatDate(dateStr1);
		Date date2 = formatDate(dateStr2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		setToMonthStart(cal1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		setToMonthStart(cal2);
		return cal1.before(cal2);
	}

	public static boolean isCurrentMonth(String dateStr) {
		Calendar c = Calendar.getInstance();
		Date date = formatDate(dateStr);
		if (date == null)
			return false;
		c.setTime(date);
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(new Date());
		return isSameMonth(c, currentCal);
	}

}

enum RegexAndDateFormat {
	PATTERN_1("[0-9]{4}[-][0-9]{2}[-][0-9]{2}", "yyyy-MM-dd"), PATTERN_2(
			"[0-9]{4}[/][0-9]{2}[/][0-9]{2}", "yyyy/MM/dd"), PATTERN_3(
			"[0-9]{8}", "yyyyMMdd");

	private String regex;
	private String format;

	private RegexAndDateFormat(String regex, String format) {
		this.regex = regex;
		this.format = format;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
