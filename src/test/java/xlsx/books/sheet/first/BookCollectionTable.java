package xlsx.books.sheet.first;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import dao.BookDAOMock;
import entity.Book;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public class BookCollectionTable extends InsertableXlsContent {
    BookCollectionTable(BundleHandler bundles, Sheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    @Override
    public void create() {
        new BookCollectionTableHeaders().create();

        int rowCount = getRowCount();
        rowCount++;

        for (Book book : BookDAOMock.getAllEntities()) {
            Row row = getSheet().createRow(rowCount++);
            int columnCount = 0;

            getCellBuilder().row(row, columnCount++, book.getId()).build();
            getCellBuilder().row(row, columnCount++, book.getAuthor()).build();
            getCellBuilder().row(row, columnCount++, book.getTitle()).build();
            getCellBuilder().row(row, columnCount++, book.getGenre()).build();

            getCellBuilder().row(row, columnCount++, String.valueOf(book.getPrice()))
                    .alignments(HorizontalAlignment.RIGHT)
                    .dataFormat(getFormat().money())
                    .cellType(CellType.NUMERIC)
                    .build();


            Cell cell2 = getCellBuilder().row(row, columnCount++, StringUtils.EMPTY)
                    .alignments(HorizontalAlignment.CENTER)
                    .dataFormat(getFormat().dateHours())
                    .build();
            cell2.setCellValue(book.getPubDate());

            getCellBuilder().row(row, columnCount++, book.getReview()).build();

            getCellBuilder().row(row, columnCount, getBundles().get(book.getType())).build();
        }
    }

    private final class BookCollectionTableHeaders {

        void create() {
            int columnCount = 0;

            Row row = getSheet().createRow(getRowCount());

            CellStyle style = GenericBuilder.of(() -> getSheet().getWorkbook().createCellStyle())
                    .with(CellStyle::setBorderLeft, BorderStyle.THIN)
                    .with(CellStyle::setBorderBottom, BorderStyle.THIN)
                    .with(CellStyle::setFillForegroundColor, IndexedColors.GREY_40_PERCENT.getIndex())
                    .with(CellStyle::setFillPattern, FillPatternType.SOLID_FOREGROUND).build();
            
            for (String header : getHeaders()) {
                getCellBuilder().row(row, columnCount++, getBundles().get(header))
                        .cellStyle(style).build();
            }
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
