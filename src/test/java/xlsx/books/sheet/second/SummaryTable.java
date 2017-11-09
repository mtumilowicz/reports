package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import dao.BookDAOMock;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public class SummaryTable extends InsertableXlsContent {
    SummaryTable(BundleHandler bundles, Sheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    @Override
    public void create() {
        new SummaryTableHeaders().create();

        int rowCount = getRowCount();
        rowCount++;

        int columnCount = 0;
        Row row = getSheet().createRow(rowCount);

        getCellBuilder().row(row, columnCount++, BookDAOMock.getAllEntities().size())
                .alignment(HorizontalAlignment.LEFT)
                .build();

        getCellBuilder().row(row, columnCount, BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO))
                .dataFormat(getFormat().money())
                .alignment(HorizontalAlignment.RIGHT)
                .build();
    }

    private class SummaryTableHeaders {

        void create() {
            int columnCount = 0;

            Row row = getSheet().createRow(getRowCount());

            getCellBuilder().setDefaultForegroundColor(IndexedColors.GREY_40_PERCENT);
            getCellBuilder().row(row, columnCount++, getBundles().get("report.table.summary.quantity"))
                    .border()
                    .fillPattern(FillPatternType.SOLID_FOREGROUND)
                    .build();


            getCellBuilder().row(row, columnCount, getBundles().get("report.table.summary.value"))
                    .border()
                    .fillPattern(FillPatternType.SOLID_FOREGROUND)
                    .build();
        }
    }
}
