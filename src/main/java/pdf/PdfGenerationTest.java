package pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import core.AbstractPdfDocumentWriter;
import dao.BookDAO;
import entity.Book;
import pdf.builder.PdfCellBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class PdfGenerationTest extends AbstractPdfDocumentWriter {
    public static final String DEST = "output/pdf/test.pdf";

    public final PdfCellBuilder cellBuilder = new PdfCellBuilder(getClass().getSimpleName());

    public static void main(String args[]) throws IOException {
        new PdfGenerationTest().save(DEST);
    }

    @Override
    public void prepare(Document document) {
        Image icon = null;
        try {
            icon = new Image(ImageDataFactory.create("src/main/resources/harvard.png")).scaleToFit(100, 100);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        icon.setFixedPosition(document.getLeftMargin(), PageSize.A4.rotate().getHeight() - document.getTopMargin() - icon.getImageScaledHeight());
        document.add(icon);

        addSpacingTable(document);

        addSheetHeader(document);

        addSpacingTable(document);

        addBooksCollectionTable(document);

        addSpacingTable(document);


        addSummaryBooksCollectionTable(document, BookDAO.getAllEntities().size(),
                BookDAO.getAllEntities().stream().map((x) -> x.getPrice()).filter(Objects::nonNull).reduce((x, y) -> x.add(y)).get());
    }

    private void addSummaryBooksCollectionTable(Document doc, int quantity, BigDecimal value) {
        Table table = new Table(new float[]{1, 1});
        table.setDocument(doc);

        table.setWidthPercent(40)
                .addHeaderCell(
                        cellBuilder
                                .bundle("report.table.summary.header")
                                .noBorder()
                                .singleCellFontSize(20)
                                .build())
                .addHeaderCell(PdfCellBuilder.EMPTY_CELL)
                .addCell(
                        cellBuilder
                                .bundle("report.table.summary.quantity")
                                .build())
                .addCell(
                        cellBuilder
                                .bundle("report.table.summary.value")
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
                                .bundle("report.header")
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
        table.addHeaderCell(cellBuilder.bundle("report.table.book.id")
                .backgroundColorStrike()
                .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.author")
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.title")
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.genre")
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.price")
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.pubDate")
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.review")
                        .backgroundColorStrike()
                        .build())
                .addHeaderCell(cellBuilder.bundle("report.table.book.type")
                        .backgroundColorStrike()
                        .build());
        BookDAO.getAllEntities().stream().forEach(book -> addRow(table, book));
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
                .addCell(cellBuilder.value(book.getType()).build());
    }
}
