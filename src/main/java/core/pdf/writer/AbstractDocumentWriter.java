package core.pdf.writer;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import core.bundle.BundleHandler;
import core.writer.DocumentWriter;

import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-07-12.
 */
public abstract class AbstractDocumentWriter implements DocumentWriter {
    
    private final BundleHandler bundles = new BundleHandler(getClass());

    public void save(String dest) {
        try {
            createPdf(dest);
        } catch (IOException e) {
            System.out.println("Cannot save pdf: " + e.getLocalizedMessage());
        }
    }

    protected void add(InsertablePdfTable table, Document document) {
        document.add(table.withBundles(bundles).get());
    }

    protected void add(InsertablePdfImage image, Document document) {
        document.add(image.getScaledFor(document));
    }

    protected abstract void prepare(Document document);

    private void createPdf(String dest) throws IOException {
        try (PdfWriter writer = new PdfWriter(dest);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4.rotate())) {

            document.setMargins(20, 20, 20, 20);
            prepare(document);
        }
    }
}
