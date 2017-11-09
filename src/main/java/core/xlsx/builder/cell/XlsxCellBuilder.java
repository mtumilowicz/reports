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
    
    private Short dataFormat;
    private CellType cellType;
    
    private boolean border;
    private short foregroundColor;
    private FillPatternType fillPattern;
    
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
        this.dataFormat = format;
        
        return this;
    }
    
    public XlsxCellBuilder cellType(CellType type) {
        this.cellType = type;
        
        return this;
    }

    public XlsxCellBuilder border() {
        this.border = true;
        
        return this;
    }
    
    public XlsxCellBuilder noBorder() {
        this.border = false;
        
        return this;
    }

    public XlsxCellBuilder foregroundColor(short var1) {
        this.foregroundColor = var1;

        return this;
    }
    
    public XlsxCellBuilder fillPattern(FillPatternType type) {
        this.fillPattern = type;
        
        return this;
    }
    
    public Cell build() {
        Cell cell = CellUtil.createCell(row, colCount, value);

        prepareAlignment(cell);

        singleCellFontSize(cell);

        prepareDataFormat(cell);

        prepareCellType(cell);
        
        prepareCellStyle(cell);
        
        return cell;
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
    
    private void prepareDataFormat(Cell cell) {
        if (dataFormat != null) {
            CellUtil.setCellStyleProperty(cell, CellUtil.DATA_FORMAT, dataFormat);

            dataFormat=null;
        }
    }
    
    private void prepareCellType(Cell cell) {
        if (cellType != null) {
            cell.setCellType(cellType);

            cellType=null;
        }
    }
    
    private void prepareCellStyle(Cell cell) {
        if (border) {
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_BOTTOM, BorderStyle.THIN);
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_TOP, BorderStyle.THIN);
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_LEFT, BorderStyle.THIN);
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_RIGHT, BorderStyle.THIN);

            border = false;
        }

        if (foregroundColor > 0) {
            CellUtil.setCellStyleProperty(cell, CellUtil.FILL_FOREGROUND_COLOR, foregroundColor);

            foregroundColor=0;
        }

        if (fillPattern != null) {
            CellUtil.setCellStyleProperty(cell, CellUtil.FILL_PATTERN, fillPattern);

            fillPattern = null;
        }
    }
}
