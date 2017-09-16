package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class BookCollectionSheet {
    private BundleHandler bundles = new BundleHandler();
    private XSSFWorkbook workbook;

    public BookCollectionSheet(BundleHandler bundles, XSSFWorkbook workbook) {
        this.bundles = bundles;
        this.workbook = workbook;
    }

    public void create() {
        XSSFSheet sheet = workbook.createSheet(bundles.get("report.sheet.book.name"));
        setColumnWidthInSheet(sheet);

        int rowCount = 0;

        new BookCollectionSheetTitle(bundles, sheet, rowCount).create();

        rowCount++;

        new BookCollectionTableHeaders(bundles, sheet, rowCount).create();

        rowCount++;

        new BookCollectionTableContent(bundles, sheet, rowCount).create();
    }

    private void setColumnWidthInSheet(Sheet sheet) {
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(7, 3000);
    }
}
