package core.xml.builder;

import core.dom.ControlXmlDocument;
import org.junit.Test;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderChainImplTest {
    
    @Test
    public void xmlDocumentBuilderChainImplConstructor_name() {
        XmlDocumentBuilderChainImpl xmlDocumentBuilderChain = new XmlDocumentBuilderChainImpl("test");
        assertXMLEqual(xmlDocumentBuilderChain.getDocument(),
                ControlXmlDocument.byPath("src/test/resources/onlyRoot.xml"));
    } 

}
