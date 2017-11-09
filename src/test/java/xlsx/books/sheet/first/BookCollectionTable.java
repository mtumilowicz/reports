package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import dao.BookDAOMock;
import entity.Book;
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

            getCellBuilder().cell(row, columnCount++, book.getId()).build();
            getCellBuilder().cell(row, columnCount++, book.getAuthor()).build();
            getCellBuilder().cell(row, columnCount++, book.getTitle()).build();
            getCellBuilder().cell(row, columnCount++, book.getGenre()).build();

            getCellBuilder().cell(row, columnCount++, book.getPrice())
                    .alignment(HorizontalAlignment.RIGHT)
                    .dataFormat(getFormat().money())
                    .build();


            getCellBuilder().cell(row, columnCount++, book.getPubDate() ,getFormat().dateHours())
                    .alignment(HorizontalAlignment.CENTER)
                    .build();

            getCellBuilder().cell(row, columnCount++, book.getReview()).build();

            getCellBuilder().cell(row, columnCount, getBundles().get(book.getType())).build();
        }
    }

    private final class BookCollectionTableHeaders {

        void create() {
            int columnCount = 0;

            Row row = getSheet().createRow(getRowCount());

            getCellBuilder().setDefaultForegroundColor(IndexedColors.GREY_40_PERCENT);
            for (String header : getHeaders()) {
                getCellBuilder().cell(row, columnCount++, getBundles().get(header))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
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
