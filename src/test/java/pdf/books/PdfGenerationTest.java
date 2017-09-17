package pdf.books;

import com.itextpdf.layout.Document;
import core.pdf.writer.AbstractPdfWriter;
import org.junit.Test;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class PdfGenerationTest extends AbstractPdfWriter {
    private static final String DEST = "output/pdf/test.pdf";

    @Test
    public void generate() {
        new PdfGenerationTest().save(DEST);
    }

    @Override
    protected void prepare(Document document) {

        new PdfDocumentBuilder(document)
                .add(new HarvardEmblem())
                .add(new SpacingTable(bundles))
                .add(new ReportHeader(bundles))
                .add(new SpacingTable(bundles))
                .add(new BooksCollectionTable(bundles))
                .add(new SpacingTable(bundles))
                .add(new SummaryBooksCollectionTable(bundles));
    }
}
