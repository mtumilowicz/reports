package core.xml.builder;

import org.w3c.dom.Element;

import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderChainImpl extends BaseXmlDocumentBuilderImpl {
    
    public XmlDocumentBuilderChainImpl(String rootName) {
        super(Objects.requireNonNull(rootName));
    }

    @Override
    XmlElementBuilder createElementBuilder() {
        return new XmlElementBuilderImpl();
    }

    private class XmlElementBuilderImpl extends BaseXmlElementBuilder {

        private XmlElementBuilderImpl() {
        }
        
        private XmlElementBuilderImpl(Element elem) {
            super(Objects.requireNonNull(elem));
        }

        private XmlElementBuilderImpl(Element elem, Element parentElement) {
            super(elem, parentElement);
        }

        @Override
        public XmlElementBuilderImpl element(String name) {
            return new XmlElementBuilderImpl(createElement(Objects.requireNonNull(name)), getElement());
        }

        @Override
        public XmlElementBuilderImpl attribute(String name, String value) {
            super.addAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));

            return this;
        }

        @Override
        public XmlElementBuilderImpl up() {
            return new XmlElementBuilderImpl(super.up(1));
        }

        @Override
        public BaseXmlElementBuilder addInnerElement(Element elem) {
            throw new UnsupportedOperationException("This operation is not supported in this builder - " +
                    "use chaining of createElement(...).");
        }

        @Override
        public BaseXmlElementBuilder addInnerElement(String name) {
            throw new UnsupportedOperationException("This operation is not supported in this builder - " +
                    "use chaining of createElement(...).");
        }
    }
}
