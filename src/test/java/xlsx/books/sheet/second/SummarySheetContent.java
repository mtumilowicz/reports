package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class SummarySheetContent extends InsertableXlsContent {

    SummarySheetContent(BundleHandler bundles, Sheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    @Override
    public void create() {
        add(new SummaryTable(getBundles(), getSheet(), getRowCount()));
    }
}
