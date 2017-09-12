package xlsx.books;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import dao.BookDAOMock;
import entity.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class BookCollectionSheet {
    private BundleHandler bundles = new BundleHandler();
    private XlsxDataFormat format;
    private XSSFWorkbook workbook;

    public BookCollectionSheet(BundleHandler bundles, XlsxDataFormat format, XSSFWorkbook workbook) {
        this.bundles = bundles;
        this.format = format;
        this.workbook = workbook;
    }

    public void create() {
        XSSFSheet sheet = workbook.createSheet(bundles.get("report.sheet.book.name"));
        setColumnWidthInSheet(sheet);

        int rowCount = 0;

        addBookCollectionSheetTitle(sheet, rowCount);

        rowCount++;

        addBookCollectionTableHeaders(sheet, rowCount);

        rowCount++;

        addBookCollectionTableContent(sheet, rowCount);
    }

    private void addBookCollectionSheetTitle(XSSFSheet sheet, int rowCount) {
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(sheet.createRow(rowCount), 0, bundles.get("report.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
        XSSFFont font = (XSSFFont) titleCell.getSheet().getWorkbook().createFont();
        font.setFontHeight((short) 500);
        CellUtil.setFont(titleCell, font);
    }

    private void addBookCollectionTableHeaders(XSSFSheet sheet, int rowCount) {
        String[] booksCollectionHeaders = {
                "report.table.book.id",
                "report.table.book.author",
                "report.table.book.title",
                "report.table.book.genre",
                "report.table.book.price",
                "report.table.book.pubDate",
                "report.table.book.review",
                "report.table.book.type"};

        XSSFRow row = sheet.createRow(rowCount);

        int columnCount = 0;

        for (String header : booksCollectionHeaders) {
            Cell headerCell = CellUtil.createCell(row, columnCount++, bundles.get(header));
            addTableHeaderCell(headerCell);
        }
    }

    private void addTableHeaderCell(Cell cell) {
        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        GenericBuilder.of(() -> style)
                .with(CellStyle::setBorderLeft, BorderStyle.THIN)
                .with(CellStyle::setBorderBottom, BorderStyle.THIN)
                .with(CellStyle::setFillForegroundColor, IndexedColors.GREY_40_PERCENT.getIndex())
                .with(CellStyle::setFillPattern, FillPatternType.SOLID_FOREGROUND).build();
        cell.setCellStyle(style);
    }

    private void addBookCollectionTableContent(XSSFSheet sheet, int rowCount) {
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

            CellUtil.createCell(row, columnCount++, bundles.get(book.getType()));
        }
    }

    private void setColumnWidthInSheet(Sheet sheet) {
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(7, 3000);
    }
}
