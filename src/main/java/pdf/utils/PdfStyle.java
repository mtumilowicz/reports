package pdf.utils;

import com.itextpdf.layout.Style;
import com.itextpdf.layout.property.TextAlignment;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public enum PdfStyle {
    HELVETICA(
            new Style().setFont(PdfFontsContainer.getHelvetica())),
    HELVETICA_CENTER(
            new Style().setFont(PdfFontsContainer.getHelvetica()).setTextAlignment(TextAlignment.CENTER)),
    HELVETICA_BOLD(
            new Style().setFont(PdfFontsContainer.getHelveticaBold())),
    HELVETICA_BOLD_CENTER(
            new Style().setFont(PdfFontsContainer.getHelveticaBold()).setTextAlignment(TextAlignment.CENTER));

    private final Style style;

    PdfStyle(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }
}
