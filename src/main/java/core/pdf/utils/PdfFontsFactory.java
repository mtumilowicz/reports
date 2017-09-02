package core.pdf.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-08-20.
 */
public class PdfFontsFactory {
    public static PdfFont createFont(String fontName) {
        try {
            return PdfFontFactory.createFont(fontName, "Cp1250", true);
        } catch (IOException e) {
            throw new PdfFontFactoryException("Cannot create: " + fontName + ", cause" + e.getLocalizedMessage());
        }
    }
    
    public static final class PdfFontFactoryException extends RuntimeException {
        private PdfFontFactoryException(String message) {
            super(message);
        }
    }
}
