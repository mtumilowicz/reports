package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class BookCollectionSheet extends InsertableXlsSheet {

    public BookCollectionSheet(BundleHandler bundles, XSSFWorkbook workbook) {
        super(bundles, workbook);
    }

    @Override
    public void create() {
        setColumnWidthInSheet();

        int rowCount = 0;

        new BookCollectionSheetTitle(getBundles(), getSheet(), rowCount).create();

        rowCount++;
        
        new BookCollectionSheetContent(getBundles(), getSheet(), rowCount).create();
    }

    @Override
    public String getName() {
        return "report.sheet.book.name";
    }

    @Override
    public void setColumnWidthInSheet() {
        getSheet().setColumnWidth(1, 5000);
        getSheet().setColumnWidth(2, 8000);
        getSheet().setColumnWidth(5, 4500);
        getSheet().setColumnWidth(7, 3000);
    }
}
