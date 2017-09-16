package core.xlsx.writer;

import core.bundle.BundleHandler;
import core.writer.DocumentWriter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public abstract class AbstractXlsxWriter implements DocumentWriter {
    protected final BundleHandler bundles = new BundleHandler(this.getClass());
    
    public void save(String dest) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(dest)) {
            prepare(workbook);
            create(workbook, outputStream);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void create(XSSFWorkbook workbook, FileOutputStream outputStream) throws IOException {
        workbook.write(outputStream);
    }
    public abstract void prepare(XSSFWorkbook workbook);
}
