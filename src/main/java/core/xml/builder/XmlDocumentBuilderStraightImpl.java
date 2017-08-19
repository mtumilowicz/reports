package core.xml.builder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderStraightImpl extends BaseXmlDocumentBuilderImpl {
    
    public XmlDocumentBuilderStraightImpl(String name) {
        super(name);
    }

    public XmlDocumentBuilderStraightImpl(Document document) {
        super(document);
    }

    @Override
    public XmlElementBuilder createElementBuilder() {
        return new XmlElementBuilderImpl();
    }

    private class XmlElementBuilderImpl extends BaseXmlElementBuilder {

        private final List<Element> innerElements = new LinkedList<>();
        
        private XmlElementBuilderImpl() {
        }

        private XmlElementBuilderImpl(Element elem) {
            super(elem);
        }

        @Override
        public XmlElementBuilderImpl element(String name) {
            Objects.requireNonNull(name);

            return new XmlElementBuilderImpl(createElement(name));
        }

        @Override
        public XmlElementBuilderImpl attribute(String name, String value) {
            super.addAttribute(name, value);

            return this;
        }

        @Override
        public XmlElementBuilderImpl up() {
            return null;
        }

        public XmlElementBuilderImpl addInnerElement(String name) {
            Objects.requireNonNull(name);

            innerElements.add(createElement(name));

            return this;
        }

        public XmlElementBuilderImpl addInnerElement(Element elem) {
            Objects.requireNonNull(elem);

            innerElements.add(elem);

            return this;
        }

        @Override
        public Element build() {
            innerElements.stream().forEachOrdered(super::appendChild);
            innerElements.clear();

            return super.build();
        }
    }
}
