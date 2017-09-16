package xlsx.books.sheet.first;

import core.xlsx.writer.InsertableXlsSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class BookCollectionSheet extends InsertableXlsSheet {

    public BookCollectionSheet(XSSFWorkbook workbook) {
        super(workbook);
    }

    @Override
    public void create() {
        XSSFSheet sheet = getWorkbook().createSheet(getBundles().get("report.sheet.book.name"));
        setColumnWidthInSheet(sheet);

        int rowCount = 0;

        new BookCollectionSheetTitle(getBundles(), sheet, rowCount).create();

        rowCount++;
        
        new BookCollectionTableContent(getBundles(), sheet, rowCount).create();
    }

    @Override
    public void setColumnWidthInSheet(Sheet sheet) {
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(7, 3000);
    }
}
