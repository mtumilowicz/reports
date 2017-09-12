package core.pdf.builder.cell;

import com.itextpdf.layout.Style;
import core.pdf.utils.PdfFontsContainer;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellStyle {
    private Style defaultStyle = new Style().setFont(PdfFontsContainer.getHelvetica());
    private Style singleCellStyle = null;

    void singleCellStyle(Style singleCellStyle) {
        this.singleCellStyle = singleCellStyle;
    }

    Style prepareStyle() {
        return singleCellStyle != null ? singleCellStyle : defaultStyle;
    }

}