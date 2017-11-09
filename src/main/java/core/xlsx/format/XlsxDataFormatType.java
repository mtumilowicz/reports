package core.xlsx.format;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public enum XlsxDataFormatType {
    MONEY("0.00"), DATE_ONLY("YYYY-MM-DD"), DATE_HOURS("YYYY-MM-DD hh:mm");
    
    private final String format;

    XlsxDataFormatType(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
