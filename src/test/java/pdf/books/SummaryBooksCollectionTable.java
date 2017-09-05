package pdf.books;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.builder.PdfCellBuilder;
import core.pdf.writer.AbstractDocumentWriter;
import dao.BookDAOMock;

import java.math.BigDecimal;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class SummaryBooksCollectionTable {
    
    private final PdfCellBuilder cellBuilder;
    private final BundleHandler bundles;

    private SummaryBooksCollectionTable(PdfCellBuilder cellBuilder, BundleHandler bundles) {
        this.cellBuilder = cellBuilder;
        this.bundles = bundles;
    }

    static SummaryBooksCollectionTable initFor(AbstractDocumentWriter writer) {
        return new SummaryBooksCollectionTable(writer.getCellBuilder(), writer.getBundles());
    }

    void insertInto(Document doc) {
        Table table = new Table(new float[]{1, 1});
        table.setDocument(doc);

        table.setWidthPercent(40)
                .addHeaderCell(
                        cellBuilder
                                .value(bundles.get("report.table.summary.header"))
                                .noBorder()
                                .singleCellFontSize(20)
                                .build())
                .addHeaderCell(PdfCellBuilder.EMPTY_CELL)
                .addCell(
                        cellBuilder
                                .value(bundles.get("report.table.summary.quantity"))
                                .build())
                .addCell(
                        cellBuilder
                                .value(bundles.get("report.table.summary.value"))
                                .build())
                .addCell(
                        cellBuilder
                                .value(BookDAOMock.getAllEntities().size())
                                .build())
                .addCell(
                        cellBuilder
                                .value(BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO))
                                .build())
                .complete();
    }
}
