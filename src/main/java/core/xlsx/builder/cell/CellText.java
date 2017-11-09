package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellText {
    private HorizontalAlignment alignment;
    private int singleCellFontSize;

    void alignment(HorizontalAlignment alignment) {
        this.alignment = alignment;
    }

    void singleCellFontSize(int singleCellFontSize) {
        this.singleCellFontSize = singleCellFontSize;
    }

    void prepareText(Cell cell) {
        prepareAlignment(cell);

        singleCellFontSize(cell);
    }

    private void prepareAlignment(Cell cell) {
        if (alignment != null) {
            CellUtil.setAlignment(cell, alignment);
        }
    }

    private void singleCellFontSize(Cell cell) {
        if (singleCellFontSize > 0) {
            Font font = cell.getSheet().getWorkbook().createFont();
            font.setFontHeight((short) singleCellFontSize);
            CellUtil.setFont(cell, font);
        }
    }
}
