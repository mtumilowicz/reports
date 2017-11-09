package xlsx.books.sheet.first;

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
                    .alignment(HorizontalAlignment.RIGHT)
                    .dataFormat(getFormat().money())
                    .cellType(CellType.NUMERIC)
                    .build();


            Cell cell2 = getCellBuilder().row(row, columnCount++, StringUtils.EMPTY)
                    .alignment(HorizontalAlignment.CENTER)
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

            for (String header : getHeaders()) {
                getCellBuilder().row(row, columnCount++, getBundles().get(header))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .foregroundColor(IndexedColors.GREY_40_PERCENT)
                        .border()
                        .build();
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
