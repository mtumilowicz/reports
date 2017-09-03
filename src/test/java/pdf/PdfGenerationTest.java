package pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import core.pdf.writer.AbstractDocumentWriter;
import core.pdf.builder.ImageBuilder;
import core.pdf.builder.PdfCellBuilder;
import dao.BookDAOMock;
import entity.Book;
import org.apache.commons.collections4.ListUtils;

import java.math.BigDecimal;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class PdfGenerationTest extends AbstractDocumentWriter {
    private static final String DEST = "output/pdf/test.pdf";

    public static void main(String args[]) {
        new PdfGenerationTest().save(DEST);
    }

    @Override
    protected void prepare(Document document) {
        Image icon = ImageBuilder.Factory.get("src/main/resources/harvard.png")
                .widthAndHeight(100, 100)
                .position(document.getLeftMargin(), 
                        PageSize.A4.rotate().getHeight() - document.getTopMargin() - 100)
                .build();
        
        document.add(icon);

        addSpacingTable(document);

        addSheetHeader(document);

        addSpacingTable(document);

        addBooksCollectionTable(document);

        addSpacingTable(document);
        
        addSummaryBooksCollectionTable(document, BookDAOMock.getAllEntities().size(),
                BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO));
    }

    private void addSummaryBooksCollectionTable(Document doc, int quantity, BigDecimal value) {
        Table table = new Table(new float[]{1, 1});
        table.setDocument(doc);

        table.setWidthPercent(40)
                .addHeaderCell(
                        cellBuilder
                                .value(bundles.get("report.table.summary.header"))
                                .noBorder()
                                .singleCellFontSize(20)
                                .build())
                .addHeaderCell(PdfCellBuilder.EMPTY_CELL)
                .addCell(
                        cellBuilder
                                .value(bundles.get("report.table.summary.quantity"))
                                .build())
                .addCell(
                        cellBuilder
                                .value(bundles.get("report.table.summary.value"))
                                .build())
                .addCell(
                        cellBuilder
                                .value(quantity)
                                .build())
                .addCell(
                        cellBuilder
                                .value(value)
                                .build())
                .complete();
    }

    private void addSheetHeader(Document doc) {
        Table table = new Table(new float[]{1});
        table.setDocument(doc);

        table.setWidthPercent(100)
                .addHeaderCell(
                        cellBuilder
                                .value(bundles.get("report.header"))
                                .center()
                                .noBorder()
                                .singleCellFontSize(20)
                                .build())
                .complete();
    }

    private void addSpacingTable(Document doc) {
        Table table = new Table(new float[]{1});
        table.setDocument(doc);

        table.setWidthPercent(100)
                .setHeight(50)
                .addCell(PdfCellBuilder.EMPTY_CELL)
                .complete();
    }

    private void addBooksCollectionTable(Document doc) {
        Table table = new Table(new float[]{1, 1, 1, 1, 1, 1, 1, 1})
                .setFixedLayout()
                .setWidthPercent(100);
        table.setDocument(doc);
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
