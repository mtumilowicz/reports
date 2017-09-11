package core.pdf.writer;

import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class AbstractInsertablePdfTable implements InsertablePdfElement {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private final BundleHandler bundles;

    public AbstractInsertablePdfTable(BundleHandler bundles) {
        this.bundles = bundles;
    }

    protected PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    protected BundleHandler getBundles() {
        return bundles;
    }
}
