package core.xml.xpath;

import core.dom.ControlXmlDocument;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathExpressionException;

import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2017-11-08.
 */
public class XpathSearcherTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void nullNode() {
        try {
            XpathSearcher.findNode(null, StringUtils.EMPTY);
        } catch (XPathExpressionException e) {
            assertNull(e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullXpathAndNode() {
        try {
            XpathSearcher.findNode(null, null);
        } catch (XPathExpressionException e) {
            assertNull(e);
        }
    }
    
    @Test
    public void notNullNodeAndNullXpath() {
        try {
            XpathSearcher.findNode(ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"), StringUtils.EMPTY);
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void allDocumentPositive() {
        Document document = ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml");
        try {
            assertEquals(document, XpathSearcher.findNode(document, "."));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void node0_grab() {
        Document document = ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml");
        try {
            assert document != null;
            assertEquals(document.getFirstChild(), XpathSearcher.findNode(document, "node0"));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void node1_grab() {
        Document document = ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml");
        try {
            assert document != null;
            assertEquals(document.getElementsByTagName("node1").item(0), 
                    XpathSearcher.findNode(document, "node0/node1"));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void node1_grab_negative() {
        Document document = ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml");
        try {
            assert document != null;
            assertNotEquals(document.getElementsByTagName("node2").item(0), 
                    XpathSearcher.findNode(document, "node1"));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void node1_1_grab() {
        Document document = ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml");
        try {
            assert document != null;
            assertEquals(document.getElementsByTagName("node1_1").item(0), 
                    XpathSearcher.findNode(document, "node0/node1/node1_1"));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void node1_1_grab_negative() {
        Document document = ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml");
        try {
            assert document != null;
            assertNotEquals(document.getElementsByTagName("node1_2").item(0), 
                    XpathSearcher.findNode(document, "node0/node1/node1_1"));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void allDocumentNegative() {
        try {
            assertNotEquals(XpathSearcher.findNode(
                    ControlXmlDocument.byPath("src/test/resources/extendedXmlDoc.xml"), "."),
                    ControlXmlDocument.byPath("src/test/resources/extendedXmlDocWrongNode.xml"));
        } catch (XPathExpressionException e) {
            assertNotNull(e);
        }
    }
}
