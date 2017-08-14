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
        DataFormat format = workbook.createDataFormat();
        XSSFSheet sheet = workbook.createSheet("Summary");
        int rowCount = 0;
        int columnCount = 0;

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        XSSFRow row = sheet.createRow(rowCount++);

        Cell summary = CellUtil.createCell(row, 0, "Summary");
        CellUtil.setAlignment(summary, HorizontalAlignment.CENTER);

        row = sheet.createRow(rowCount++);

        Cell quantityHeader = CellUtil.createCell(row, columnCount++, "Ilość");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        quantityHeader.setCellStyle(style);


        Cell valueHeader = CellUtil.createCell(row, columnCount++, "Wartość");
        valueHeader.setCellStyle(style);

        row = sheet.createRow(rowCount++);
        columnCount = 0;
        
        Cell quantity = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAO.getAllEntities().size()));
        quantity.setCellType(CellType.NUMERIC);
        CellUtil.setAlignment(quantity, HorizontalAlignment.LEFT);
        
        Cell value = CellUtil.createCell(row, columnCount++, String.valueOf(BookDAO.sumPriceOfAllEntities().get()));
        value.setCellType(CellType.NUMERIC);
        CellUtil.setCellStyleProperty(value, CellUtil.DATA_FORMAT, format.getFormat("#.00"));
        CellUtil.setAlignment(value, HorizontalAlignment.RIGHT);
    }
    
    private static void addBookCollectionSheet(XSSFWorkbook workbook) {
        DataFormat format = workbook.createDataFormat();
        XSSFSheet sheet = workbook.createSheet("Books");
        setColumnWidthInSheet(sheet);

        int rowCount = 0;

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Row row = sheet.createRow(rowCount++);

        Cell cell = row.createCell(0);

        cell.setCellValue("Books Collection");

        CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);

        String[] booksCollectionHeaders = {"ID", "Author", "Title", "Genre", "Price", "PubDate", "Review", "Type"};

        row = sheet.createRow(++rowCount);
        int columnCount = 0;

        for (String header : booksCollectionHeaders) {
            Cell cell1 = row.createCell(columnCount++);
            cell1.setCellValue(header);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell1.setCellStyle(style);
        }

        for (Book book : BookDAO.getAllEntities()) {
            row = sheet.createRow(++rowCount);
            columnCount = 0;

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

    private static void setColumnWidthInSheet(Sheet sheet) {
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(7, 3000);
    }

}
