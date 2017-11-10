package core.xlsx.builder.cell;

import core.xlsx.format.XlsxDataFormat;
import core.xlsx.format.XlsxDataFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellFormat {
    private XlsxDataFormatType type;
    private final XlsxDataFormat format;

    CellFormat(XlsxDataFormat format) {
        this.format = format;
    }

    void setDataFormat(XlsxDataFormatType type) {
        this.type = type;
    }
    
    void prepareFormat(Cell cell) {
        prepareDataFormat(cell);
    }

    private void prepareDataFormat(Cell cell) {
        if (type != null) {
            CellUtil.setCellStyleProperty(cell, CellUtil.DATA_FORMAT, format.of(type));
        }
    }

    CellFormat newInstanceWithTheSameFormat() {
        return new CellFormat(format);
    }
}
