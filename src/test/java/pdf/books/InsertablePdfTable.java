package pdf.books;

import com.itextpdf.layout.Document;
import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public interface InsertablePdfTable {

    void insertInto(Document document);
    
    PdfCellBuilder getCellBuilder();

    BundleHandler getBundles();
}
