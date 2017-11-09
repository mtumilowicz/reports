package core.xlsx.builder.cell;

import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
final class CellDefaults {
    private IndexedColors backgroundColor = IndexedColors.AUTOMATIC;

    void setBackgroundColor(IndexedColors backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    IndexedColors getBackgroundColor() {
        return backgroundColor;
    }
}
