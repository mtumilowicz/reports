package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class BookCollectionSheetTitle extends InsertableXlsContent {

    BookCollectionSheetTitle(BundleHandler bundles, Sheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    @Override
    public void create() {
        getSheet().addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(getSheet().createRow(getRowCount()), 0, getBundles().get("report.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
        XSSFFont font = (XSSFFont) titleCell.getSheet().getWorkbook().createFont();
        font.setFontHeight((short) 500);
        CellUtil.setFont(titleCell, font);
    }
}
