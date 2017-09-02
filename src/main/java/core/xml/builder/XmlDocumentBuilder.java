package core.xml.builder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public interface XmlDocumentBuilder {
    
    Document getDocument();
    
    XmlDocumentBuilder addElement(Element e);

    XmlElementBuilder getElementBuilder();

    XmlElementBuilder element(String name);
}
