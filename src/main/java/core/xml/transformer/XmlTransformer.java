package core.xml.transformer;

import com.google.common.base.Preconditions;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by mtumilowicz on 2017-06-11.
 */
public class XmlTransformer {
    
    public static String document2String(Document doc, OutputFormat format) {
        Preconditions.checkArgument(doc != null);
        Preconditions.checkArgument(format != null);

        try {
            return serialize(new XMLSerializer(format), doc);
        } catch (IOException e) {
            throw new XmlTransformerException(e.getLocalizedMessage());
        }
    }

    public static Document loadXMLFrom(String xml) {
        Preconditions.checkArgument(xml != null);
        
        try {
            return loadXMLFrom(new ByteArrayInputStream(xml.getBytes()));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new XmlTransformerException(e.getLocalizedMessage());
        }
    }
    
    private static String serialize(XMLSerializer serializer, Document doc) throws IOException {
        Preconditions.checkArgument(serializer != null);
        Preconditions.checkArgument(doc != null);
        
        StringWriter stringOut = new StringWriter();
        serializer.setOutputCharStream(stringOut);
        serializer.serialize(doc);
        return stringOut.toString();
    }

    private static Document loadXMLFrom(InputStream is) throws SAXException, IOException, ParserConfigurationException {
        Preconditions.checkArgument(is != null);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        return factory.newDocumentBuilder().parse(is);
    }

    private static class XmlTransformerException extends RuntimeException  {
        private XmlTransformerException(String message) {
            super(message);
        }
    }
}
