package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import dao.BookDAOMock;
import entity.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class BookCollectionTableContent {
    private BundleHandler bundles = new BundleHandler();
    private XSSFSheet sheet;
    private XlsxDataFormat format;
    private int rowCount;

    BookCollectionTableContent(BundleHandler bundles, XSSFSheet sheet, XlsxDataFormat format, int rowCount) {
        this.bundles = bundles;
        this.sheet = sheet;
        this.format = format;
        this.rowCount = rowCount;
    }

    void create() {
        for (Book book : BookDAOMock.getAllEntities()) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;

            CellUtil.createCell(row, columnCount++, book.getId());

            CellUtil.createCell(row, columnCount++, book.getAuthor());

            CellUtil.createCell(row, columnCount++, book.getTitle());

            CellUtil.createCell(row, columnCount++, book.getGenre());

            Cell cell1 = CellUtil.createCell(row, columnCount++, String.valueOf(book.getPrice()));
            CellUtil.setAlignment(cell1, HorizontalAlignment.RIGHT);
            CellUtil.setCellStyleProperty(cell1, CellUtil.DATA_FORMAT, format.money());
            cell1.setCellType(CellType.NUMERIC);


            Cell cell2 = CellUtil.createCell(row, columnCount++, "");
            cell2.setCellValue(book.getPubDate());
            CellUtil.setCellStyleProperty(cell2, CellUtil.DATA_FORMAT, format.dateHours());
            CellUtil.setAlignment(cell2, HorizontalAlignment.CENTER);

            CellUtil.createCell(row, columnCount++, book.getReview());

            CellUtil.createCell(row, columnCount, bundles.get(book.getType()));
        }
    }
}
