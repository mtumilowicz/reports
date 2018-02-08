package core.xml.builder.straight;

import core.dom.ControlXmlDocument;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLNotEqual;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderStraightImplTest {

    @Before
    public void setUp() {
        XMLUnit.setNormalizeWhitespace(true);
    }
    
    @Test
    public void xmlDocumentBuilderStraightImplConstructor_name() {
        XmlDocumentBuilderImpl xmlDocumentBuilder = new XmlDocumentBuilderImpl("test");
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                xmlDocumentBuilder.getDocument());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestPositive() {
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"),
                new StraightReportTypeXmlWriterShowcase("node0").prepare());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestNegative() {
        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDocWrongNode.xml"),
                new StraightReportTypeXmlWriterShowcase("node0").prepare());
    }
}
