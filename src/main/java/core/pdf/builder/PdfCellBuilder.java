package core.pdf.builder;

import com.google.common.base.Preconditions;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import core.date.CoreDateUtils;
import core.pdf.utils.PdfFontsContainer;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public class PdfCellBuilder {

    public final static Cell EMPTY_CELL = new Cell().add("").setBorder(Border.NO_BORDER);
    
    private final static DecimalFormat DF = new DecimalFormat("#.00");
    private final static String EMPTY_STRING_VALUE = "-";

    private Style defaultStyle = new Style().setFont(PdfFontsContainer.getHelvetica());
    private Style singleCellStyle = null;

    private String text = EMPTY_STRING_VALUE;
    private TextAlignment textAlignment = TextAlignment.LEFT;
    private int singleCellFontSize = 0;
    private int defaultFontSize = 14;
    private boolean bold = false;
    private boolean border = true;

    private CellBackgroundColor cellBackgroundColor = new CellBackgroundColor();
    
    public PdfCellBuilder value(String text) {
        this.text = StringUtils.isNotEmpty(text) ? text : EMPTY_STRING_VALUE;

        return this;
    }

    public PdfCellBuilder value(BigDecimal value) {
        return value(value == null ? StringUtils.EMPTY : DF.format(value));
    }

    public PdfCellBuilder value(Date value) {
        return value(value == null ? StringUtils.EMPTY : CoreDateUtils.DATE_ONLY.format(value));
    }

    public PdfCellBuilder value(Integer value) {
        return value(value == null ? StringUtils.EMPTY : value.toString());
    }

    public PdfCellBuilder bold() {
        this.bold = true;

        return this;
    }

    public PdfCellBuilder singleCellStyle(Style style) {
        Preconditions.checkArgument(style != null);
        this.singleCellStyle = style;

        return this;
    }

    public void backgroundColor(Color backgroundColor) {
        Preconditions.checkArgument(backgroundColor != null);
        CellBackgroundColor.backgroundColor(cellBackgroundColor, backgroundColor);
    }

    public PdfCellBuilder backgroundColorStrike() {
        CellBackgroundColor.strike(cellBackgroundColor);

        return this;
    }

    public PdfCellBuilder singleCellFontSize(int fontSize) {
        Preconditions.checkArgument(fontSize > 0);
        this.singleCellFontSize = fontSize;

        return this;
    }

    public void defaultFontSize(int defaultFontSize) {
        Preconditions.checkArgument(defaultFontSize > 0);
        this.defaultFontSize = defaultFontSize;
    }

    public PdfCellBuilder center() {
        return setTextAlignment(TextAlignment.CENTER);
    }

    public PdfCellBuilder right() {
        return setTextAlignment(TextAlignment.RIGHT);
    }

    private PdfCellBuilder setTextAlignment(TextAlignment textAlignment) {
        Preconditions.checkArgument(textAlignment != null);
        this.textAlignment = textAlignment;

        return this;
    }

    public PdfCellBuilder border() {
        this.border = true;

        return this;
    }

    public PdfCellBuilder noBorder() {
        this.border = false;

        return this;
    }

    public Cell build() {
        Cell cell = prepareCell();

        resetFields();

        return cell;
    }
    
    private Text prepareText() {
        Text text = new Text(this.text);
        if (bold) {
            text.setBold();
        }

        text.setFontSize(singleCellFontSize > 0 ? singleCellFontSize : defaultFontSize);
        
        return text;
    }
    
    private Paragraph prepareParagraph() {
        Paragraph paragraph = new Paragraph(prepareText());
        paragraph.setTextAlignment(textAlignment);
        
        return paragraph;
    }
    
    private Style prepareStyle() {
        return singleCellStyle != null ?  singleCellStyle : defaultStyle;
    }
    
    private Border prepareBorder() {
        return border ? new SolidBorder(1) : Border.NO_BORDER;
    }
    
    private Color prepareBackgroundColor() {
        return cellBackgroundColor.backgroundColorStrike ? cellBackgroundColor.backgroundColor : null;
    }
    
    private Cell prepareCell() {
        return new Cell()
                .add(prepareParagraph())
                .addStyle(prepareStyle())
                .setBorder(prepareBorder())
                .setBackgroundColor(prepareBackgroundColor());
    }

    private void resetFields() {
        this.bold = false;
        this.cellBackgroundColor = new CellBackgroundColor();
        this.singleCellStyle = null;
        this.textAlignment = TextAlignment.LEFT;
        this.singleCellFontSize = 0;
        this.border = true;
    }
    
    private static final class CellBackgroundColor {
        private Color backgroundColor = Color.LIGHT_GRAY;
        private boolean backgroundColorStrike = false;

        private static void backgroundColor(CellBackgroundColor bc, Color backgroundColor) {
            bc.backgroundColor = backgroundColor;
        }

        private static void strike(CellBackgroundColor bc) {
            bc.backgroundColorStrike = true;
        }
    }
}
