package core.xml.builder.straight;

import core.dom.ControlXmlDocument;
import core.xml.builder.XmlDocumentBuilderChainImpl;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLNotEqual;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderStraightImpl {

    @Before
    public void setUp() {
        XMLUnit.setNormalizeWhitespace(true);
    }
    
    @Test
    public void xmlDocumentBuilderChainImplConstructor_name() {
        XmlDocumentBuilderChainImpl xmlDocumentBuilderChain = new XmlDocumentBuilderChainImpl("test");
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                xmlDocumentBuilderChain.getDocument());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestSuccess() {
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"),
                new StraightReportTypeXmlWriterShowcase("node0").prepare());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestFail() {
        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDocWrongNode.xml"),
                new StraightReportTypeXmlWriterShowcase("node0").prepare());
    }
}