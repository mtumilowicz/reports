package xlsx.books.sheet.second;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class SummaryTableHeaders {
    private BundleHandler bundles = new BundleHandler();
    private XSSFSheet sheet;
    private int rowCount;

    SummaryTableHeaders(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.rowCount = rowCount;
    }

    void create() {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount);
        Cell quantityHeader = CellUtil.createCell(row, columnCount++, bundles.get("report.table.summary.quantity"));
        addTableHeaderCell(quantityHeader);


        Cell valueHeader = CellUtil.createCell(row, columnCount, bundles.get("report.table.summary.value"));
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
