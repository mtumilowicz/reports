package core.pdf.builder.cell;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
final class CellText {
    private final static String EMPTY_STRING_VALUE = "-";
    
    private String text = EMPTY_STRING_VALUE;
    private TextAlignment textAlignment = TextAlignment.LEFT;
    private int singleCellFontSize = 0;
    private int defaultFontSize = 14;
    private boolean bold = false;

    static void text(CellText ct, String text) {
        ct.text = StringUtils.isNotEmpty(text) ? text : EMPTY_STRING_VALUE;
    }

    static void textAlignment(CellText ct, TextAlignment textAlignment) {
        ct.textAlignment = textAlignment;
    }

    static void singleCellFontSize(CellText ct, int singleCellFontSize) {
        ct.singleCellFontSize = singleCellFontSize;
    }

    static void defaultFontSize(CellText ct, int defaultFontSize) {
        ct.defaultFontSize = defaultFontSize;
    }

    static void bold(CellText ct) {
        ct.bold = true;
    }

    static Paragraph prepareParagraph(CellText ct) {
        Paragraph paragraph = new Paragraph(prepareText(ct));
        paragraph.setTextAlignment(ct.textAlignment);

        return paragraph;
    }

    private static Text prepareText(CellText ct) {
        Text text = new Text(ct.text);
        if (ct.bold) {
            text.setBold();
        }

        text.setFontSize(ct.singleCellFontSize > 0 ? ct.singleCellFontSize : ct.defaultFontSize);

        return text;
    }

}
