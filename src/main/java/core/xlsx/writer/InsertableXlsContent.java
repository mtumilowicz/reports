package core.xlsx.writer;

import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public abstract class InsertableXlsContent {
    private BundleHandler bundles = new BundleHandler();
    private final XSSFSheet sheet;
    private final XlsxDataFormat format;
    private final int rowCount;

    public InsertableXlsContent(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.format = initDateFormat(sheet.getWorkbook());
        this.rowCount = rowCount;
    }
    
    public abstract void create();

    public BundleHandler getBundles() {
        return bundles;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public XlsxDataFormat getFormat() {
        return format;
    }

    public int getRowCount() {
        return rowCount;
    }

    protected void add(InsertableXlsContent content) {
        content.create();
    }

    private XlsxDataFormat initDateFormat(XSSFWorkbook workbook) {
        return XlsxDataFormat.Factory.get(workbook.createDataFormat());
    }
}
