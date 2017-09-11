package core.pdf.writer;

import core.bundle.BundleHandler;
import core.pdf.builder.cell.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class AbstractInsertablePdfTable implements InsertablePdfTable {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private BundleHandler bundles = new BundleHandler();

    protected PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    public AbstractInsertablePdfTable withBundles(BundleHandler bundles) {
        this.bundles = bundles;
        
        return this;
    }

    protected BundleHandler getBundles() {
        return bundles;
    }
}
