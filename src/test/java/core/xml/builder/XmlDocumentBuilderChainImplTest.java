package core.xml.builder;

import core.dom.ControlXmlDocument;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLNotEqual;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderChainImplTest {
    
    @Test
    public void xmlDocumentBuilderChainImplConstructor_name() {
        XmlDocumentBuilderChainImpl xmlDocumentBuilderChain = new XmlDocumentBuilderChainImpl("test");
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
                xmlDocumentBuilderChain.getDocument());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestSuccess() {
        XMLUnit.setNormalizeWhitespace(true);
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"),
                new ChainReportTypeXmlWriterShowcase("node0").prepare());
    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestFail() {
        XMLUnit.setNormalizeWhitespace(true);
        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDocWrongNode.xml"),
                new ChainReportTypeXmlWriterShowcase("node0").prepare());
    }

}
