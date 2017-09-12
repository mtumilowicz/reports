package core.pdf.writer;

import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.builder.cell.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class InsertablePdfTable {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private BundleHandler bundles = new BundleHandler();

    public abstract Table get();

    protected PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    protected BundleHandler getBundles() {
        return bundles;
    }

    InsertablePdfTable withBundles(BundleHandler bundles) {
        this.bundles = bundles;

        return this;
    }
}
