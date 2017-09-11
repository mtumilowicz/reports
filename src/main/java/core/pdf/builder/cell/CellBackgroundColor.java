package core.pdf.builder.cell;

import com.itextpdf.kernel.color.Color;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellBackgroundColor {
    private Color backgroundColor = Color.LIGHT_GRAY;
    private boolean backgroundColorStrike = false;

    static void backgroundColor(CellBackgroundColor bc, Color backgroundColor) {
        bc.backgroundColor = backgroundColor;
    }

    static void strike(CellBackgroundColor bc) {
        bc.backgroundColorStrike = true;
    }

    static Color prepareBackgroundColor(CellBackgroundColor bc) {
        return bc.backgroundColorStrike ? bc.backgroundColor : null;
    }
}
