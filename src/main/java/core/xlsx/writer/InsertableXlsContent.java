package core.xlsx.writer;

import core.bundle.BundleHandler;
import core.xlsx.builder.cell.XlsxCellBuilder;
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
    private final XlsxCellBuilder cellBuilder;

    public InsertableXlsContent(BundleHandler bundles, Sheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.format = initDateFormat(sheet.getWorkbook());
        this.rowCount = rowCount;
        this.cellBuilder = new XlsxCellBuilder(sheet);
    }
    
    public abstract void create();

    public BundleHandler getBundles() {
        return bundles;
    }

    public Sheet getSheet() {
        return sheet;
    }

    protected XlsxDataFormat getFormat() {
        return format;
    }

    public int getRowCount() {
        return rowCount;
    }

    public XlsxCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    protected void add(InsertableXlsContent content) {
        content.create();
    }

    private XlsxDataFormat initDateFormat(Workbook workbook) {
        return XlsxDataFormat.Factory.get(workbook.createDataFormat());
    }
}
