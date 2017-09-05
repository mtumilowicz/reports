package pdf.books;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;
import core.pdf.writer.AbstractDocumentWriter;
import dao.BookDAOMock;
import entity.Book;
import org.apache.commons.collections4.ListUtils;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class BooksCollectionTable {
    private final PdfCellBuilder cellBuilder;
    private final BundleHandler bundles;

    private BooksCollectionTable(AbstractDocumentWriter writer) {
        this.cellBuilder = writer.getCellBuilder();
        this.bundles = writer.getBundles();
    }

    static BooksCollectionTable initFor(AbstractDocumentWriter writer) {
        return new BooksCollectionTable(writer);
    }
    
    void insertInto(Document document) {
        Table table = new Table(new float[]{1, 1, 1, 1, 1, 1, 1, 1})
                .setFixedLayout()
                .setWidthPercent(100);

        table.setDocument(document);
        
        table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.id"))
                .backgroundColorStrike()
                .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.author"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.title"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.genre"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.price"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.pubDate"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.review"))
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.value(bundles.get("report.table.book.type"))
                        .backgroundColorStrike()
                        .build());
        ListUtils.emptyIfNull(BookDAOMock.getAllEntities()).forEach(book -> addRow(table, book));
        table.complete();
    }

    private void addRow(Table table, Book book) {
        table.addCell(cellBuilder.value(book.getId()).build())
                .addCell(cellBuilder.value(book.getAuthor()).build())
                .addCell(cellBuilder.value(book.getTitle()).build())
                .addCell(cellBuilder.value(book.getGenre()).build())
                .addCell(cellBuilder.value(book.getPrice()).right().build())
                .addCell(cellBuilder.value(book.getPubDate()).center().build())
                .addCell(cellBuilder.value(book.getReview()).build())
                .addCell(cellBuilder.value(bundles.get(book.getType())).build());
    }
}
