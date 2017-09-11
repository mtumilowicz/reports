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
                .add(new SpacingTable())
                .add(new ReportHeader())
                .add(new SpacingTable())
                .add(new BooksCollectionTable())
                .add(new SpacingTable())
                .add(new SummaryBooksCollectionTable());
    }
}
