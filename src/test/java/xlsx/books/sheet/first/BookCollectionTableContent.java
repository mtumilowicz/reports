package xlsx.books.sheet.first;

import core.bundle.BundleHandler;
import core.xlsx.writer.InsertableXlsTableContent;
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
final class BookCollectionTableContent extends InsertableXlsTableContent {

    BookCollectionTableContent(BundleHandler bundles, XSSFSheet sheet, int rowCount) {
        super(bundles, sheet, rowCount);
    }

    void create() {
        new BookCollectionTableHeaders(getBundles(), getSheet(), getRowCount()).create();

        int rowCount = getRowCount();
        rowCount++;

        for (Book book : BookDAOMock.getAllEntities()) {
            XSSFRow row = getSheet().createRow(rowCount++);
            int columnCount = 0;

            CellUtil.createCell(row, columnCount++, book.getId());

            CellUtil.createCell(row, columnCount++, book.getAuthor());

            CellUtil.createCell(row, columnCount++, book.getTitle());

            CellUtil.createCell(row, columnCount++, book.getGenre());

            Cell cell1 = CellUtil.createCell(row, columnCount++, String.valueOf(book.getPrice()));
            CellUtil.setAlignment(cell1, HorizontalAlignment.RIGHT);
            CellUtil.setCellStyleProperty(cell1, CellUtil.DATA_FORMAT, getFormat().money());
            cell1.setCellType(CellType.NUMERIC);


            Cell cell2 = CellUtil.createCell(row, columnCount++, "");
            cell2.setCellValue(book.getPubDate());
            CellUtil.setCellStyleProperty(cell2, CellUtil.DATA_FORMAT, getFormat().dateHours());
            CellUtil.setAlignment(cell2, HorizontalAlignment.CENTER);

            CellUtil.createCell(row, columnCount++, book.getReview());

            CellUtil.createCell(row, columnCount, getBundles().get(book.getType()));
        }
    }
}
