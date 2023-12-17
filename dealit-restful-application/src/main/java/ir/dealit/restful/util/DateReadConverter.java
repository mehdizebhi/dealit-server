package ir.dealit.restful.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ReadingConverter
public class DateReadConverter implements Converter<Date, DateTime> {

    @Override
    public DateTime convert(Date source) {
        return new DateTime(source);
    }

    /*@Override
    public DateTime convert(LocalDateTime source) {
        // Convert Java LocalDateTime to Joda DateTime using the system default time zone
        return new DateTime(source.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), DateTimeZone.getDefault());
    }*/
}
