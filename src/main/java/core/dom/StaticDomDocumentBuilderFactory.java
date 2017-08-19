package core.dom;

import com.google.common.base.Preconditions;
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
    
    public static final Document create(String name) {
        Preconditions.checkArgument(name != null);
        
        Document document = getBuilder().newDocument();
        document.appendChild(document.createElement(name));

        return Objects.requireNonNull(document);
    }
    
    public static final Document parse(String path) {
        Preconditions.checkArgument(path != null);
        try {
            return Objects.requireNonNull(getBuilder().parse(path));
        } catch (SAXException | IOException e) {
            throw new StaticDocumentBuilderFactoryException(e.getLocalizedMessage());
        }
    }
    
    private static DocumentBuilder getBuilder() {
        try {
            return Objects.requireNonNull(factory.newDocumentBuilder());
        } catch (ParserConfigurationException e) {
            throw new StaticDocumentBuilderFactoryException(e.getLocalizedMessage());
        }
    }

    private static final class StaticDocumentBuilderFactoryException extends RuntimeException {
        private StaticDocumentBuilderFactoryException(String message) {
            super(message);
        }
    }
}
