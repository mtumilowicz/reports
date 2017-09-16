package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class SummarySheetTitle extends InsertableXlsContent {

    SummarySheetTitle(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    public void create() {
        getSheet().addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(getSheet().createRow(getRowCount()), 0, getBundles().get("report.table.summary.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
    }
}
