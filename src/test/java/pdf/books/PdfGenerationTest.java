package pdf.books;

import com.itextpdf.layout.Document;
import core.pdf.writer.AbstractDocumentWriter;

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
