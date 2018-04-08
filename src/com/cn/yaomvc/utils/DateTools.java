package com.cn.yaomvc.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class DateTools {
	 
    public static void main(String[] args) {
    	
    	 Calendar cal = Calendar.getInstance();
         cal.clear();
         cal.set(Calendar.YEAR, 2016);
         cal.set(Calendar.WEEK_OF_YEAR,47);
         cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
         System.out.println(cal.getTime());
         cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
         System.out.println(cal.getTime());
         
         
         
         
         System.out.println("--------------根据年和周数查找日期--------------");
         List<String> lst9=DateTools.getAllweekDaysbyzhoushu("2016","47");
         for(String s3:lst9)
         {
        	 System.out.println(s3);
         }
         
         TimeZone timeZone;
         timeZone = TimeZone.getDefault();
			Calendar cal111 = Calendar.getInstance(timeZone);
			cal111.setTime(DateTools.ben51Monday());
	         System.out.println(cal111.toString());
	         //取当前日期的年份里面的周数
	         int currentWeekOfYear = cal111.get(Calendar.WEEK_OF_YEAR);//上周周数
	         int currentWeekOfYear1 = cal.get(Calendar.YEAR);
	         
	         System.out.println("51周数"+currentWeekOfYear+"."+currentWeekOfYear1);
	         
         System.out.println(DateTools.lastMonday().toString());
         System.out.println(DateTools.lastlastMonday().toString());
         System.out.println(DateTools.ben51Monday().toString());
         System.out.println(DateTools.ben52Monday().toString());
        System.out.println(DateTools.benMonday().toString());
         System.out.println("--------------测试上上周一周的每一天--------------");
         
         Date dd1=DateTools.lastlastMonday();
         List<String> lst8=DateTools.getAllweekDays(dd1);
         for(String s3:lst8)
         {
        	 System.out.println(s3);
         }
         
         System.out.println("--------------测试上周一周的每一天--------------");
         
         Date dd=DateTools.lastMonday();
         List<String> lst7=DateTools.getAllweekDays(dd);
         for(String s3:lst7)
         {
        	 System.out.println(s3);
         }
         
         
    	 System.out.println("--------------测试日期周的每一天--------------");
         List<String> lst5=  getALlweekDays1();//获取当前日期下，一周的每一天
         for(String s:lst5)
         {
        	 System.out.println(s);
         }
    	 System.out.println("--------------当前日期下一周的每一天--------------");
     List<String> lst=  getALlweekDays();//获取当前日期下，一周的每一天
     for(String s:lst)
     {
    	 System.out.println(s);
     }
     List <String>lst2=getAllDaysMonth();//获取当前日期下，一个月的每一天
     System.out.println("--------------一个月的每一天--------------");
     for(String s2:lst2)
     {
  	   System.out.println(s2);
     }
     System.out.println("--------------根据特定的日期‘2016-11-29’获取特定日期所在一周的每一天--------------");
     Date d=DateTools.paraseStringToDate("2016-11-29");
     List <String>lst3=DateTools.getAllweekDays(d);
     for(String s3:lst3)
     {
    	 System.out.println(s3);
     }
     System.out.println("--------------根据特定的日期‘2014-8-1’获取特定日期所在一个月的每一天--------------");
     Date d2=DateTools.paraseStringToDate("2014-8-1");
     List <String>lst4=DateTools.getAllDaysMonthByDate(d2);
     for(String s3:lst4)
     {
    	 System.out.println(s3);
     }
     
    }
 
    public static List<String> getAllweekDaysbyzhoushu(String string, String string2) {
    	
    	Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(string)); // 2016年
        cal.set(Calendar.WEEK_OF_YEAR, Integer.valueOf(string2)); // 设置为2016年的第10周
        List<String> lst=new ArrayList();
        setToFirstDay(cal);
		for (int i = 0; i < 7; i++) {
			String day = printDay(cal);
			lst.add(day);
			cal.add(Calendar.DATE, 1);
		}
		return lst;
	}

	private static final int FIRST_DAY = Calendar.MONDAY;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public String getMonthStart()//获取月初日期
    {
 	   Date d = new Date();
          // 月初
       // System.out.println("月初" + sdf.format(getMonthStart(d)));
 	   return sdf.format(getMonthStart(d));
    }
    public static String getMonthStartStr(Date d)//根据传入日期来获取一个月的开始时间
    {
	   return sdf.format(getMonthStart(d));
    }
   public static String getMonthEndStr(Date d)//根据传入时间获取一个月月末时间
    {
 	   
 	  return sdf.format(getMonthEnd(d));
    }
   public static List<String>getAllDaysMonthByDate(Date d)//根据传入的日期获取所在月份所有日期
   {
     	List<String> lst=new ArrayList();
        Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        while (!date.after(monthEnd)) {
            //System.out.println(sdf.format(date));
       	 lst.add(sdf.format(date));
            date = getNext(date);
        }
        return lst;
   }
   public static Date paraseStringToDate(String timestr )//将字符串转化为日期
   {
	   Date date=null;
	  
          
           Format f = new SimpleDateFormat("yyyy-MM-dd");
            try {
				date = (Date) f.parseObject(timestr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       return date;
   }
  
   public String getMonthEnd()//获取月末日期
   {
	   Date d = new Date();
	  return sdf.format(getMonthEnd(d));
   }
    public static List<String>getAllDaysMonth()
    {
    	List<String> lst=new ArrayList();
    	 Date d = new Date();
         // 月初
        // System.out.println("月初" + sdf.format(getMonthStart(d)));
         // 月末
         //System.out.println("月末" + sdf.format(getMonthEnd(d)));
  
         Date date = getMonthStart(d);
         Date monthEnd = getMonthEnd(d);
         while (!date.after(monthEnd)) {
             //System.out.println(sdf.format(date));
        	 lst.add(sdf.format(date));
             date = getNext(date);
         }
         return lst;
    }
    private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }
 
    private static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }
 
    private static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
    public static String getWeekStartDay(Date d) {//根据日期来获取一周的第一天
		Calendar c = Calendar.getInstance();
       List <String>lst=new ArrayList();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(0);
	}
    public static String getWeekEndtDay(Date d) {//根据日期来获取一周的最后一天
		Calendar c = Calendar.getInstance();
       List <String>lst=new ArrayList();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(6);
	}
	public static List<String> getAllweekDays(Date d) {//根据日期来获取其所在周的每一天
		Calendar c = Calendar.getInstance();
       List <String>lst=new ArrayList();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst;
	}
    public static List<String> getALlweekDays() {
    	List<String>lst=new ArrayList();
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            String day=printDay(calendar);
        	lst.add(day);
            calendar.add(Calendar.DATE, 1);
        }
        return lst;
    }
 
    public static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
            //calendar.add(Calendar.DATE, -3);
        }
    }
    
    public static List<String> getALlweekDays1() {
    	List<String>lst=new ArrayList();
        Calendar calendar = Calendar.getInstance();
        setToFirstDay1(calendar);
  
        for (int i = 0; i < 7; i++) {
            String day=printDay(calendar);
        	lst.add(day);
            calendar.add(Calendar.DATE, 1);
        }
        return lst;
    }
 
    private static void setToFirstDay1(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, 1);
        }
    }
 
    private static String  printDay(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }
    /**
     * 获取上周五时间
     */
    public Date lastFirday() {

//作用防止周日得到本周日期
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 9);

        return DateTools.getFirstDayOfWeek(calendar.getTime(), 6);//这是从上周日开始数的到本周五为6

    }
    /**
     * 获取上周日时间
     */
    public static Date lastSunday() {

    	//作用防止周日得到本周日期
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 11);

        return DateTools.getFirstDayOfWeek(calendar.getTime(), 1);//这是从上周日开始数的到本周五为6

    }

 

