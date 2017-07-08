package pdf.builder;

import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import pdf.utils.PdfFontsContainer;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public class PdfCellBuilder {
    
    private final static Style NORMAL = new Style().setFont(PdfFontsContainer.getHelvetica());
    private final static FastDateFormat SDF = FastDateFormat.getInstance("yyyy-MM-dd");
    private final static DecimalFormat DF = new DecimalFormat("#.00");
    private final static String EMPTY_STRING_VALUE = "-";
    private String text = EMPTY_STRING_VALUE;
    
    public PdfCellBuilder value(String text) {
        this.text = StringUtils.isNotEmpty(text) ? text : EMPTY_STRING_VALUE;
        
        return this;
    }

    public PdfCellBuilder value(BigDecimal value) {
        return value(value == null ? StringUtils.EMPTY : DF.format(value));
    }

    public PdfCellBuilder value(Date value) {
        return value(value == null ? StringUtils.EMPTY : SDF.format(value));
    }

    public Cell build() {
        return new Cell().add(new Paragraph(new Text(text))).addStyle(NORMAL);
    }
}
