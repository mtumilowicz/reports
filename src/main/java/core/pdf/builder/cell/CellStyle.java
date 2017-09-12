package core.pdf.builder.cell;

import com.itextpdf.layout.Style;
import core.pdf.utils.PdfFontsContainer;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellStyle {
    private Style defaultStyle = new Style().setFont(PdfFontsContainer.getHelvetica());

    Style prepareStyle() {
        return defaultStyle;
    }

}