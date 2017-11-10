package core.xlsx.format;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.apache.poi.ss.usermodel.DataFormat;

import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public class XlsxDataFormat {
    private final ImmutableMap<XlsxDataFormatType, Short> map;

    private XlsxDataFormat(ImmutableMap<XlsxDataFormatType, Short> map) {
        this.map = map;
    }
    
    public Short of(XlsxDataFormatType type) {
        Preconditions.checkArgument(type != null);
        return Objects.requireNonNull(map.get(type));
    }

    public static final class Factory {
        public static final XlsxDataFormat get(DataFormat format) {
            Preconditions.checkArgument(format != null);

            ImmutableMap<XlsxDataFormatType, Short> map = ImmutableMap.of(XlsxDataFormatType.MONEY, format.getFormat(XlsxDataFormatType.MONEY.getFormat()),
                    XlsxDataFormatType.DATE_ONLY, format.getFormat(XlsxDataFormatType.DATE_ONLY.getFormat()),
                    XlsxDataFormatType.DATE_HOURS, format.getFormat(XlsxDataFormatType.DATE_HOURS.getFormat()));

            return new XlsxDataFormat(map);
        }
    }
}
