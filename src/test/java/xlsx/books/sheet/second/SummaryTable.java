package xlsx.books.sheet.second;

import core.builder.GenericBuilder;
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
            CellStyle style = GenericBuilder.of(() -> getSheet().getWorkbook().createCellStyle())
                    .with(CellStyle::setBorderLeft, BorderStyle.THIN)
                    .with(CellStyle::setBorderBottom, BorderStyle.THIN)
                    .with(CellStyle::setFillForegroundColor, IndexedColors.GREY_40_PERCENT.getIndex())
                    .with(CellStyle::setFillPattern, FillPatternType.SOLID_FOREGROUND).build();

            int columnCount = 0;

            Row row = getSheet().createRow(getRowCount());

            getCellBuilder().row(row, columnCount++, getBundles().get("report.table.summary.quantity"))
                    .cellStyle(style)
                    .build();


            getCellBuilder().row(row, columnCount, getBundles().get("report.table.summary.value"))
                    .cellStyle(style)
                    .build();
        }
    }
}
