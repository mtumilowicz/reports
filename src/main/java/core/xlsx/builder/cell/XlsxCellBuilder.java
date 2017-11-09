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
    private String value;
    
    private HorizontalAlignment alignment;
    private int singleCellFontSize;
    private int setDefaultFontSize;
    
    private CellFormat cellFormat = new CellFormat();
    
    private CellBorder cellBorder = new CellBorder();
    private CellForegroundColor cellForegroundColor = new CellForegroundColor();
    
    public XlsxCellBuilder row(Row row, int colCount, String value) {
        this.row = row;
        this.colCount = colCount;
        this.value = value;
        
        return this;
    }
    
    public XlsxCellBuilder alignment(HorizontalAlignment alignment) {
        this.alignment = alignment;
        
        return this;
    }

    public XlsxCellBuilder singleCellFontSize(int size) {
        Preconditions.checkArgument(size > 0);
        this.singleCellFontSize = size;

        return this;
    }

    public void setDefaultFontSize(int size) {
        Preconditions.checkArgument(size > 0);
        this.setDefaultFontSize = size;
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
        Cell cell = CellUtil.createCell(row, colCount, value);

        prepareAlignment(cell);

        singleCellFontSize(cell);

        prepareCellFormat(cell);
        
        prepareCellStyle(cell);
        
        return cell;
    }

    private void prepareCellFormat(Cell cell) {
        cellFormat.prepareFormat(cell);
        
        cellFormat = new CellFormat();
    }

    private void prepareAlignment(Cell cell) {
        if (alignment != null) {
            CellUtil.setAlignment(cell, alignment);
            alignment = null;
        }
    }

    private void singleCellFontSize(Cell cell) {
        if (singleCellFontSize > 0) {
            Font font = cell.getSheet().getWorkbook().createFont();
            font.setFontHeight((short) singleCellFontSize);
            CellUtil.setFont(cell, font);

            singleCellFontSize=0;
        }
    }
    
    private void prepareCellStyle(Cell cell) {
        cellBorder.prepareBorder(cell);
        cellBorder = new CellBorder();
        
        cellForegroundColor.prepareForegroundColor(cell);
        cellForegroundColor = new CellForegroundColor();
    }
}
