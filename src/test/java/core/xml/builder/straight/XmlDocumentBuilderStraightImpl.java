package core.xml.builder.straight;

import core.dom.ControlXmlDocument;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderStraightImpl {
    
//    @Test
//    public void xmlDocumentBuilderChainImplConstructor_name() {
//        XmlDocumentBuilderChainImpl xmlDocumentBuilderChain = new XmlDocumentBuilderChainImpl("test");
//        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"),
//                xmlDocumentBuilderChain.getDocument());
//    }

    @Test
    public void xmlDocumentBuilderChainImplFullMethodsTestSuccess() {
        XMLUnit.setNormalizeWhitespace(true);
        new StraightReportTypeXmlWriterShowcase("node0").print();
        assertXMLEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"),
                new StraightReportTypeXmlWriterShowcase("node0").prepare());
    }

//    @Test
//    public void xmlDocumentBuilderChainImplFullMethodsTestFail() {
//        XMLUnit.setNormalizeWhitespace(true);
//        assertXMLNotEqual(ControlXmlDocument.byPath("src/test/resources/extendedXmlDocWrongNode.xml"),
//                new StraightReportTypeXmlWriterShowcase("node0").prepare());
//    }
}
