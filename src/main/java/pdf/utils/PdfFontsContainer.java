package pdf.utils;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;
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

    public static PdfFont getHelveticaBold() {
        return get(FontConstants.HELVETICA_BOLD);
    }
    
    private static PdfFont get(String fontName) {
        if (!FONTS.containsKey(fontName)) {
            loadFont(fontName);
        }
        return FONTS.get(fontName);
    }
    
    private static void loadFont(String fontName) {
        PdfFont font;
        try {
            font = PdfFontFactory.createFont(fontName, "Cp1250", true);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load: " + fontName);
        }

        FONTS.put(fontName, font);
    }
}
