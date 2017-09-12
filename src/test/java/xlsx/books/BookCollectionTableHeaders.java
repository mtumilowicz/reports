package xlsx.books;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class BookCollectionTableHeaders {
    private BundleHandler bundles = new BundleHandler();
    private XSSFSheet sheet;
    private int rowCount;

    BookCollectionTableHeaders(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.rowCount = rowCount;
    }

    void create() {
        String[] booksCollectionHeaders = {
                "report.table.book.id",
                "report.table.book.author",
                "report.table.book.title",
                "report.table.book.genre",
                "report.table.book.price",
                "report.table.book.pubDate",
                "report.table.book.review",
                "report.table.book.type"};

        XSSFRow row = sheet.createRow(rowCount);

        int columnCount = 0;

        for (String header : booksCollectionHeaders) {
            Cell headerCell = CellUtil.createCell(row, columnCount++, bundles.get(header));
            addTableHeaderCell(headerCell);
        }
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
