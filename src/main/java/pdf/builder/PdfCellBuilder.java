package pdf.builder;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import core.BundleHandler;
import core.EnumBundleHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import pdf.utils.PdfStyle;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public class PdfCellBuilder {
    
    private PdfStyle defaultStyle = PdfStyle.HELVETICA;
    private final BundleHandler bundles;
    
    private final static FastDateFormat SDF = FastDateFormat.getInstance("yyyy-MM-dd");
    private final static DecimalFormat DF = new DecimalFormat("#.00");
    private final static String EMPTY_STRING_VALUE = "-";
    private String text = EMPTY_STRING_VALUE;
    
    private Style singleCellStyle = null;
    private boolean bold = false;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private boolean backgroundColorStrike = false;
    private boolean useSingleCellStyle = false;
    private TextAlignment textAlignment = TextAlignment.LEFT;
    private int singleFontSize = 0;
    private int defaultFontSize = 14;
    
    public PdfCellBuilder(String pdfClassName) {
        bundles = new BundleHandler(pdfClassName);
    }

    public PdfCellBuilder value(String text) {
        this.text = StringUtils.isNotEmpty(text) ? text : EMPTY_STRING_VALUE;
        
        return this;
    }
    
    public PdfCellBuilder bundle(String key) {
        this.text = StringUtils.isNotEmpty(text) ? bundles.get(key) : EMPTY_STRING_VALUE;

        return this;
    }

    public PdfCellBuilder value(BigDecimal value) {
        return value(value == null ? StringUtils.EMPTY : DF.format(value));
    }

    public PdfCellBuilder value(Date value) {
        return value(value == null ? StringUtils.EMPTY : SDF.format(value));
    }

    public PdfCellBuilder bold() {
        this.bold = true;
        
        return this;
    }

    public PdfCellBuilder singleCellStyle(Style style) {
        this.singleCellStyle = style;
        
        return this;
    }

    public PdfCellBuilder backgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        
        return this;
    }

    public PdfCellBuilder backgroundColorStrike() {
        this.backgroundColorStrike = true;
        
        return this;
    }

    public PdfCellBuilder singleFontSize(int fontSize) {
        this.singleFontSize = fontSize;
        
        return this;
    }

    public void defaultFontSize(int defaultFontSize) {
        this.defaultFontSize = defaultFontSize;
    }

    public PdfCellBuilder center() {
        return setTextAlignment(TextAlignment.CENTER);
    }
    
    public PdfCellBuilder right() {
        return setTextAlignment(TextAlignment.RIGHT);
    }

    private PdfCellBuilder setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
        
        return this;
    }

    public Cell build() {
        Text text = new Text(this.text);
        if (bold) {
            text.setBold();
        }
        
        text.setFontSize(singleFontSize > 0 ? singleFontSize : defaultFontSize);
        
        Style style = defaultStyle.getStyle();
        if (useSingleCellStyle) {
            Objects.requireNonNull(singleCellStyle);
            style = singleCellStyle;
        }

        Paragraph paragraph = new Paragraph(text);
        paragraph.setTextAlignment(textAlignment);

        Cell cell = new Cell().add(paragraph).addStyle(style);

        if (backgroundColorStrike) {
            cell.setBackgroundColor(backgroundColor);
        }
        
        resetFields();
        
        return cell;
    }

    private void resetFields() {
        this.bold = false;
        this.backgroundColorStrike = false;
        this.useSingleCellStyle = false;
        this.textAlignment = TextAlignment.LEFT;
        this.singleFontSize = 0;
    }

    public <T extends Enum<T>> PdfCellBuilder value(T e) {
        this.text = EnumBundleHandler.get(e);
        
        return this;
    }
}
