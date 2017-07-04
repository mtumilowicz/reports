package xml.transformer;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-11.
 */
public class XmlTransformer {
    
    public static String document2String(Document doc, OutputFormat format) {
        Objects.requireNonNull(doc);
        Objects.requireNonNull(format);
        
        StringWriter stringOut = new StringWriter();
        XMLSerializer serial = new XMLSerializer(stringOut, format);
        try {
            serial.serialize(doc);
        } catch (IOException e) {
            throw new XmlTransformerException(e.getLocalizedMessage());
        }
        return stringOut.toString();
    }

    public static Document loadXMLFrom(String xml) {
        try {
            return loadXMLFrom(new ByteArrayInputStream(xml.getBytes()));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new XmlTransformerException(e.getLocalizedMessage());
        }
    }

    private static Document loadXMLFrom(InputStream is) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        is.close();

        return doc;
    }

    private static class XmlTransformerException  extends RuntimeException  {
        private XmlTransformerException(String message) {
            super(message);
        }
    }
}
