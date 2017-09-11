package core.pdf.writer;

import com.itextpdf.layout.Document;

/**
 * Created by mtumilowicz on 2017-09-11.
 */
public interface InsertablePdfElement {
    void insertInto(Document document);
}
