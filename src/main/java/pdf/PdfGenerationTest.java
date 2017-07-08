package pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import core.BundleHandler;
import dao.BookDAO;
import entity.Book;
import pdf.builder.PdfCellBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class PdfGenerationTest {
    public static final String DEST = "output/pdf/test.pdf";
    
    private final BundleHandler bundles = new BundleHandler(getClass().getSimpleName());

    public static final PdfCellBuilder cellBuilder = new PdfCellBuilder();

    public static void main(String args[]) throws IOException {
        new PdfGenerationTest().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        try (Document document = new Document(pdf, PageSize.A4.rotate())) {
            document.setMargins(20, 20, 20, 20);

            Table header = new Table(new float[]{1});
            header.setDocument(document);

            header.setPaddingBottom(100)
                    .setWidthPercent(100)
                    .addHeaderCell(
                            cellBuilder
                                    .value(bundles.get("report.header"))
                                    .center()
                                    .singleFontSize(20)
                                    .build())
                    .complete();

            Table table = new Table(new float[]{1, 1, 1, 1, 1, 1, 1, 1});
            table.setFixedLayout();
            table.setWidthPercent(100);
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.id"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.author"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.title"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.genre"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.price"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.pubDate"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.review"))
                    .backgroundColorStrike()
                    .build());
            table.addHeaderCell(cellBuilder.value(bundles.get("report.table.book.type"))
                    .backgroundColorStrike()
                    .build());
            List<Book> allEntities = BookDAO.getAllEntities();
            allEntities.stream().forEach(book -> process(table, book));
            document.add(table);

        }
    }

    public void process(Table table, Book book) {
        table.addCell(cellBuilder.value(book.getId()).build());
        table.addCell(cellBuilder.value(book.getAuthor()).build());
        table.addCell(cellBuilder.value(book.getTitle()).build());
        table.addCell(cellBuilder.value(book.getGenre()).build());
        table.addCell(cellBuilder.value(book.getPrice()).build());
        table.addCell(cellBuilder.value(book.getPubDate()).center().build());
        table.addCell(cellBuilder.value(book.getReview()).build());
        table.addCell(cellBuilder.value(book.getType()).build());
    }
}
