package core.pdf.builder.cell;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellBorder {
    private boolean border = true;

    static void border(CellBorder cb) {
        cb.border = true;
    }

    static void noBorder(CellBorder cb) {
        cb.border = false;
    }

    static Border prepareBorder(CellBorder cb) {
        return cb.border ? new SolidBorder(1) : Border.NO_BORDER;
    }
}
