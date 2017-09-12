package core.pdf.builder.cell;

import com.itextpdf.kernel.color.Color;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellBackgroundColor {
    private Color defaultBackgroundColor = Color.LIGHT_GRAY;
    private Color backgroundColor;

    void backgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    Color prepareBackgroundColor() {
        return backgroundColor;
    }

    void useDefaultBackgroundColor() {
        this.backgroundColor = defaultBackgroundColor;
    }
}
