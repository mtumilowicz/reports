package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsTableContent;
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
final class SummaryTableContent extends InsertableXlsTableContent {

    SummaryTableContent(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    void create() {
        
        new SummaryTableHeaders(getBundles(), getSheet(), getRowCount()).create();

        int rowCount = getRowCount();
        rowCount++;
        
        int columnCount = 0;
        XSSFRow row = getSheet().createRow(rowCount);

        Cell quantity = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAOMock.getAllEntities().size()));
        quantity.setCellType(CellType.NUMERIC);
        CellUtil.setAlignment(quantity, HorizontalAlignment.LEFT);

        Cell value = CellUtil.createCell(row, columnCount, String.valueOf(BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO)));
        value.setCellType(CellType.NUMERIC);
        CellUtil.setCellStyleProperty(value, CellUtil.DATA_FORMAT, getFormat().money());
        CellUtil.setAlignment(value, HorizontalAlignment.RIGHT);
    }
}
