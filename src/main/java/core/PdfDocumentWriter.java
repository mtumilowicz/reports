package core;

import com.itextpdf.layout.Document;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public interface PdfDocumentWriter {
    Document prepare();

    void save();
}
