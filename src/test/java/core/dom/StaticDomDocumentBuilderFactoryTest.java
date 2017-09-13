package core.dom;

import org.junit.Test;

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
        assertXMLEqual(ControlXmlDocument.byName(sameTestNodeName),
                StaticDomDocumentBuilderFactory.create(sameTestNodeName));
    }

    @Test
    public void createDocumentWithDifferentRootNameAsControlDocument() {
        assertXMLNotEqual(ControlXmlDocument.byName(differentTestNodeName), 
                StaticDomDocumentBuilderFactory.create(testNodeName));
    }
    
    @Test
    public void createDocumentSameAsImportedFromFile() {
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                StaticDomDocumentBuilderFactory.create(testNodeName));
    }

    @Test
    public void createDocumentDifferentAsImportedFromFile() {
        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                StaticDomDocumentBuilderFactory.create(differentTestNodeName));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDocumentWithNullPath() {
        StaticDomDocumentBuilderFactory.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDocumentWithEmptyPath() {
        StaticDomDocumentBuilderFactory.parse("");
    }


    @Test
    public void parseDocumentSameAsImportedFromFile() {
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                StaticDomDocumentBuilderFactory.parse("src/test/resources/onlyRoot.xml"));
    }

    @Test
    public void parseDocumentDifferentAsImportedFromFile() {
        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                StaticDomDocumentBuilderFactory.parse("src/test/resources/onlyRoot2.xml"));
    }
}
