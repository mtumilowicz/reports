package xlsx;

import core.BundleHandler;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public abstract class AbstractXlsxWriter {
    protected final BundleHandler bundle = new BundleHandler(this.getClass());
    protected XlsxDataFormat format;
    
    public void save(String dest) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(dest)) {
            initDateFormat(workbook);
            prepare(workbook);
            create(workbook, outputStream);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void create(XSSFWorkbook workbook, FileOutputStream outputStream) throws IOException {
        workbook.write(outputStream);
    }
    
    private void initDateFormat(XSSFWorkbook workbook) {
        format = new XlsxDataFormat(workbook.createDataFormat());
    }

    abstract void prepare(XSSFWorkbook workbook);
}
