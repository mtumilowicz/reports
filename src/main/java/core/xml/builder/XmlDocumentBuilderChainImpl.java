package core.xml.builder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderChainImpl extends BaseXmlDocumentBuilderImpl {
    
    public XmlDocumentBuilderChainImpl(String name) {
        super(name);
    }

    public XmlDocumentBuilderChainImpl(Document document) {
        super(document);
    }

    @Override
    public XmlElementBuilder createElementBuilder() {
        return new XmlElementBuilderImpl();
    }

    private class XmlElementBuilderImpl extends BaseXmlElementBuilder {

        private XmlElementBuilderImpl() {
        }
        
        private XmlElementBuilderImpl(Element elem) {
            super(elem);
        }

        private XmlElementBuilderImpl(Element elem, Element parentElement) {
            super(elem, parentElement);
        }

        @Override
        public XmlElementBuilderImpl element(String name) {
            Objects.requireNonNull(name);

            return new XmlElementBuilderImpl(createElement(name), getElement());
        }

        @Override
        public XmlElementBuilderImpl attribute(String name, String value) {
            Objects.requireNonNull(name);
            Objects.requireNonNull(value);

            super.addAttribute(name, value);

            return this;
        }

        @Override
        public XmlElementBuilderImpl up() {
            return new XmlElementBuilderImpl(super.up(1));
        }

        @Override
        public BaseXmlElementBuilder addInnerElement(Element elem) {
            throw new UnsupportedOperationException("This operation is not supported in this builder - " +
                    "use chaining of element(...).");
        }

        @Override
        public BaseXmlElementBuilder addInnerElement(String name) {
            throw new UnsupportedOperationException("This operation is not supported in this builder - " +
                    "use chaining of element(...).");
        }
    }
}
