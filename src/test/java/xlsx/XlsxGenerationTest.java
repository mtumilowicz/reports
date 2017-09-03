package xlsx;

import core.builder.GenericBuilder;
import core.xlsx.writer.AbstractXlsxWriter;
import dao.BookDAOMock;
import entity.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;

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
        addBookCollectionSheet(workbook);

        addSummarySheet(workbook);
    }
    
    private void addBookCollectionSheet(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(bundle.get("report.sheet.book.name"));
        setColumnWidthInSheet(sheet);

        int rowCount = 0;

        addBookCollectionSheetTitle(sheet, rowCount);
        
        rowCount++;
        
        addBookCollectionTableHeaders(sheet, rowCount);

        rowCount++;
        
        addBookCollectionTableContent(sheet, rowCount);
    }

    private void addSummarySheet(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(bundle.get("report.sheet.summary.name"));

        int rowCount = 0;

        addSummarySheetTitle(sheet, rowCount);

        rowCount++;

        addSummaryTableHeaders(workbook, sheet, rowCount);

        rowCount++;

        addSummaryTableContent(workbook, sheet, rowCount);

    }
    
    private void addBookCollectionSheetTitle(XSSFSheet sheet, int rowCount) {
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(sheet.createRow(rowCount), 0, bundle.get("report.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
        XSSFFont font = (XSSFFont) titleCell.getSheet().getWorkbook().createFont();
        font.setFontHeight((short) 500);
        CellUtil.setFont(titleCell, font);
    }

    private void addBookCollectionTableContent(XSSFSheet sheet,
                                                      int rowCount) {
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

            CellUtil.createCell(row, columnCount++, bundle.get(book.getType()));
        }
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
            Cell headerCell = CellUtil.createCell(row, columnCount++, bundle.get(header));
            addTableHeaderCell(headerCell);
        }
    }

    private void addSummarySheetTitle(XSSFSheet sheet, int rowCount) {
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(sheet.createRow(rowCount), 0, bundle.get("report.table.summary.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
    }

    private void addSummaryTableHeaders(XSSFWorkbook workbook, XSSFSheet sheet, int rowCount) {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount);
        Cell quantityHeader = CellUtil.createCell(row, columnCount++, bundle.get("report.table.summary.quantity"));
        addTableHeaderCell(quantityHeader);


        Cell valueHeader = CellUtil.createCell(row, columnCount++, bundle.get("report.table.summary.value"));
        addTableHeaderCell(valueHeader);
    }

    private void addSummaryTableContent(XSSFWorkbook workbook, XSSFSheet sheet, int rowCount) {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount++);
        
        Cell quantity = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAOMock.getAllEntities().size()));
        quantity.setCellType(CellType.NUMERIC);
        CellUtil.setAlignment(quantity, HorizontalAlignment.LEFT);

        Cell value = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAOMock.sumPriceOfAllEntities().orElse(BigDecimal.ZERO)));
        value.setCellType(CellType.NUMERIC);
        CellUtil.setCellStyleProperty(value, CellUtil.DATA_FORMAT, format.money());
        CellUtil.setAlignment(value, HorizontalAlignment.RIGHT);
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

    private void setColumnWidthInSheet(Sheet sheet) {
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(7, 3000);
    }
}
