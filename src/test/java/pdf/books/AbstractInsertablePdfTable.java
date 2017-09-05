package pdf.books;

import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;
import core.pdf.writer.AbstractDocumentWriter;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class AbstractInsertablePdfTable implements InsertablePdfTable {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private final BundleHandler bundles;

    public AbstractInsertablePdfTable(AbstractDocumentWriter writer) {
        this.bundles = writer.getBundles();
    }

    public PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    public BundleHandler getBundles() {
        return bundles;
    }
}
