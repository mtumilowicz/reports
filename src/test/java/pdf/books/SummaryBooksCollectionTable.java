package pdf.books;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.element.Table;
import core.bundle.BundleHandler;
import core.pdf.builder.cell.PdfCellBuilder;
import core.pdf.writer.InsertablePdfTable;
import dao.BookDAOMock;

import java.math.BigDecimal;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class SummaryBooksCollectionTable extends InsertablePdfTable {

    SummaryBooksCollectionTable(BundleHandler bundles) {
        super(bundles);
    }

    public Table get() {
        getCellBuilder().setDefaultFontSize(20);
        Table table = new Table(new float[]{1, 1});

        table.setWidthPercent(40)
                .addHeaderCell(
                        getCellBuilder()
                                .value(getBundles().get("report.table.summary.header"))
                                .noBorder()
                                .build())
                .addHeaderCell(PdfCellBuilder.getEmptyCell())
                .addCell(
                        getCellBuilder()
                                .value(getBundles().get("report.table.summary.quantity"))
                                .build())
                .addCell(
                        getCellBuilder()
                                .value(getBundles().get("report.table.summary.value"))
                                .build())
                .addCell(
                        getCellBuilder()
                                .value(BookDAOMock.getAllEntities().size())
                                .backgroundColor(Color.CYAN)
                                .build())
                .addCell(
                        getCellBuilder()
                                .value(BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO))
                                .backgroundColor(Color.CYAN)
                                .build());
        
        return table;
    }
}
