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
        assertXMLEqual(StaticDomDocumentBuilderFactory.create(sameTestNodeName),
                ControlXmlDocument.byName(sameTestNodeName));
    }

    @Test
    public void createDocumentWithDifferentRootNameAsControlDocument() {
        assertXMLNotEqual(StaticDomDocumentBuilderFactory.create(testNodeName), 
                ControlXmlDocument.byName(differentTestNodeName));
    }
    
    @Test
    public void createDocumentSameAsImportedFromFile() {
        assertXMLEqual(StaticDomDocumentBuilderFactory.create(testNodeName),
                ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"));
    }

    @Test
    public void createDocumentDifferentAsImportedFromFile() {
        assertXMLNotEqual(StaticDomDocumentBuilderFactory.create(differentTestNodeName),
                ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"));
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
        assertXMLEqual(StaticDomDocumentBuilderFactory.parse("src/test/resources/onlyRoot.xml"),
                ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"));
    }

    @Test
    public void parseDocumentDifferentAsImportedFromFile() {
        assertXMLNotEqual(StaticDomDocumentBuilderFactory.parse("src/test/resources/onlyRoot2.xml"),
                ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"));
    }
}
