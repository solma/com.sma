package com.sma.thirdpartylibrary.jodatime;

import static org.joda.time.DateTimeConstants.MILLIS_PER_DAY;
import static org.joda.time.DateTimeConstants.MILLIS_PER_HOUR;
import static org.joda.time.DateTimeConstants.MILLIS_PER_SECOND;
import static org.joda.time.DateTimeConstants.SECONDS_PER_HOUR;
import static org.joda.time.DateTimeZone.UTC;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class Time {

  static int[] countHourlyHistogram = new int[24];
  static double[] timeHourlyHistogram = new double[24];

  public static void main(String[] args) throws ParseException {
//    DateTime dt = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
//    System.out.println( DateTimeFormat.forPattern("yyyyMMdd").print(dt) );
//    System.out.println(dt.getMillis() - dt.getMillisOfDay() - 3600 * 1000 * 24 * 10);

    //printTimestamp();

    //if (true) return;
    DateTime dateTime = new DateTime(1415851708000L, UTC);
    System.out.println(dateTime);
    System.out.println(dateTime.millisOfDay().get() + " "
        + (dateTime.getMillis() - dateTime.withMillisOfDay(0).getMillis()));
    //System.out.println( ((DateFormat) new SimpleDateFormat("yyyyMMdd")).parse("20141110").getTime() );
    System.out.println("Millis_in_a_day:" + 1415670653532L % MILLIS_PER_DAY);

    long[] dur1 = new long[]{1415640654027L, 1415640658765L};
    long[] dur2 = new long[]{1415670653532L, 1415676837096L};
    //updateTimeAndCount(dur1);
    updateTimeAndCount(dur2);
  }

  public static void updateTimeAndCount(long[] startAndEndTime) {

    int[] startAndEndHour = new int[2];
    long[] startAndEndMillisecondWithInHour = new long[2];
    for (int i = 0; i < 2; i++) {
      long millisOfDay = startAndEndTime[i] % MILLIS_PER_DAY;
      startAndEndHour[i] = (int) (millisOfDay / MILLIS_PER_HOUR);
      startAndEndMillisecondWithInHour[i] =
          millisOfDay - startAndEndHour[i] * MILLIS_PER_HOUR;
    }

    // update hourly app launches count
    countHourlyHistogram[startAndEndHour[0]]++;

    // update hourly time spent
    for (int hour = startAndEndHour[0]; hour <= startAndEndHour[1]; hour++) {
      if (hour == startAndEndHour[0] && hour == startAndEndHour[1]) {
        timeHourlyHistogram[hour] += (startAndEndMillisecondWithInHour[1]
            - startAndEndMillisecondWithInHour[0]) * 1.0 / MILLIS_PER_SECOND;
        continue;
      }
      if (!(hour == startAndEndHour[0] || hour == startAndEndHour[1])) {
        timeHourlyHistogram[hour] += SECONDS_PER_HOUR;
        continue;
      }
      if (hour == startAndEndHour[0]) {
        timeHourlyHistogram[hour] +=
            (MILLIS_PER_HOUR - startAndEndMillisecondWithInHour[0]) * 1.0 / MILLIS_PER_SECOND;
      } else {
        timeHourlyHistogram[hour] += startAndEndMillisecondWithInHour[1] * 1.0 / MILLIS_PER_SECOND;
      }
    }

    System.out.println(Arrays.toString(startAndEndMillisecondWithInHour));
    System.out.println(Arrays.toString(countHourlyHistogram));
    System.out.println(Arrays.toString(timeHourlyHistogram));
  }

  public static void printTimestamp() {
    String timestamp = "1415854116000"; //"1418112000000";
    System.out.println(new Timestamp(Long.parseLong(timestamp)));

    try {
      System.out.println( (
          (DateFormat) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2014-12-10 10:09:30").getTime() );
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
