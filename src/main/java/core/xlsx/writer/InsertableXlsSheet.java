package core.xlsx.writer;

import core.bundle.BundleHandler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public abstract class InsertableXlsSheet {

    private BundleHandler bundles = new BundleHandler();
    private final XSSFWorkbook workbook;

    public InsertableXlsSheet(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public BundleHandler getBundles() {
        return bundles;
    }

    protected XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public InsertableXlsSheet withBundles(BundleHandler bundles) {
        this.bundles = bundles;

        return this;
    }

    public abstract void setColumnWidthInSheet(Sheet sheet);

    public abstract void create();
}
