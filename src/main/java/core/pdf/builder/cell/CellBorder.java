package core.pdf.builder.cell;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellBorder {
    private boolean border = true;

    void border() {
        border = true;
    }

    void noBorder() {
        border = false;
    }

    Border prepareBorder() {
        return border ? new SolidBorder(1) : Border.NO_BORDER;
    }
}
