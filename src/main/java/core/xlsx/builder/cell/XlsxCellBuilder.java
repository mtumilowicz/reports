package core.xlsx.builder.cell;

import com.google.common.base.Preconditions;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-08.
 */
public class XlsxCellBuilder {
    private Row row;
    private int colCount;
    
    private CellText cellText = new CellText();
    private CellFormat cellFormat = new CellFormat();
    private CellBorder cellBorder = new CellBorder();
    private CellForegroundColor cellForegroundColor = new CellForegroundColor();
    
    public XlsxCellBuilder row(Row row, int colCount, String value) {
        this.row = row;
        this.colCount = colCount;
        cellText.value(value);
        
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

    public void setDefaultFontSize(int size) {
        Preconditions.checkArgument(size > 0);
        cellText.setDefaultFontSize(size);
    }
    
    public XlsxCellBuilder dataFormat(Short format) {
        cellFormat.setDataFormat(format);
        
        return this;
    }
    
    public XlsxCellBuilder cellType(CellType type) {
        cellFormat.setCellType(type);
        
        return this;
    }

    public XlsxCellBuilder border() {
        cellBorder.border();
        
        return this;
    }
    
    public XlsxCellBuilder noBorder() {
        cellBorder.noBorder();
        
        return this;
    }

    public XlsxCellBuilder foregroundColor(short var1) {
        cellForegroundColor.foregroundColor(var1);

        return this;
    }
    
    public XlsxCellBuilder fillPattern(FillPatternType type) {
        cellForegroundColor.fillPattern(type);
        
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
        cellBorder = new CellBorder();
        cellForegroundColor = new CellForegroundColor();
    }

    private Cell prepareCell() {
        Cell cell = CellUtil.createCell(row, colCount, cellText.getValue());

        prepareText(cell);

        prepareCellFormat(cell);

        prepareCellStyle(cell);
        
        return cell;
    }
    
    private void prepareText(Cell cell) {
        cellText.prepareText(cell);
    }

    private void prepareCellFormat(Cell cell) {
        cellFormat.prepareFormat(cell);
    }
    
    private void prepareCellStyle(Cell cell) {
        cellBorder.prepareBorder(cell);
        cellForegroundColor.prepareForegroundColor(cell);
    }
}
