package core.xml.xpath;

import com.google.common.base.Preconditions;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by mtumilowicz on 2017-11-08.
 */
public class XpathSearcher {
    public static Node findNode(Node node, String xpath) throws XPathExpressionException {
        Preconditions.checkArgument(node != null);
        Preconditions.checkArgument(xpath != null);
        
        return (Node) XPathFactory.newInstance().newXPath().compile(xpath).evaluate(node, XPathConstants.NODE);
    }
}
