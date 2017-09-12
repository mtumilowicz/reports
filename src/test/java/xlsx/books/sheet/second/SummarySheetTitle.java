package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class SummarySheetTitle {
    private BundleHandler bundles = new BundleHandler();
    private XSSFSheet sheet;
    private int rowCount;

    SummarySheetTitle(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.rowCount = rowCount;
    }

    void create() {
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(sheet.createRow(rowCount), 0, bundles.get("report.table.summary.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
    }
}
