package pdf.books;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;
import core.pdf.writer.AbstractDocumentWriter;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class ReportHeader {

    private final PdfCellBuilder cellBuilder = new PdfCellBuilder();
    private final BundleHandler bundles;

    private ReportHeader(AbstractDocumentWriter writer) {
        this.bundles = writer.getBundles();
    }

    static ReportHeader initFor(AbstractDocumentWriter writer) {
        return new ReportHeader(writer);
    }

    void insertInto(Document document) {
        Table table = new Table(new float[]{1});

        table.setDocument(document);
        table.setWidthPercent(100)
                .addHeaderCell(
                        cellBuilder
                                .value(bundles.get("report.header"))
                                .center()
                                .noBorder()
                                .singleCellFontSize(20)
                                .build())
                .complete();
    }
}
