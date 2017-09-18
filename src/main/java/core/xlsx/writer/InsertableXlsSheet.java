package core.xlsx.writer;

import core.bundle.BundleHandler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public abstract class InsertableXlsSheet {

    private final BundleHandler bundles;
    private final Sheet sheet;

    protected InsertableXlsSheet(BundleHandler bundles, Workbook workbook) {
        this.bundles = bundles;
        this.sheet = workbook.createSheet(loadNameFromBundles());
    }

    public abstract void setColumnWidthInSheet();

    public abstract void create();
    
    public abstract String getBundleKeySheetName();

    public BundleHandler getBundles() {
        return bundles;
    }

    public Sheet getSheet() {
        return sheet;
    }
    
    protected void add(InsertableXlsContent content) {
        content.create();
    }
    
    private String loadNameFromBundles() {
        return bundles.get(getBundleKeySheetName());
    }
}
