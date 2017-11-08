package xlsx.books.sheet.second;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsContent;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class SummarySheetTitle extends InsertableXlsContent {

    SummarySheetTitle(BundleHandler bundles, Sheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    public void create() {
        getSheet().addMergedRegion(new CellRangeAddress(0,0,0,7));

        getCellBuilder().row(getSheet().createRow(getRowCount()),0, getBundles().get(getHeader()))
                .alignment(HorizontalAlignment.CENTER)
                .build();
    }
    
    private String getHeader() {
        return "report.table.summary.header";
    }
}
