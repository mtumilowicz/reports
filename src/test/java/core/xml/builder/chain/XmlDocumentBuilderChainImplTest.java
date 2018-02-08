package core.xml.builder.chain;

import core.dom.ControlXmlDocument;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLNotEqual;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderChainImplTest {

    @Before
    public void setUp() {
        XMLUnit.setNormalizeWhitespace(true);
    }
    
    @Test
    public void xmlDocumentBuilderChainImplConstructor_name() {
        XmlDocumentBuilderImpl xmlDocumentBuilderChain = new XmlDocumentBuilderImpl("test");
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                xmlDocumentBuilderChain.getDocument());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestPositive() {
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"),
                new ChainReportTypeXmlWriterShowcase("node0").prepare());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestNegative() {
        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDocWrongNode.xml"),
                new ChainReportTypeXmlWriterShowcase("node0").prepare());
    }

}
