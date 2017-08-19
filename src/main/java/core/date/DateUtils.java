package core.date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Created by mtumilowicz on 2017-08-14.
 */
public final class DateUtils {

    public final static FastDateFormat ONLY_DATE = FastDateFormat.getInstance("yyyy-MM-dd");
    
    private DateUtils() {
    }
}
