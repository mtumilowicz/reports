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
        super(Objects.requireNonNull(name));
    }

    public XmlDocumentBuilderStraightImpl(Document document) {
        super(Objects.requireNonNull(document));
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
            super(Objects.requireNonNull(elem));
        }

        @Override
        public XmlElementBuilderImpl element(String name) {
            return new XmlElementBuilderImpl(createElement(Objects.requireNonNull(name)));
        }

        @Override
        public XmlElementBuilderImpl attribute(String name, String value) {
            super.addAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));

            return this;
        }

        @Override
        public XmlElementBuilderImpl up() {
            throw new UnsupportedOperationException("This operation is not supported in this builder - " +
                    "use addInnerElement(...) in the proper level.");
        }

        public XmlElementBuilderImpl addInnerElement(String name) {
            innerElements.add(createElement(Objects.requireNonNull(name)));

            return this;
        }

        public XmlElementBuilderImpl addInnerElement(Element elem) {
            innerElements.add(Objects.requireNonNull(elem));

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
