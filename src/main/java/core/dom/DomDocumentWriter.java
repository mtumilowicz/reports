package core.dom;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import org.w3c.dom.Document;

/**
 * Created by mtumilowicz on 2017-06-01.
 */
public interface DomDocumentWriter {

    Document prepare();
    
    void print();

    OutputFormat getFormat();
    
}
