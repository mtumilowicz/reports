package core.pdf.writer;

import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.builder.cell.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public abstract class InsertablePdfTable {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private final BundleHandler bundles;

    protected InsertablePdfTable(BundleHandler bundles) {
        this.bundles = bundles;
    }

    protected abstract Table get();

    protected PdfCellBuilder getCellBuilder() {
        return cellBuilder;
    }

    protected BundleHandler getBundles() {
        return bundles;
    }
}
