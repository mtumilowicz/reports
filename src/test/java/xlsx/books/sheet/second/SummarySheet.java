package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class SummarySheet extends InsertableXlsSheet {

    public SummarySheet(BundleHandler bundles, XSSFWorkbook workbook) {
        super(bundles, workbook);
    }

    public void create() {
        int rowCount = 0;

        new SummarySheetTitle(getBundles(), getSheet(), rowCount).create();

        rowCount++;

        new SummarySheetContent(getBundles(), getSheet(), rowCount).create();

    }

    @Override
    public String getName() {
        return "report.sheet.summary.name";
    }

    @Override
    public void setColumnWidthInSheet() {

    }
}
