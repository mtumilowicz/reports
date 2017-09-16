package xlsx;

import core.xlsx.writer.AbstractXlsxWriter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xlsx.books.sheet.first.BookCollectionSheet;
import xlsx.books.sheet.second.SummarySheet;

/**
 * Created by mtumilowicz on 2017-08-14.
 */
public class XlsxGenerationTest extends AbstractXlsxWriter {
    
    private static final String DEST = "output/xlsx/test.xlsx";
    
    public static void main(String[] args) {
        new XlsxGenerationTest().save(DEST);
    }

    @Override
    public void prepare(XSSFWorkbook workbook) {
        new BookCollectionSheet(bundles, workbook).create();

        new SummarySheet(bundles, workbook).create();
    }
}
