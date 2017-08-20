package core.xml.builder;

import org.w3c.dom.Element;

/**
 * Created by mtumilowicz on 2017-08-20.
 */
public interface XmlElementBuilder {
    XmlElementBuilder element(String name);

    XmlElementBuilder attribute(String name, String value);

    XmlElementBuilder up();

    XmlElementBuilder addInnerElement(Element elem);

    XmlElementBuilder addInnerElement(String name);

    Element build();
}
