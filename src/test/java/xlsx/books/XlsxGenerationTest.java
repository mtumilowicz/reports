package xlsx.books;

import core.xlsx.writer.AbstractXlsxWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import xlsx.books.sheet.first.BookCollectionSheet;
import xlsx.books.sheet.second.SummarySheet;

/**
 * Created by mtumilowicz on 2017-08-14.
 */
public class XlsxGenerationTest extends AbstractXlsxWriter {
    
    private static final String DEST = "output/xlsx/test.xlsx";
    
    @Test
    public void generate() {
        new XlsxGenerationTest().save(DEST);
    }

    @Override
    public void prepare(Workbook workbook) {
        add(new BookCollectionSheet(bundles, workbook));

        add(new SummarySheet(bundles, workbook));
    }
}
