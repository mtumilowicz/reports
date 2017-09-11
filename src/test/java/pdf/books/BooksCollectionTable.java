package pdf.books;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import core.pdf.writer.AbstractDocumentWriter;
import core.pdf.writer.AbstractInsertablePdfTable;
import dao.BookDAOMock;
import entity.Book;
import org.apache.commons.collections4.ListUtils;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class BooksCollectionTable extends AbstractInsertablePdfTable {

    BooksCollectionTable(AbstractDocumentWriter writer) {
        super(writer);
    }

    @Override
    public void insertInto(Document document) {
        Table table = new Table(new float[]{1, 1, 1, 1, 1, 1, 1, 1})
                .setFixedLayout()
                .setWidthPercent(100);

        table.setDocument(document);
        
        table.addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.id"))
                .backgroundColorStrike()
                .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.author"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.title"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.genre"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.price"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.pubDate"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.review"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(getCellBuilder().value(getBundles().get("report.table.book.type"))
                        .backgroundColorStrike()
                        .build());
        ListUtils.emptyIfNull(BookDAOMock.getAllEntities()).forEach(book -> addRow(table, book));
        table.complete();
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
