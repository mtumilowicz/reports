package xlsx.books;

import core.builder.GenericBuilder;
import core.bundle.BundleHandler;
import core.xlsx.format.XlsxDataFormat;
import dao.BookDAOMock;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
public class SummarySheet {

    private BundleHandler bundles = new BundleHandler();
    private XlsxDataFormat format;
    private XSSFWorkbook workbook;

    public SummarySheet(BundleHandler bundles, XlsxDataFormat format, XSSFWorkbook workbook) {
        this.bundles = bundles;
        this.format = format;
        this.workbook = workbook;
    }

    public void create() {
        XSSFSheet sheet = workbook.createSheet(bundles.get("report.sheet.summary.name"));

        int rowCount = 0;

        addSummarySheetTitle(sheet, rowCount);

        rowCount++;

        addSummaryTableHeaders(sheet, rowCount);

        rowCount++;

        addSummaryTableContent(sheet, rowCount);

    }

    private void addSummarySheetTitle(XSSFSheet sheet, int rowCount) {
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        Cell titleCell = CellUtil.createCell(sheet.createRow(rowCount), 0, bundles.get("report.table.summary.header"));
        CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
    }

    private void addSummaryTableHeaders(XSSFSheet sheet, int rowCount) {
        int columnCount = 0;
        XSSFRow row = sheet.createRow(rowCount);
        Cell quantityHeader = CellUtil.createCell(row, columnCount++, bundles.get("report.table.summary.quantity"));
        addTableHeaderCell(quantityHeader);


        Cell valueHeader = CellUtil.createCell(row, columnCount++, bundles.get("report.table.summary.value"));
        addTableHeaderCell(valueHeader);
    }

    private void addSummaryTableContent(XSSFSheet sheet, int rowCount) {
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
}
