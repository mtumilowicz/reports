package core.xlsx.builder.cell;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-11-08.
 */
public class XlsxCellBuilder {
    private Cell cell;
    
    private CellDefaults defaults = new CellDefaults();
    private CellText cellText = new CellText();
    private CellFormat cellFormat = new CellFormat();
    private CustomCellStyle cellStyle = new CustomCellStyle();
    
    public XlsxCellBuilder row(Row row, int colCount, String value) {
        cell = CellUtil.createCell(row, colCount, value);
        
        return this;
    }

    public XlsxCellBuilder row(Row row, int colCount, BigDecimal value) {
        cell = CellUtil.createCell(row, colCount, StringUtils.EMPTY);
        cell.setCellValue(value != null ? value.doubleValue() : 0.0);
        cellFormat.setCellType(CellType.NUMERIC);
        
        return this;
    }

    public XlsxCellBuilder row(Row row, int colCount, int value) {
        cell = CellUtil.createCell(row, colCount, StringUtils.EMPTY);
        cell.setCellValue(value);
        cellFormat.setCellType(CellType.NUMERIC);

        return this;
    }

    public XlsxCellBuilder row(Row row, int colCount, Date value, short dataFormat) {
        cell = CellUtil.createCell(row, colCount, StringUtils.EMPTY);
        cell.setCellValue(value);
        dataFormat(dataFormat);

        return this;
    }
    
    public XlsxCellBuilder alignment(HorizontalAlignment alignment) {
        cellText.alignment(alignment);
        
        return this;
    }

    public XlsxCellBuilder singleCellFontSize(int size) {
        Preconditions.checkArgument(size > 0);
        cellText.singleCellFontSize(size);

        return this;
    }
    
    public XlsxCellBuilder dataFormat(Short format) {
        cellFormat.setDataFormat(format);
        
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
        cellStyle.getCellForegroundColor().foregroundColor(color);

        return this;
    }

    public void setDefaultForegroundColor(IndexedColors color) {
        defaults.setBackgroundColor(color);
    }
    
    public XlsxCellBuilder fillPattern(FillPatternType type) {
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
        cellFormat = new CellFormat();
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
