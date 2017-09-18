package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsSheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class BookCollectionSheet extends InsertableXlsSheet {

    public BookCollectionSheet(BundleHandler bundles, Workbook workbook) {
        super(bundles, workbook);
    }

    @Override
    public void create() {
        setColumnWidthInSheet();

        int rowCount = 0;

        add(new BookCollectionSheetTitle(getBundles(), getSheet(), rowCount));

        rowCount++;
        
        add(new BookCollectionSheetContent(getBundles(), getSheet(), rowCount));
    }

    @Override
    public String getBundleKeySheetName() {
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
