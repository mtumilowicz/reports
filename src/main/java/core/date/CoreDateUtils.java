package core.date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Created by mtumilowicz on 2017-08-14.
 */
public final class CoreDateUtils {

    public final static FastDateFormat DATE_ONLY = FastDateFormat.getInstance("yyyy-MM-dd");
    
    private CoreDateUtils() {
    }
}
