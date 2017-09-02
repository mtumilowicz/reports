package core.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mtumilowicz on 2017-09-02.
 */
public class ControlXmlDocument {
    
    public static Document byName(String name) {
        assertNotNull(name);

        Document control = create();
        assertNotNull(control);

        Element controlNode = prepare(control, name);
        assertNotNull(controlNode);

        control.appendChild(controlNode);
        assertNotNull(control);

        return control;
    }

    public static Document byPath(String path) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            assertNotNull(e);
        }
        return null;
    }

    private static Document create() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            assertNotNull(e);
        }
        return null;
    }

    private static Element prepare(Document doc, String name) {
        assertNotNull(name);

        Element element = doc.createElement(name);
        assertNotNull(element);

        return element;
    }
}
