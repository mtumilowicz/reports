package core.dom;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLNotEqual;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public class StaticDomDocumentBuilderFactoryTest {

    private static final String testNodeName = "test";
    private static final String sameTestNodeName = "test";
    private static final String differentTestNodeName = "test23";
    
    @Test(expected = IllegalArgumentException.class)
    public void createDocumentWithNullName() {
        StaticDomDocumentBuilderFactory.create(null);
    }
    
    @Test
    public void createDocumentWithNotNullName() {
        assertNotNull(StaticDomDocumentBuilderFactory.create(testNodeName));
    }
    
    @Test
    public void createDocumentWithSameRootNameAsControlDocument() {
        assertXMLEqual(StaticDomDocumentBuilderFactory.create(sameTestNodeName), getControlDocument(sameTestNodeName));
    }

    @Test
    public void createDocumentWithDifferentRootNameAsControlDocument() {
        assertXMLNotEqual(StaticDomDocumentBuilderFactory.create(testNodeName), getControlDocument(differentTestNodeName));
    }
    
    @Test
    public void createDocumentSameAsImportedFromFile() {
        assertXMLEqual(StaticDomDocumentBuilderFactory.create(testNodeName), 
                getControlDocumentFromFile("src/test/resources/onlyRoot.xml"));
    }

    @Test
    public void createDocumentDifferentAsImportedFromFile() {
        assertXMLNotEqual(StaticDomDocumentBuilderFactory.create(differentTestNodeName),
                getControlDocumentFromFile("src/test/resources/onlyRoot.xml"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDocumentWithNullPath() {
        StaticDomDocumentBuilderFactory.parse(null);
    }

    @Test
    public void parseDocumentSameAsImportedFromFile() {
        assertXMLEqual(StaticDomDocumentBuilderFactory.parse("src/test/resources/onlyRoot.xml"),
                getControlDocumentFromFile("src/test/resources/onlyRoot.xml"));
    }

    @Test
    public void parseDocumentDifferentAsImportedFromFile() {
        assertXMLNotEqual(StaticDomDocumentBuilderFactory.parse("src/test/resources/onlyRoot2.xml"),
                getControlDocumentFromFile("src/test/resources/onlyRoot.xml"));
    }
    
    private Document getControlDocument(String name) { 
        assertNotNull(name);
        
        Document control = createControlDocument();
        assertNotNull(control);
        
        Element controlNode = prepareControlElement(control, name);
        assertNotNull(controlNode);
        
        control.appendChild(controlNode);
        assertNotNull(control);
        
        return control;
    }
    
    private Document createControlDocument() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            assertNotNull(e);
        }
        return null;
    }

    private Document getControlDocumentFromFile(String path) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            assertNotNull(e);
        }
        return null;
    }

    private Element prepareControlElement(Document doc, String name) {
        assertNotNull(name);
        
        Element element = doc.createElement(name);
        assertNotNull(element);
        
        return element;
    }
}
