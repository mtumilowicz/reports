package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellUtil;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellForegroundColor {
    private IndexedColors foregroundColor;
    private FillPatternType fillPattern;

    void foregroundColor(IndexedColors foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    void fillPattern(FillPatternType fillPattern) {
        this.fillPattern = fillPattern;
    }
    
    void prepareForegroundColor(Cell cell, CellDefaults defaults) {
            CellUtil.setCellStyleProperty(cell, 
                    CellUtil.FILL_FOREGROUND_COLOR, 
                    foregroundColor != null ? foregroundColor.index : defaults.getBackgroundColor());

        if (fillPattern != null) {
            CellUtil.setCellStyleProperty(cell, CellUtil.FILL_PATTERN, fillPattern);
        }
    }
}
