package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class SummarySheet {

    private BundleHandler bundles = new BundleHandler();
    private XlsxDataFormat format;
    private XSSFWorkbook workbook;

    public SummarySheet(BundleHandler bundles, XlsxDataFormat format, XSSFWorkbook workbook) {
        this.bundles = bundles;
        this.format = format;
        this.workbook = workbook;
    }

    public void create() {
        XSSFSheet sheet = workbook.createSheet(bundles.get("report.sheet.summary.name"));

        int rowCount = 0;

        new SummarySheetTitle(bundles, sheet, rowCount).create();

        rowCount++;

        new SummaryTableHeaders(bundles, sheet, rowCount).create();

        rowCount++;

        new SummaryTableContent(bundles, sheet, format, rowCount).create();

    }
}
