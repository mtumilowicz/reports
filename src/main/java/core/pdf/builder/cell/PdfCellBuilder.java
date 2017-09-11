package core.pdf.builder.cell;

import com.google.common.base.Preconditions;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import core.date.CoreDateUtils;
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

    private CellBorder cellBorder = new CellBorder();
    private CellStyle cellStyle = new CellStyle();
    private CellText cellText = new CellText();
    private CellBackgroundColor cellBackgroundColor = new CellBackgroundColor();
    
    public PdfCellBuilder value(String text) {
        CellText.text(cellText, text);

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
        CellText.bold(cellText);

        return this;
    }

    public PdfCellBuilder singleCellStyle(Style style) {
        Preconditions.checkArgument(style != null);
        CellStyle.singleCellStyle(cellStyle, style);

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
        CellText.singleCellFontSize(cellText, fontSize);

        return this;
    }

    public void defaultFontSize(int defaultFontSize) {
        Preconditions.checkArgument(defaultFontSize > 0);
        CellText.defaultFontSize(cellText, defaultFontSize);
    }

    public PdfCellBuilder center() {
        return setTextAlignment(TextAlignment.CENTER);
    }

    public PdfCellBuilder right() {
        return setTextAlignment(TextAlignment.RIGHT);
    }

    private PdfCellBuilder setTextAlignment(TextAlignment textAlignment) {
        Preconditions.checkArgument(textAlignment != null);
        CellText.textAlignment(cellText, textAlignment);

        return this;
    }

    public PdfCellBuilder border() {
        CellBorder.border(cellBorder);

        return this;
    }

    public PdfCellBuilder noBorder() {
        CellBorder.noBorder(cellBorder);

        return this;
    }

    public Cell build() {
        Cell cell = prepareCell();

        resetFields();

        return cell;
    }
    
    private Cell prepareCell() {
        return new Cell()
                .add(CellText.prepareParagraph(cellText))
                .addStyle(CellStyle.prepareStyle(cellStyle))
                .setBorder(CellBorder.prepareBorder(cellBorder))
                .setBackgroundColor(CellBackgroundColor.prepareBackgroundColor(cellBackgroundColor));
    }

    private void resetFields() {
        this.cellText = new CellText();
        this.cellBackgroundColor = new CellBackgroundColor();
        this.cellStyle = new CellStyle();
        this.cellBorder = new CellBorder();
    }
}
