package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class BookCollectionSheetContent extends InsertableXlsContent {

    BookCollectionSheetContent(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    @Override
    public void create() {
        new BookCollectionTable(getBundles(), getSheet(), getRowCount()).create();
    }

}
