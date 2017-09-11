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
        
        add(new HarvardEmblem(), document);

        add(new SpacingTable(), document);

        add(new ReportHeader(), document);

        add(new SpacingTable(), document);

        add(new BooksCollectionTable(), document);

        add(new SpacingTable(), document);

        add(new SummaryBooksCollectionTable(), document);
    }
}
