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

    void text(String text) {
        this.text = StringUtils.isNotEmpty(text) ? text : EMPTY_STRING_VALUE;
    }

    void textAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
    }

    void singleCellFontSize(int singleCellFontSize) {
        this.singleCellFontSize = singleCellFontSize;
    }

    void defaultFontSize(int defaultFontSize) {
        this.defaultFontSize = defaultFontSize;
    }

    void bold() {
        this.bold = true;
    }

    Paragraph prepareParagraph() {
        Paragraph paragraph = new Paragraph(prepareText());
        paragraph.setTextAlignment(textAlignment);

        return paragraph;
    }

    private Text prepareText() {
        Text text = new Text(this.text);
        if (bold) {
            text.setBold();
        }

        text.setFontSize(singleCellFontSize > 0 ? singleCellFontSize : defaultFontSize);

        return text;
    }

}
