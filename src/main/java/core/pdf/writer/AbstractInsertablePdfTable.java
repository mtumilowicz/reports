package core.pdf.writer;

import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class AbstractInsertablePdfTable implements InsertablePdfElement {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private final BundleHandler bundles;

    public AbstractInsertablePdfTable(AbstractDocumentWriter writer) {
        this.bundles = writer.getBundles();
    }

    protected PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    protected BundleHandler getBundles() {
        return bundles;
    }
}
