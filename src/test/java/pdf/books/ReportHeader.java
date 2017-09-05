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

    private final PdfCellBuilder cellBuilder;
    private final BundleHandler bundles;

    private ReportHeader(PdfCellBuilder cellBuilder, BundleHandler bundles) {
        this.cellBuilder = cellBuilder;
        this.bundles = bundles;
    }

    static ReportHeader initFor(AbstractDocumentWriter writer) {
        return new ReportHeader(writer.getCellBuilder(), writer.getBundles());
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
