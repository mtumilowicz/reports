package core.xlsx.writer;

import core.bundle.BundleHandler;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public abstract class InsertableXlsSheet {

    private final BundleHandler bundles;
    private final XSSFSheet sheet;

    protected InsertableXlsSheet(BundleHandler bundles, XSSFWorkbook workbook) {
        this.bundles = bundles;
        this.sheet = workbook.createSheet(loadNameFromBundles());
    }

    public abstract void setColumnWidthInSheet();

    public abstract void create();
    
    public abstract String getName();

    public BundleHandler getBundles() {
        return bundles;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }
    
    private String loadNameFromBundles() {
        return bundles.get(getName());
    }
}
