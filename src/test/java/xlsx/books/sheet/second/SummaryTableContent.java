package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import dao.BookDAOMock;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.math.BigDecimal;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class SummaryTableContent {
    private BundleHandler bundles = new BundleHandler();
    private XSSFSheet sheet;
    private XlsxDataFormat format;
    private int rowCount;

    SummaryTableContent(BundleHandler bundles, XSSFSheet sheet, XlsxDataFormat format, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.format = format;
        this.rowCount = rowCount;
    }

    void create() {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount++);

        Cell quantity = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAOMock.getAllEntities().size()));
        quantity.setCellType(CellType.NUMERIC);
        CellUtil.setAlignment(quantity, HorizontalAlignment.LEFT);

        Cell value = CellUtil.createCell(row, columnCount, String.valueOf(BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO)));
        value.setCellType(CellType.NUMERIC);
        CellUtil.setCellStyleProperty(value, CellUtil.DATA_FORMAT, format.money());
        CellUtil.setAlignment(value, HorizontalAlignment.RIGHT);
    }
}
