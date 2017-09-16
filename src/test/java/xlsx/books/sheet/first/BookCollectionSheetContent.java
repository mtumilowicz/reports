package xlsx.books.sheet.first;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import dao.BookDAOMock;
import entity.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
        new BookCollectionTableHeaders().create();

        int rowCount = getRowCount();
        rowCount++;

        for (Book book : BookDAOMock.getAllEntities()) {
            XSSFRow row = getSheet().createRow(rowCount++);
            int columnCount = 0;

            CellUtil.createCell(row, columnCount++, book.getId());

            CellUtil.createCell(row, columnCount++, book.getAuthor());

            CellUtil.createCell(row, columnCount++, book.getTitle());

            CellUtil.createCell(row, columnCount++, book.getGenre());

            Cell cell1 = CellUtil.createCell(row, columnCount++, String.valueOf(book.getPrice()));
            CellUtil.setAlignment(cell1, HorizontalAlignment.RIGHT);
            CellUtil.setCellStyleProperty(cell1, CellUtil.DATA_FORMAT, getFormat().money());
            cell1.setCellType(CellType.NUMERIC);


            Cell cell2 = CellUtil.createCell(row, columnCount++, "");
            cell2.setCellValue(book.getPubDate());
            CellUtil.setCellStyleProperty(cell2, CellUtil.DATA_FORMAT, getFormat().dateHours());
            CellUtil.setAlignment(cell2, HorizontalAlignment.CENTER);

            CellUtil.createCell(row, columnCount++, book.getReview());

            CellUtil.createCell(row, columnCount, getBundles().get(book.getType()));
        }
    }

    private final class BookCollectionTableHeaders {
        
        void create() {
            int columnCount = 0;

            XSSFRow row = getSheet().createRow(getRowCount());

            for (String header : getHeaders()) {
                Cell headerCell = CellUtil.createCell(row, columnCount++, getBundles().get(header));
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

        private String[] getHeaders() {
            return new String[] {
                    "report.table.book.id",
                    "report.table.book.author",
                    "report.table.book.title",
                    "report.table.book.genre",
                    "report.table.book.price",
                    "report.table.book.pubDate",
                    "report.table.book.review",
                    "report.table.book.type"};
        }

    }

}