/**
     * 获取上周一时间
     */
    public static Date lastMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
        int offset = 1 - dayOfWeek;
        System.out.println(offset);
        calendar.add(Calendar.DATE, offset - 7);
        System.out.println(calendar.getTime());
        return DateTools.getFirstDayOfWeek(calendar.getTime(), 2);
    }


/**
     * 获取上上周一时间
     */
    public static Date lastlastMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
        int offset = 1 - dayOfWeek;
        System.out.println(offset);
        calendar.add(Calendar.DATE, offset - 14);
        System.out.println(calendar.getTime());
        return DateTools.getFirstDayOfWeek(calendar.getTime(), 2);
    }
 
    
    /**
     * 获取本周周一时间
     */
    public static Date benMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
        int offset = 1 - dayOfWeek;
        System.out.println(offset);
        calendar.add(Calendar.DATE, offset - 0);
        System.out.println(calendar.getTime());
        return DateTools.getFirstDayOfWeek(calendar.getTime(), 2);
    }
    /**
     * 获取51周周一时间
     */
    public static Date ben51Monday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
        int offset = 1 - dayOfWeek;
        System.out.println(offset);
        calendar.add(Calendar.DATE, offset - 21);
        System.out.println(calendar.getTime());
        return DateTools.getFirstDayOfWeek(calendar.getTime(), 2);
    }

    /**
     * 获取52周周一时间
     */
    public static Date ben52Monday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
        int offset = 1 - dayOfWeek;
        System.out.println(offset);
        calendar.add(Calendar.DATE, offset - 14);
        System.out.println(calendar.getTime());
        return DateTools.getFirstDayOfWeek(calendar.getTime(), 2);
    }
    /**
     * 获取50周周一时间
     */
    public static Date ben50Monday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
        int offset = 1 - dayOfWeek;
        System.out.println(offset);
        calendar.add(Calendar.DATE, offset - 28);
        System.out.println(calendar.getTime());
        return DateTools.getFirstDayOfWeek(calendar.getTime(), 2);
    }
/**
  * 得到某一天的该星期的第一日 00:00:00
  * 
  * @param date
  * @param firstDayOfWeek
  *            一个星期的第一天为星期几
  * 
  * @return
  */
 public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
  Calendar cal = Calendar.getInstance();
  if (date != null)
   cal.setTime(date);
  cal.setFirstDayOfWeek(firstDayOfWeek);//设置一星期的第一天是哪一天
  cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);//指示一个星期中的某天
  cal.set(Calendar.HOUR_OF_DAY, 0);//指示一天中的小时。HOUR_OF_DAY 用于 24 小时制时钟。例如，在 10:04:15.250 PM 这一时刻，HOUR_OF_DAY 为 22。
  cal.set(Calendar.MINUTE, 0);//指示一小时中的分钟。例如，在 10:04:15.250 PM 这一时刻，MINUTE 为 4。
  cal.set(Calendar.SECOND, 0);
  cal.set(Calendar.MILLISECOND, 0);
  return cal.getTime();
 }
 
}