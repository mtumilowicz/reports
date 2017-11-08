package core.xlsx.builder.cell;

import com.google.common.base.Preconditions;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-08.
 */
public class XlsxCellBuilder {
    private final Sheet sheet;
    private Row row;
    private String value;
    private HorizontalAlignment alignment;
    private int singleCellFontSize;
    private int setDefaultFontSize;
    private int colCount;
    private Short dataFormat;
    private CellType cellType;
    private CellStyle cellStyle;


    public XlsxCellBuilder(Sheet sheet) {
        this.sheet = sheet;
    }
    
    public XlsxCellBuilder row(Row row, int colCount, String value) {
        this.row = row;
        this.colCount = colCount;
        this.value = value;
        
        return this;
    }
    
    public XlsxCellBuilder alignments(HorizontalAlignment alignment) {
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
    
    public XlsxCellBuilder cellStyle(CellStyle style) {
        this.cellStyle = style;
        
        return this;
    }
    
    public Cell build() {
        Cell cell = CellUtil.createCell(row, colCount, value);
        
        if (alignment != null) {
            CellUtil.setAlignment(cell, alignment);
            alignment = null;
        }
        
        if (singleCellFontSize > 0) {
            Font font = cell.getSheet().getWorkbook().createFont();
            font.setFontHeight((short) singleCellFontSize);
            CellUtil.setFont(cell, font);

            singleCellFontSize=0;
        }
        
        if (dataFormat != null) {
            CellUtil.setCellStyleProperty(cell, CellUtil.DATA_FORMAT, dataFormat);

            dataFormat=null;
        }
        
        if (cellType != null) {
            cell.setCellType(cellType);

            cellType=null;
        }
        
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);

            cellStyle=null;
        }
        
        return cell;
    }
}
