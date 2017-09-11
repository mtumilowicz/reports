package core.pdf.builder.cell;

import com.itextpdf.layout.Style;
import core.pdf.utils.PdfFontsContainer;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellStyle {
    private Style defaultStyle = new Style().setFont(PdfFontsContainer.getHelvetica());
    private Style singleCellStyle = null;

    static void singleCellStyle(CellStyle cs, Style singleCellStyle) {
        cs.singleCellStyle = singleCellStyle;
    }

    static Style prepareStyle(CellStyle cs) {
        return cs.singleCellStyle != null ? cs.singleCellStyle : cs.defaultStyle;
    }

}