package pdf.utils;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public final class PdfFontsContainer {
    
    private static PdfFont helvetica; 
    
    private PdfFontsContainer() {
    }

    public static PdfFont getHelvetica() {
        PdfFont font;
        try {
            font = PdfFontFactory.createFont(FontConstants.HELVETICA, "Cp1250", true);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load: " + FontConstants.HELVETICA);
        }
        
        return helvetica == null ? helvetica = font : helvetica;
    }
}
