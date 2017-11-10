package core.xlsx.builder.cell;

import com.google.common.base.Preconditions;
import core.xlsx.format.XlsxDataFormat;
import core.xlsx.format.XlsxDataFormatType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-11-08.
 */
public class XlsxCellBuilder {
    private Cell cell;
    
    private CellDefaults defaults = new CellDefaults();
    private CellText cellText = new CellText();
    private CellFormat cellFormat;
    private CustomCellStyle cellStyle = new CustomCellStyle();

    public XlsxCellBuilder(XlsxDataFormat dataFormat) {
        cellFormat = new CellFormat(dataFormat);
    }

    public XlsxCellBuilder cell(Row row, int colCount, String value) {
        Preconditions.checkArgument(row != null);
        Preconditions.checkArgument(colCount >= 0);
        
        cell = CellUtil.createCell(row, colCount, value);
        
        return this;
    }

    public XlsxCellBuilder cell(Row row, int colCount, BigDecimal value) {
        Preconditions.checkArgument(row != null);
        Preconditions.checkArgument(colCount >= 0);
        
        cell = CellUtil.createCell(row, colCount, StringUtils.EMPTY);
        cell.setCellValue(value != null ? value.doubleValue() : 0.0);
        cell.setCellType(CellType.NUMERIC);
        
        return this;
    }

    public XlsxCellBuilder cell(Row row, int colCount, Integer value) {
        Preconditions.checkArgument(row != null);
        Preconditions.checkArgument(colCount >= 0);
        
        cell = CellUtil.createCell(row, colCount, StringUtils.EMPTY);
        cell.setCellValue(value);
        cell.setCellType(CellType.NUMERIC);

        return this;
    }

    public XlsxCellBuilder cell(Row row, int colCount, Date value, XlsxDataFormatType type) {
        Preconditions.checkArgument(row != null);
        Preconditions.checkArgument(colCount >= 0);
        
        cell = CellUtil.createCell(row, colCount, StringUtils.EMPTY);
        cell.setCellValue(value);
        dataFormat(type);

        return this;
    }
    
    public XlsxCellBuilder alignment(HorizontalAlignment alignment) {
        Preconditions.checkArgument(alignment != null);
        cellText.alignment(alignment);
        
        return this;
    }

    public XlsxCellBuilder singleCellFontSize(int size) {
        Preconditions.checkArgument(size > 0);
        cellText.singleCellFontSize(size);

        return this;
    }
    
    public XlsxCellBuilder dataFormat(XlsxDataFormatType type) {
        cellFormat.setDataFormat(type);
        
        return this;
    }

    public XlsxCellBuilder border() {
        cellStyle.getCellBorder().border();
        
        return this;
    }
    
    public XlsxCellBuilder noBorder() {
        cellStyle.getCellBorder().noBorder();
        
        return this;
    }

    public XlsxCellBuilder foregroundColor(IndexedColors color) {
        Preconditions.checkArgument(color != null);
        cellStyle.getCellForegroundColor().foregroundColor(color);
        fillPattern(FillPatternType.SOLID_FOREGROUND);

        return this;
    }

    public void setDefaultForegroundColor(IndexedColors color) {
        Preconditions.checkArgument(color != null);
        defaults.setBackgroundColor(color);
    }
    
    public XlsxCellBuilder fillPattern(FillPatternType type) {
        Preconditions.checkArgument(type != null);
        cellStyle.getCellForegroundColor().fillPattern(type);
        
        return this;
    }
    
    public Cell build() {
        Cell cell = prepareCell();
        
        resetFields();
        
        return cell;
    }

    private void resetFields() {
        cellText = new CellText();
        cellFormat = cellFormat.newInstanceWithTheSameFormat();
        cellStyle = new CustomCellStyle();
    }

    private Cell prepareCell() {
        prepareText(cell);

        prepareCellFormat(cell);

        prepareCellStyle(cell, defaults);
        
        return cell;
    }
    
    private void prepareText(Cell cell) {
        cellText.prepareText(cell);
    }

    private void prepareCellFormat(Cell cell) {
        cellFormat.prepareFormat(cell);
    }
    
    private void prepareCellStyle(Cell cell, CellDefaults defaults) {
        cellStyle.prepareCellStyle(cell, defaults);
    }
}
