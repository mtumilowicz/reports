package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CustomCellStyle {
    private CellBorder cellBorder = new CellBorder();
    private CellForegroundColor cellForegroundColor = new CellForegroundColor();

    void cellBorder(CellBorder cellBorder) {
        this.cellBorder = cellBorder;
    }

    void cellForegroundColor(CellForegroundColor cellForegroundColor) {
        this.cellForegroundColor = cellForegroundColor;
    }

    CellBorder getCellBorder() {
        return cellBorder;
    }

    CellForegroundColor getCellForegroundColor() {
        return cellForegroundColor;
    }

    void prepareCellStyle(Cell cell) {
        cellBorder.prepareBorder(cell);
        cellForegroundColor.prepareForegroundColor(cell);
    }
}
