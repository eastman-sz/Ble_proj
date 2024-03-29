package com.utils.lib.ss.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
/**
 * 日期功能类。
 * @author E
 */
@SuppressLint("SimpleDateFormat")
public class DateHepler {

	public static final String Y_M_D = "yyyy-MM-dd";
	
	/**
	 * 一年当中的第几周
	 * @return 一年当中的第几周
	 */
	public static int getWeekOfYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 一年当中的第几周
	 * @param timestamp 13位的时间戳
	 * @return 一年当中的第几周
	 */
	public static int getWeekOfYear(long timestamp){
		Calendar calendar = Calendar.getInstance();
		timestamp = adjustTimestamp(timestamp); 
		calendar.setTimeInMillis(timestamp);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 哪一年
	 * @return 某一年
	 */
	public static int getYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 哪一年
	 * @param timestamp 13位的时间戳
	 * @return 哪一年
	 */
	public static int getYear(long timestamp){
		Calendar calendar = Calendar.getInstance();
		timestamp = adjustTimestamp(timestamp); 
		calendar.setTimeInMillis(timestamp);
		return calendar.get(Calendar.YEAR);
	}	
	
	/**
	 * 哪一年
	 * @param calendar Calendar
	 * @return 哪一年
	 */
	public static int getYear(Calendar calendar){
		return calendar.get(Calendar.YEAR);
	}	
	
	/**
	 * 一年当中的第几月
	 * @return 一年当中的第几月
	 */
	public static int getMonthOfYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 一年当中的第几月
	 * @param timestamp 13位的时间戳
	 * @return 一年当中的第几月
	 */
	public static int getMonthOfYear(long timestamp){
		Calendar calendar = Calendar.getInstance();
		timestamp = adjustTimestamp(timestamp); 
		calendar.setTimeInMillis(timestamp);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 一年当中的第几月
	 * @param calendar Calendar
	 * @return 一年当中的第几月
	 */
	public static int getMonthOfYear(Calendar calendar){
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 一年当中的第几日
	 * @param calendar Calendar
	 * @return 一年当中的第几日
	 */
	public static int getDayOfYear(Calendar calendar){
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 一年当中的第几日
	 * @param timestamp 13位的时间戳
	 * @return 一年当中的第几日
	 */
	public static int getDayOfYear(long timestamp){
		Calendar calendar = Calendar.getInstance();
		timestamp = adjustTimestamp(timestamp); 
		calendar.setTimeInMillis(timestamp);
		return getDayOfYear(calendar);
	}	
	
	/**
	 * 一月当中的第几日
	 * @param calendar Calendar
	 * @return 一月当中的第几日
	 */
	public static int getDayOfMonth(Calendar calendar){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

    /**
     * 一月当中的第几日
     * @param timestamp 时间戳
     * @return 一月当中的第几日
     */
	public static int getDayOfMonth(long timestamp){
        Calendar calendar = Calendar.getInstance();
        timestamp = adjustTimestamp(timestamp);
        calendar.setTimeInMillis(timestamp);
        return getDayOfMonth(calendar);
    }
	
	/**
	 * 一天当中的第几个小时
	 * @param calendar Calendar
	 * @return 一天当中的第几个小时
	 */
	public static int getHourOfDay(Calendar calendar){
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 一小时当中的第几分钟。
	 * @param calendar Calendar
	 * @return 一小时当中的第几分钟
	 */
	public static int getMinuteOfHour(Calendar calendar){
		return calendar.get(Calendar.MINUTE);
	}


    /**
     * 计算某年某月某日的一天的开始时间戳
     * @param year
     * @param month
     * @param dayOfYear
     * @return 一天的开始时间戳
     */
	public static long getDayStartTimestamp(int year , int month , int dayOfYear){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH , month);
        cal.set(Calendar.DAY_OF_YEAR , dayOfYear);
        return cal.getTime().getTime();
    }

    /**
     * 一年之中的某一周的开始日期
     * @param weekOfYear 一年之中的某一周
     * @return
     */
	public static Date getWeekStartDate(int weekOfYear){
        return getWeekStartDate(getYear() , weekOfYear);
	}
	
    /**
     * 一年之中的某一周的开始日期
     * @param weekOfYear 一年之中的某一周
     * @return
     */
	public static Date getWeekStartDate(int year , int weekOfYear){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
	}
	
	/**
	 * 一年之中的某一周的结束日期
	 * @param weekOfYear 一年之中的某一周
	 * @return
	 */
	public static Date getWeekEndDate(int weekOfYear){
        return getWeekEndDate(getYear() , weekOfYear);
	}
	
	/**
	 * 一年之中的某一周的结束日期
	 * @param weekOfYear 一年之中的某一周
	 * @return
	 */
	public static Date getWeekEndDate(int year , int weekOfYear){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR,weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return cal.getTime();
	}	
	
	/**
	 * 一周当中的第几天。
	 * @param timestamp 时间戳
	 * @return 一周当中的第几天
	 */
	public static int getDayOfWeek(long timestamp){
		Calendar cal = Calendar.getInstance();
		timestamp = adjustTimestamp(timestamp); 
		cal.setTime(new Date(timestamp));
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 返回今天是一周中的第几天。周日为1 ，依次例推。
	 * @return 今天是一周中的第几天
	 */
	public static int getDayOfWeek(){
		return getDayOfWeek(System.currentTimeMillis());
	}

	public static String getDayOfWeekString(long timestamp){
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int dayOfweek = getDayOfWeek(timestamp) - 1;
        if (dayOfweek < 0){
            dayOfweek = 0;
        }
        return weekDays[dayOfweek];
    }

    public static Date getSomeDayDate(int year , int dayOfYear){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR,dayOfYear);
        return cal.getTime();
    };

    /**
     * 某一天的时间格式。
     * @param year
     * @param dayOfYear
     * @param pattern
     * @return 某一天的时间格式。
     */
    public static String getSomeDayDateFomat(int year , int dayOfYear , String pattern){
        Date date = getSomeDayDate(year , dayOfYear);
        return dateFormat(date , pattern);
    }

	/**
	 * 将时间戳转换为pattern样式的文字输出。
	 * @param timestamp 时间戳
	 * @param pattern 样式 eg:yyyy:mm:dd
	 * @return pattern样式的文字
	 */
	public static String timestampFormat(long timestamp , String pattern){
		timestamp = adjustTimestamp(timestamp);
		Date date = new Date(timestamp);
		return dateFormat(date, pattern);
	}
	
	/**
	 * 将Date转换为pattern样式的文字输出。
	 * @param date Date
	 * @param pattern 样式 eg:yyyy:mm:dd
	 * @return pattern样式的文字
	 */
	public static String dateFormat(Date date , String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 校正时间戳。
	 * @param timestamp 时间戳
	 * @return 校正后时间戳
	 */
	public static long adjustTimestamp(long timestamp){
		int length = String.valueOf(timestamp).length();
		return 10 == length ? timestamp*1000 : timestamp;
	}

    /**
     * 某一天00:00:01分的时间戳。
     * @param dayTimestamp 某一天内的任意时间戳都可以。
     * @return 某一天00:00:01分的时间戳
     */
	public static long getDayStartTimestamp(long dayTimestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR , getDayOfYear(dayTimestamp));

		calendar.set(Calendar.YEAR , getYear(dayTimestamp));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int) (calendar.getTimeInMillis()/1000);
    }

    /**
     * 某一天00:00:01分的时间戳。
     * @param dayTimestamp 某一天内的任意时间戳都可以。
     * @param days 前面第几天
     * @return 某一天00:00:01分的时间戳
     */
    public static long getDayStartTimestampBefore(long dayTimestamp , int days){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR , getDayOfYear(dayTimestamp) - days);

        calendar.set(Calendar.YEAR , getYear(dayTimestamp));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int) (calendar.getTimeInMillis()/1000);
    }

    /**
     * 某天前的某一天23:59:59分的时间戳。
     * @param dayTimestamp 某一天内的任意时间戳都可以。
     * @param days 几天
     * @return 某一天23:59:59分的时间戳
     */
	public static long getDayEndTimestampBefore(long dayTimestamp , int days){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR , getDayOfYear(dayTimestamp) - days);

		calendar.set(Calendar.YEAR , getYear(dayTimestamp));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return (int) (calendar.getTimeInMillis()/1000);
	}

    /**
     * 某一天23:59:59分的时间戳。
     * @param dayTimestamp 某一天内的任意时间戳都可以。
     * @return 某一天23:59:59分的时间戳
     */
    public static long getDayEndTimestamp(long dayTimestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR , getDayOfYear(dayTimestamp));

        calendar.set(Calendar.YEAR , getYear(dayTimestamp));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int) (calendar.getTimeInMillis()/1000);
    }

	
	private final static String[] DAY_OF_WEEK = new String[]{"周日" ,"周一","周二","周三","周四","周五","周六"};
	
	/**
	 * 周几对应的文字显示。
	 * @param dayOfWeek 周几
	 * @return 周几对应的文字显示
	 */
	public static String getDayOfWeekTxt(int dayOfWeek){
		if (dayOfWeek < 0) {
			return DAY_OF_WEEK[0];
		}
		return DAY_OF_WEEK[dayOfWeek%7];
	}

    /**
     * 某年某月的第一天
     * @param year 某年
     * @param month 某月
     * @param pattern 日期格式yyyy-MM-dd
     * @return 某年某月的第一天
     */
	public static String getFirstDayOfMonth(int year , int month , String pattern){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    /**
     * 某年某月的第一天
     * @param year 某年
     * @param month 某月
     * @return 某年某月的第一天
     */
    public static String getFirstDayOfMonth(int year , int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 某年某月的第一天，是一年中的第几天.
     * @param year
     * @param month
     * @return 是一年中的第几天
     */
    public static int getFirstdayOfMonth(int year , int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 某年某月的最后一天，是一年中的第几天.
     * @param year
     * @param month
     * @return 是一年中的第几天
     */
    public static int getLastdayOfMonth(int year , int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 得到某年某月的最后一天
     * @param year 某年
     * @param month 某月
     * @param pattern 日期格式yyyy-MM-dd
     * @return 某年某月的最后一天
     */
    public static String getLastDayOfMonth(int year, int month , String pattern){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    /**
     * 得到某年某月的最后一天
     * @param year 某年
     * @param month 某月
     * @return 某年某月的最后一天
     */
    public static String getLastDayOfMonth(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

	/**
	 * 通过年龄反推生日。
	 * @param age 年龄
	 * @return 生日
	 */
	public static String age2Birthday(int age){
		int currentYear =  getYear();
		int birthYear = currentYear - age;
		String birthday = birthYear + "-01-01";
		return birthday;
	}
	
	/**
	 * 将日期格式的字符串转化为日期。
	 * @param dateFormatString 日期格式的字符串
	 * @return Date
	 */
	public static Date dateFormatString2Date(String dateFormatString){
		return dateFormatString2Date(dateFormatString, "yyyy-MM-dd");
	}
	
	/**
	 * 将日期格式的字符串转化为日期。
	 * @param dateFormatString 日期格式的字符串
	 * @return Date
	 */
	public static Date dateFormatString2Date(String dateFormatString , String pattern){
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(dateFormatString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户生日计算用户年龄。
	 * @param birthday 用户生日
	 * @return 用户年龄
	 */
	public static int getAgeByBirthday(String birthday){
		Date date = dateFormatString2Date(birthday);
		return null == date ? 0 : getAgeByBirthday(date);
	}
	
	/**
	 * 根据用户生日计算用户年龄。
	 * @param birthday 用户生日
	 * @return 用户年龄
	 */
	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;
		
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth 
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth 
				age--;
			}
		}
		return age;
	}
	
	/**
	 * 判断时间是否是今天范围的时间。
	 * @param timestamp 时间戳
	 * @return
	 */
	public static boolean isCurrentDay(long timestamp){
		String pDate =  timestampFormat(timestamp, "yyyy-MM-dd");
		String cDate =  timestampFormat(System.currentTimeMillis(), "yyyy-MM-dd");
		return pDate.equals(cDate);
	}
	
	public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座","金牛座", "双子座", "巨蟹座", "狮子座", 
													  "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 
													   23, 23, 23, 22, 22 };
	
	/**
	 * 计算某时间的星座
	 * @param dateFormatString 字符串时间
	 * @return
	 */
	public static String date2Constellation(String dateFormatString) {
		Date date = dateFormatString2Date(dateFormatString);
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		return date2Constellation(time);
	}
	
	/**
	 * 计算某时间的星座
	 * @param timestamp 字符串时间
	 * @return
	 */
	public static String date2Constellation(long timestamp) {
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(timestamp);
		return date2Constellation(time);
	}
	
	/**
	 * 计算某时间的星座
	 * @param time Calendar时间
	 * @return 某时间的星座
	 */
	private static String date2Constellation(Calendar time) {
		int month = time.get(Calendar.MONTH);
		int day = time.get(Calendar.DAY_OF_MONTH);
		if (day < constellationEdgeDay[month]) {
			month = month - 1;
		}
		if (month >= 0) {
			return constellationArr[month];
		}
		// default to return 魔羯
		return constellationArr[11];
	}	

}
