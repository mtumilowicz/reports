package pdf.books;

import com.itextpdf.layout.Document;
import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;
import core.pdf.writer.AbstractDocumentWriter;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class AbstractInsertablePdfTable {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private final BundleHandler bundles;

    public AbstractInsertablePdfTable(AbstractDocumentWriter writer) {
        this.bundles = writer.getBundles();
    }
    
    abstract void insertInto(Document document);

    public PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    public BundleHandler getBundles() {
        return bundles;
    }
}
