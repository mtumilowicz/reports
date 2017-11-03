package pdf.builder.cell;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.Background;
import core.pdf.builder.cell.PdfCellBuilder;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Created by mtumilowicz on 2017-11-03.
 */
public class PdfCellBuilderTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void nullBackgroundColor() {
        new PdfCellBuilder().backgroundColor(null);
    }

    @Test
    public void nonNullBackgroundColor() {
        Color testColor = Color.BLUE;
        Cell cell = new PdfCellBuilder().backgroundColor(testColor).build();
        Object background = cell.getProperty(6);
        assertEquals(Background.class, background.getClass());
        assertEquals(((Background) background).getColor(), testColor);
    }

    @Test
    public void nullDefaultBackgroundColor() {
        new PdfCellBuilder().setDefaultBackgroundColor(null);
    }

    @Test
    public void nonNullDefaultBackgroundColor() {
        Color testColor = Color.BLUE;
        
        PdfCellBuilder cellBuilder = new PdfCellBuilder();
        cellBuilder.setDefaultBackgroundColor(testColor);
        
        Object property = Objects.requireNonNull(cellBuilder.build().getProperty(6));
        assertEquals(Background.class, property.getClass());
        assertEquals(((Background) property).getColor(), testColor);
    }

    @Test
    public void defaultBackgroundColorHasNoInfluenceOnCellWhenNotNullBackgroundColor() {
        Color testDefaultColor = Color.BLUE;
        Color testColor = Color.RED;
        
        PdfCellBuilder cellBuilder = new PdfCellBuilder();
        cellBuilder.setDefaultBackgroundColor(testDefaultColor);
        cellBuilder.backgroundColor(testColor);
        
        Object property = Objects.requireNonNull(cellBuilder.build().getProperty(6));
        assertEquals(Background.class, property.getClass());
        assertEquals(((Background) property).getColor(), testColor);
    }

    @Test
    public void afterBuildDefaultBackgroundColorIsLoaded() {
        Color testDefaultColor = Color.BLUE;
        Color testColor = Color.RED;

        PdfCellBuilder cellBuilder = new PdfCellBuilder();
        cellBuilder.setDefaultBackgroundColor(testDefaultColor);
        cellBuilder.backgroundColor(testColor);
        
        cellBuilder.build();
        
        Object property = Objects.requireNonNull(cellBuilder.build().getProperty(6));
        assertEquals(Background.class, property.getClass());
        assertEquals(((Background) property).getColor(), testDefaultColor);
    }
    
    @Test
    public void noBorder() {
        Cell cell = new PdfCellBuilder().noBorder().build();
        
        Object property = cell.getProperty(9);
        assertEquals(property, Border.NO_BORDER);
    }

    @Test
    public void border() {
        Cell cell = new PdfCellBuilder().border().build();

        Object property = Objects.requireNonNull(cell.getProperty(9));
        assertEquals(SolidBorder.class, property.getClass());
        assertEquals(((Border) property).getType(), Border.SOLID);
    }
}
