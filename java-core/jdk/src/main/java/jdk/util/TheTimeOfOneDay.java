package jdk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by whydk on 2016/12/23.
 */
public class TheTimeOfOneDay {

  public static void main(String[] args) throws ParseException {
    //one day`s begin time is 00:00:00
    //one day`s end time is 23:23:59
    String pattern = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sf = new SimpleDateFormat(pattern);
    Date yesterday = sf.parse("2016-12-11 23:59:59");
    Date todayBegin = sf.parse("2016-12-12 00:00:00");
    Date today1 = sf.parse("2016-12-12 00:00:01");
    Date todayend = sf.parse("2016-12-12 23:23:59");

    System.out.println(sf.format(yesterday) + " is before " + sf.format(todayBegin) + ":" + yesterday.before(todayBegin));
    System.out.println(sf.format(todayBegin) + " is before " + sf.format(today1) + ":" + todayBegin.before(today1));
    System.out.println(sf.format(todayBegin) + " is before " + sf.format(todayend) + ":" + todayBegin.before(todayend));
    System.out.println(sf.format(today1) + " is before " + sf.format(todayend) + ":" + today1.before(todayend));
  }
}
