package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellFormat {
    private Short dataFormat;
    private CellType cellType;

    void setDataFormat(Short dataFormat) {
        this.dataFormat = dataFormat;
    }

    void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
    
    void prepareFormat(Cell cell) {
        prepareDataFormat(cell);

        prepareCellType(cell);
    }

    private void prepareDataFormat(Cell cell) {
        if (dataFormat != null) {
            CellUtil.setCellStyleProperty(cell, CellUtil.DATA_FORMAT, dataFormat);
        }
    }

    private void prepareCellType(Cell cell) {
        if (cellType != null) {
            cell.setCellType(cellType);
        }
    }
}
