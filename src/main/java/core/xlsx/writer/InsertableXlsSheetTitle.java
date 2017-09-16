package core.xlsx.writer;

import core.bundle.BundleHandler;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public abstract class InsertableXlsSheetTitle {
    private BundleHandler bundles;
    private XSSFSheet sheet;
    private int rowCount;

    public InsertableXlsSheetTitle(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.rowCount = rowCount;
    }

    public BundleHandler getBundles() {
        return bundles;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    protected int getRowCount() {
        return rowCount;
    }

    public abstract void create();
}
