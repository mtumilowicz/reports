package core;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-07.
 */
public class StaticDomDocumentBuilderFactory {
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
    public static Document createDocument(String name) {
        Objects.requireNonNull(name);

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new StaticDocumentBuilderFactoryException(e.getLocalizedMessage());
        }
        Document document = builder.newDocument();
        document.appendChild(document.createElement(name));

        return document;
    }
    
    public static Document parse(String path) {
        Document document;
        try {
            document = factory.newDocumentBuilder().parse(path);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new StaticDocumentBuilderFactoryException(e.getLocalizedMessage());
        }
        
        return document;
    }

    private static class StaticDocumentBuilderFactoryException extends RuntimeException {
        private StaticDocumentBuilderFactoryException(String message) {
            super(message);
        }
    }
}
