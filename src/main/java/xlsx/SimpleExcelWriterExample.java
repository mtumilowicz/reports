package xlsx;

import dao.BookDAO;
import entity.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-08-14.
 */
public class SimpleExcelWriterExample {

    private static final String DEST = "output/xlsx/JavaBooks.xlsx";

    public static void main(String[] args) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(DEST)) {

            addBookCollectionSheet(workbook);

            addSummarySheet(workbook);
            
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void addSummarySheet(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Summary");
        int rowCount = 0;

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        XSSFRow row = sheet.createRow(rowCount++);

        Cell summary = CellUtil.createCell(row, 0, "Summary");
        CellUtil.setAlignment(summary, HorizontalAlignment.CENTER);
        
        rowCount++;
        
        addSummaryTableHeaders(workbook, sheet, rowCount++);

        addSummaryTableContent(workbook, sheet, rowCount++);
        
       
    }
    
    private static void addSummaryTableHeaders(XSSFWorkbook workbook, XSSFSheet sheet, int rowCount) {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount++);
        Cell quantityHeader = CellUtil.createCell(row, columnCount++, "Ilość");
        addTableHeaderCell(workbook, quantityHeader);


        Cell valueHeader = CellUtil.createCell(row, columnCount++, "Wartość");
        addTableHeaderCell(workbook, valueHeader);
    }
    
    private static void addSummaryTableContent(XSSFWorkbook workbook, XSSFSheet sheet, int rowCount) {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount++);
        
        DataFormat format = workbook.createDataFormat();
        Cell quantity = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAO.getAllEntities().size()));
        quantity.setCellType(CellType.NUMERIC);
        CellUtil.setAlignment(quantity, HorizontalAlignment.LEFT);

        Cell value = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAO.sumPriceOfAllEntities().get()));
        value.setCellType(CellType.NUMERIC);
        CellUtil.setCellStyleProperty(value, CellUtil.DATA_FORMAT, format.getFormat("#.00"));
        CellUtil.setAlignment(value, HorizontalAlignment.RIGHT);
    }
    
    private static void addBookCollectionSheet(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Books");
        setColumnWidthInSheet(sheet);

        int rowCount = 0;

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell bookCollection = CellUtil.createCell(sheet.createRow(rowCount++), 0, "Books Collection");
        CellUtil.setAlignment(bookCollection, HorizontalAlignment.CENTER);
        
        addBookCollectionTableHeaders(workbook, sheet, rowCount);

        rowCount++;
        
        addBookCollectionTableContent(workbook, sheet, rowCount);
    }

    private static void addBookCollectionTableContent(XSSFWorkbook workbook,
                                                      XSSFSheet sheet,
                                                      int rowCount) {
        DataFormat format = workbook.createDataFormat();
        for (Book book : BookDAO.getAllEntities()) {
            XSSFRow row = sheet.createRow(++rowCount);
            int columnCount = 0;

            CellUtil.createCell(row, columnCount++, book.getId());

            CellUtil.createCell(row, columnCount++, book.getAuthor());

            CellUtil.createCell(row, columnCount++, book.getTitle());

            CellUtil.createCell(row, columnCount++, book.getGenre());

            Cell cell1 = CellUtil.createCell(row, columnCount++, String.valueOf(book.getPrice()));
            CellUtil.setAlignment(cell1, HorizontalAlignment.RIGHT);
            CellUtil.setCellStyleProperty(cell1, CellUtil.DATA_FORMAT, format.getFormat("#.00"));
            cell1.setCellType(CellType.NUMERIC);


            Cell cell2 = CellUtil.createCell(row, columnCount++, "");
            cell2.setCellValue(book.getPubDate());
            CellUtil.setCellStyleProperty(cell2, CellUtil.DATA_FORMAT, format.getFormat("YYYY-MM-DD"));
            CellUtil.setAlignment(cell2, HorizontalAlignment.CENTER);

            CellUtil.createCell(row, columnCount++, book.getReview());

            CellUtil.createCell(row, columnCount++, book.getType().toString());

        }
    }

    private static void addBookCollectionTableHeaders(XSSFWorkbook workbook, XSSFSheet sheet, int rowCount) {
        String[] booksCollectionHeaders = {"ID", "Author", "Title", "Genre", 
                "Price", "PubDate", "Review", "Type"};

        XSSFRow row = sheet.createRow(++rowCount);
        
        int columnCount = 0;
        
        for (String header : booksCollectionHeaders) {
            Cell headerCell = CellUtil.createCell(row, columnCount++, header);
            addTableHeaderCell(workbook, headerCell);
        }
    }
    
    private static void addTableHeaderCell(XSSFWorkbook workbook, Cell cell) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }

    private static void setColumnWidthInSheet(Sheet sheet) {
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(7, 3000);
    }
}
