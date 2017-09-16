package xlsx.books.sheet.second;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsTableContent;
import dao.BookDAOMock;
import org.apache.poi.ss.usermodel.*;
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
        
        new SummaryTableHeaders().create();

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

    private class SummaryTableHeaders {

        void create() {
            int columnCount = 0;
            XSSFRow row = getSheet().createRow(getRowCount());
            Cell quantityHeader = CellUtil.createCell(row, columnCount++, getBundles().get("report.table.summary.quantity"));
            addTableHeaderCell(quantityHeader);


            Cell valueHeader = CellUtil.createCell(row, columnCount, getBundles().get("report.table.summary.value"));
            addTableHeaderCell(valueHeader);
        }

        private void addTableHeaderCell(Cell cell) {
            CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
            GenericBuilder.of(() -> style)
                    .with(CellStyle::setBorderLeft, BorderStyle.THIN)
                    .with(CellStyle::setBorderBottom, BorderStyle.THIN)
                    .with(CellStyle::setFillForegroundColor, IndexedColors.GREY_40_PERCENT.getIndex())
                    .with(CellStyle::setFillPattern, FillPatternType.SOLID_FOREGROUND).build();
            cell.setCellStyle(style);
        }
    }
}
