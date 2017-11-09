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

        getCellBuilder().row(row, columnCount++, String.valueOf(BookDAOMock.getAllEntities().size()))
                .alignment(HorizontalAlignment.LEFT)
                .cellType(CellType.NUMERIC)
                .build();

        getCellBuilder().row(row, columnCount, String.valueOf(BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO)))
                .cellType(CellType.NUMERIC)
                .dataFormat(getFormat().money())
                .alignment(HorizontalAlignment.RIGHT)
                .build();
    }

    private class SummaryTableHeaders {

        void create() {
            int columnCount = 0;

            Row row = getSheet().createRow(getRowCount());

            getCellBuilder().row(row, columnCount++, getBundles().get("report.table.summary.quantity"))
                    .border()
                    .fillPattern(FillPatternType.SOLID_FOREGROUND)
                    .foregroundColor(IndexedColors.GREY_40_PERCENT.getIndex())
                    .build();


            getCellBuilder().row(row, columnCount, getBundles().get("report.table.summary.value"))
                    .border()
                    .fillPattern(FillPatternType.SOLID_FOREGROUND)
                    .foregroundColor(IndexedColors.GREY_40_PERCENT.getIndex())
                    .build();
        }
    }
}
