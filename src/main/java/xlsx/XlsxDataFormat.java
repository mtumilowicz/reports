package xlsx;

import com.google.common.collect.ImmutableMap;
import org.apache.poi.ss.usermodel.DataFormat;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public class XlsxDataFormat {
    private final DataFormat format;
    private final ImmutableMap<XlsxDataFormatType, Short> map;

    public XlsxDataFormat(DataFormat format) {
        this.format = format;
        map = ImmutableMap.of(XlsxDataFormatType.MONEY, format.getFormat(CONSTANTS.MONEY),
                XlsxDataFormatType.DATE_ONLY, format.getFormat(CONSTANTS.DATE_ONLY),
                XlsxDataFormatType.DATE_HOURS,format.getFormat(CONSTANTS.DATE_HOURS));
    }
    
    public Short getFormat(XlsxDataFormatType type) {
        return map.get(type);
    }
    
    public Short money() {
        return map.get(XlsxDataFormatType.MONEY);
    }

    public Short dateOnly() {
        return map.get(XlsxDataFormatType.DATE_ONLY);
    }

    public Short dateHours() {
        return map.get(XlsxDataFormatType.DATE_HOURS);
    }
    
    private static final class CONSTANTS {
        private static final String MONEY = "#.00";
        private static final String DATE_ONLY = "YYYY-MM-DD";
        private static final String DATE_HOURS = "YYYY-MM-DD hh:mm";
    }
}
