package core.pdf.builder.cell;

import com.google.common.base.Preconditions;
import com.itextpdf.kernel.color.Color;
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

    private CellDefaults defaults = new CellDefaults();
    private CellBorder cellBorder = new CellBorder();
    private CellStyle cellStyle = new CellStyle();
    private CellText cellText = new CellText();
    private CellBackgroundColor cellBackgroundColor = new CellBackgroundColor();
    
    public PdfCellBuilder value(String text) {
        cellText.text(text);

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
        cellText.bold();

        return this;
    }

    public PdfCellBuilder defaultBackgroundColor() {
        cellBackgroundColor.useDefaultBackgroundColor();

        return this;
    }

    public PdfCellBuilder backgroundColor(Color backgroundColor) {
        Preconditions.checkArgument(backgroundColor != null);
        cellBackgroundColor.backgroundColor(backgroundColor);

        return this;
    }

    public PdfCellBuilder singleCellFontSize(int fontSize) {
        Preconditions.checkArgument(fontSize > 0);
        cellText.singleCellFontSize(fontSize);

        return this;
    }

    public void withDefaultFontSize(int defaultFontSize) {
        Preconditions.checkArgument(defaultFontSize > 0);
        defaults.setDefaultFontSize(defaultFontSize);
    }

    public PdfCellBuilder center() {
        return setTextAlignment(TextAlignment.CENTER);
    }

    public PdfCellBuilder right() {
        return setTextAlignment(TextAlignment.RIGHT);
    }

    private PdfCellBuilder setTextAlignment(TextAlignment textAlignment) {
        Preconditions.checkArgument(textAlignment != null);
        cellText.textAlignment(textAlignment);

        return this;
    }

    public PdfCellBuilder border() {
        cellBorder.border();

        return this;
    }

    public PdfCellBuilder noBorder() {
        cellBorder.noBorder();

        return this;
    }

    public Cell build() {
        Cell cell = prepareCell();

        resetFields();

        return cell;
    }
    
    private Cell prepareCell() {
        return new Cell()
                .add(cellText.prepareParagraph(defaults))
                .addStyle(cellStyle.prepareStyle())
                .setBorder(cellBorder.prepareBorder())
                .setBackgroundColor(cellBackgroundColor.prepareBackgroundColor());
    }

    private void resetFields() {
        this.cellText = new CellText();
        this.cellBackgroundColor = new CellBackgroundColor();
        this.cellStyle = new CellStyle();
        this.cellBorder = new CellBorder();
    }
}
