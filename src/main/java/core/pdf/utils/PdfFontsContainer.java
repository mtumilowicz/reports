package core.pdf.utils;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public final class PdfFontsContainer {
    
    private final static Map<String, PdfFont> FONTS = new HashMap<>(); 
    
    private PdfFontsContainer() {
    }

    public static PdfFont getHelvetica() {
        return get(FontConstants.HELVETICA);
    }
    
    private static PdfFont get(String fontName) {
        if (!FONTS.containsKey(fontName)) {
            loadFont(fontName);
        }
        return FONTS.get(fontName);
    }
    
    private static void loadFont(String fontName) {
        FONTS.put(fontName, PdfFontsFactory.createFont(fontName));
    }
}
