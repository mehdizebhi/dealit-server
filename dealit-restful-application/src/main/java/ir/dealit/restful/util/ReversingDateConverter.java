package ir.dealit.restful.util;

import org.joda.time.DateTime;
import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.convert.ValueConversionContext;
import org.springframework.data.mapping.PersistentProperty;

import java.util.Date;

public class ReversingDateConverter implements PropertyValueConverter<DateTime, Date, ValueConversionContext<? extends PersistentProperty<?>>> {

    @Override
    public DateTime read(Date value, ValueConversionContext context) {
        return new DateTime(value);
    }

    @Override
    public Date write(DateTime value, ValueConversionContext context) {
        return value.toDate();
    }
}
