package com.prj.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述: 日期工具类
 * @author 胡义振
 * @date 2016-03-10
 */
public final class UfdmDateUtil
{

	/**
	 * 描述: 日期转为字符串
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date,String format)
	{
		String result = "";
		if (date != null)
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			}
			catch (Exception ex)
			{
				result = "";
			}
		}
		return result;
	}

	/**
	 * 描述: 字符串转为日期类型
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param strDate 
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String strDate,String format)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(strDate); 
		}
		catch (Exception er)
		{
			return null;
		}
	}
	

	/**
	 * 描述: 获取当前完整时间
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @return
	 */
	public static String getCurrentTime()
	{
		return dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 描述: 获取当前年份
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @return
	 */
	public static String getCurrentYear()
	{
		return dateToString(new Date(), "yyyy");
	}
	
	/**
	 * 描述: 获取当前日期
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @return
	 */
	public static String getCurrentDate()
	{
		return dateToString(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 描述: 获取当前时间(时、分、秒)
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @return
	 */
	public static String getCurrentSimpleTime()
	{
		return dateToString(new Date(), "HH:mm:ss");   
	}

	/**
	 * 描述: 把日期转成完整格式。如：2014-1-1 转化后为 2014-01-01
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param strDate
	 * @return
	 */
	public static String toComplexDate(String strDate)
	{
		try
		{
			String tmp_date[] = strDate.split("-");
			String tmp_year = tmp_date[0];
			String tmp_month = tmp_date[1];
			String tmp_day = tmp_date[2];
			if (Integer.parseInt(tmp_month) <= 9)
			{
				tmp_month = "0" + tmp_month;
			}
			if (Integer.parseInt(tmp_day) <= 9)
			{
				tmp_day = "0" + tmp_day;
			}
			return tmp_year + "-" + tmp_month + "-" + tmp_day;
		}
		catch (Exception er)
		{
			return strDate;
		}
	}

	/**
	 * 描述: 获取N年后日期
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param date
	 * @param years  年数
	 * @return
	 */
	public static Date getAfterDateByYears(Date date,int years) 
	{
		try
		{
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.YEAR,years);
			return now.getTime();  
		}
		catch(Exception er) 
		{
			return date;   
		}
	}
	
	/**
	 * 描述: 获取N月后日期
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param date
	 * @param months 月数
	 * @return
	 */
	public static Date getAfterDateByMonths(Date date,int months) 
	{
		try
		{
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.MONTH,months);
			return now.getTime();  
		}
		catch(Exception er) 
		{
			return date;   
		}
	}
	
	/**
	 * 描述: 获取N天后日期
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param date
	 * @param months 月数
	 * @return
	 */
	public static Date getAfterDateByDays(Date date,int days) 
	{
		try
		{
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.DAY_OF_YEAR,days);
			return now.getTime();  
		}
		catch(Exception er) 
		{
			return date;   
		}
	}
	
	/**
	 * 描述: 获取N小时后日期
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param date
	 * @param hours 小时数
	 * @return
	 */
	public static Date getAfterDateByHours(Date date,int hours) 
	{
		try
		{
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.HOUR,hours);
			return now.getTime();  
		}
		catch(Exception er) 
		{
			return date;   
		}
	}
	
	/**
	 * 描述: 获取N分钟后日期
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param date
	 * @param hours 小时数
	 * @return
	 */
	public static Date getAfterDateByMinutes(Date date,int minutes) 
	{
		try
		{
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.MINUTE,minutes);
			return now.getTime();  
		}
		catch(Exception er) 
		{
			return date;   
		}
	}
	
	/**
	 * 描述: 计算两日期相差天数
	 * @auther 胡义振
	 * @date 2016-03-10 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public static int getBetweenDays(Date startDate,Date endDate) 
	{
		try
		{
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);
			long startTime = startCalendar.getTimeInMillis();
		    Calendar endCalendar = Calendar.getInstance();
		    endCalendar.setTime(endDate);
		    long endTime = endCalendar.getTimeInMillis();
		    long between_days = (endTime-startTime)/(1000*60*60*24);
		    return Integer.parseInt(String.valueOf(between_days));
		}
	    catch(Exception er)
	    {
		    return 0;
	    }
	}
	
	public static void main(String str[]){
		
		Date beforeDate = stringToDate("2014-10-20","yyyy-MM-dd");
		Date afterDate = stringToDate("2014-10-18","yyyy-MM-dd");
		
		System.out.println(getBetweenDays(beforeDate,afterDate));
		
	}
	

}
