package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellDefaults {
    private int fontSize = 14;
    private IndexedColors backgroundColor = IndexedColors.AUTOMATIC;

    void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    void setBackgroundColor(IndexedColors backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    int getFontSize() {
        return fontSize;
    }

    IndexedColors getBackgroundColor() {
        return backgroundColor;
    }
}
