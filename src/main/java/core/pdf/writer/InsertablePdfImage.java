package core.pdf.writer;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
public interface InsertablePdfImage {
    Image getScaledFor(Document document);
}
