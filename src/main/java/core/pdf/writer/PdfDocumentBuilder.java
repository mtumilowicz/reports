package core.pdf.writer;

import com.itextpdf.layout.Document;

/**
 * Created by mtumilowicz on 2017-09-18.
 */
public class PdfDocumentBuilder {
    private final Document document;

    public PdfDocumentBuilder(Document document) {
        this.document = document;
    }

    public PdfDocumentBuilder add(InsertablePdfTable table) {
        document.add(table.get());

        return this;
    }

    public PdfDocumentBuilder add(InsertablePdfImage image) {
        document.add(image.getScaledFor(document));

        return this;
    }
}
