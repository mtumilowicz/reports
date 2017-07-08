package xml.writer;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import core.DomDocumentWriter;
import org.w3c.dom.Document;
import xml.builder.XmlDocumentBuilder;
import xml.transformer.XmlTransformer;

/**
 * Created by mtumilowicz on 2017-06-01.
 */
public abstract class XmlWriter implements DomDocumentWriter {

    private final XmlDocumentBuilder documentBuilder;

    public XmlWriter(XmlDocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }
    
    public abstract Document prepare();

    public XmlDocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }

    @Override
    public void print() {
        System.out.println(XmlTransformer.document2String(prepare(), getFormat()));
    }

    @Override
    public OutputFormat getFormat() {
        OutputFormat format = new OutputFormat();
        format.setLineWidth(120);
        format.setIndenting(true);
        format.setIndent(2);
        format.setEncoding("UTF-8");
        return format;
    }
}
