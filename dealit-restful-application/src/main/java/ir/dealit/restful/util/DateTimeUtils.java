package ir.dealit.restful.util;

import org.joda.time.DateTime;

import java.util.Date;

public class DateTimeUtils {

    public static DateTime withTimeAtStartOfDay(Date date) {
        return new DateTime(date).withTimeAtStartOfDay();
    }

    public static DateTime withTimeAtEndOfDay(Date date) {
        return new DateTime(date).withTime(23, 59, 59, 999);
    }
}
