package xlsx.books.sheet.second;

import core.xlsx.writer.InsertableXlsSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class SummarySheet extends InsertableXlsSheet {

    public SummarySheet(XSSFWorkbook workbook) {
        super(workbook);
    }

    public void create() {
        XSSFSheet sheet = getWorkbook().createSheet(getBundles().get("report.sheet.summary.name"));

        int rowCount = 0;

        new SummarySheetTitle(getBundles(), sheet, rowCount).create();

        rowCount++;

        new SummaryTableHeaders(getBundles(), sheet, rowCount).create();

        rowCount++;

        new SummaryTableContent(getBundles(), sheet, rowCount).create();

    }

    @Override
    public void setColumnWidthInSheet(Sheet sheet) {

    }
}
