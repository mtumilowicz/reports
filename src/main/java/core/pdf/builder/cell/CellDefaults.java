package core.pdf.builder.cell;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Style;
import core.pdf.utils.PdfFontsContainer;

/**
 * Created by mtumilowicz on 2017-09-12.
 */
final class CellDefaults {
    private int fontSize = 14;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private Style style = new Style().setFont(PdfFontsContainer.getHelvetica());

    int getFontSize() {
        return fontSize;
    }

    void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    Color getBackgroundColor() {
        return backgroundColor;
    }

    void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    Style getStyle() {
        return style;
    }

    void setStyle(Style style) {
        this.style = style;
    }
}
