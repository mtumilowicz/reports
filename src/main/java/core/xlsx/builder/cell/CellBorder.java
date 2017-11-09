package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellBorder {
    private boolean border;

    void border() {
        border = true;
    }

    void noBorder() {
        border = false;
    }

    void prepareBorder(Cell cell) {
        if (border) {
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_BOTTOM, BorderStyle.THIN);
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_TOP, BorderStyle.THIN);
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_LEFT, BorderStyle.THIN);
            CellUtil.setCellStyleProperty(cell, CellUtil.BORDER_RIGHT, BorderStyle.THIN);
        }
        
        border = false;
    }
}
