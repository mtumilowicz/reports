package pdf.books;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.writer.InsertablePdfTable;
import dao.BookDAOMock;
import entity.Book;
import org.apache.commons.collections4.ListUtils;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class BooksCollectionTable extends InsertablePdfTable {

    BooksCollectionTable(BundleHandler bundles) {
        super(bundles);
    }

    @Override
    public Table get() {
        Table table = new Table(new float[]{1, 1, 1, 1, 1, 1, 1, 1})
                .setFixedLayout()
                .setWidthPercent(100);
        getCellBuilder().setDefaultBackgroundColor(Color.LIGHT_GRAY);
        
        table.addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.id")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.author")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.title")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.genre")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.price")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.pubDate")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.review")).build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.type")).build());

        getCellBuilder().setDefaultBackgroundColor(null);
        ListUtils.emptyIfNull(BookDAOMock.getAllEntities()).forEach(book -> addRow(table, book));
        
        return table;
    }

    private void addRow(Table table, Book book) {
        table.addCell(getCellBuilder().value(book.getId()).build())
                .addCell(getCellBuilder().value(book.getAuthor()).build())
                .addCell(getCellBuilder().value(book.getTitle()).build())
                .addCell(getCellBuilder().value(book.getGenre()).build())
                .addCell(getCellBuilder().value(book.getPrice()).right().build())
                .addCell(getCellBuilder().value(book.getPubDate()).center().build())
                .addCell(getCellBuilder().value(book.getReview()).build())
                .addCell(getCellBuilder().value(getBundles().get(book.getType())).build());
    }
}
