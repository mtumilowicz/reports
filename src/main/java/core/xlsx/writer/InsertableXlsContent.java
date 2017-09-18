package core.xlsx.writer;

import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public abstract class InsertableXlsContent {
    private BundleHandler bundles = new BundleHandler();
    private final Sheet sheet;
    private final XlsxDataFormat format;
    private final int rowCount;

    public InsertableXlsContent(BundleHandler bundles, Sheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.format = initDateFormat(sheet.getWorkbook());
        this.rowCount = rowCount;
    }
    
    public abstract void create();

    public BundleHandler getBundles() {
        return bundles;
    }

    public Sheet getSheet() {
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

    private XlsxDataFormat initDateFormat(Workbook workbook) {
        return XlsxDataFormat.Factory.get(workbook.createDataFormat());
    }
}
