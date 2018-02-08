package core.xml.builder.straight;

import core.dom.StaticDomDocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderImpl implements XmlDocumentBuilder {

    private final Document document;
    private XmlElementBuilder elementBuilder;

    XmlDocumentBuilderImpl(String rootName) {
        document = StaticDomDocumentBuilderFactory.create(Objects.requireNonNull(rootName));
    }

    public XmlDocumentBuilder addElement(Element e) {
        document.getDocumentElement().appendChild(Objects.requireNonNull(e));

        return this;
    }

    @Override
    public Document getDocument() {
        return Objects.requireNonNull(document);
    }

    @Override
    public XmlElementBuilder getElementBuilder() {
        if (elementBuilder == null) {
            elementBuilder = createElementBuilder();
        }

        return Objects.requireNonNull(elementBuilder);
    }

    @Override
    public XmlElementBuilder element(String elementName) {
        return getElementBuilder().element(elementName);
    }

    @Override
    public XmlElementBuilder createElementBuilder() {
        return new XmlElementBuilderImpl();
    }

    private class XmlElementBuilderImpl implements XmlElementBuilder {

        private final List<Element> innerElements = new LinkedList<>();

        private final Element element;

        XmlElementBuilderImpl() {
            element = null;
        }

        XmlElementBuilderImpl(Element elem) {
            this.element = Objects.requireNonNull(elem);
        }

        XmlElementBuilderImpl(Element elem, Element parentElement) {
            this.element = Objects.requireNonNull(elem);
            document.getDocumentElement().appendChild(elem);
            if (parentElement != null) {
                parentElement.appendChild(elem);
            }
        }

        @Override
        public XmlElementBuilderImpl element(String name) {
            return new XmlElementBuilderImpl(document.createElement(Objects.requireNonNull(name)));
        }

        @Override
        public XmlElementBuilderImpl attribute(String name, String value) {
            element.setAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));

            return this;
        }

        @Override
        public XmlElementBuilderImpl addInnerElement(String name) {
            innerElements.add(document.createElement(Objects.requireNonNull(name)));

            return this;
        }

        @Override
        public XmlElementBuilderImpl addInnerElement(Element elem) {
            innerElements.add(Objects.requireNonNull(elem));

            return this;
        }

        @Override
        public Element build() {
            innerElements.forEach(innerElement -> element.appendChild(Objects.requireNonNull(innerElement)));
            innerElements.clear();

            return Objects.requireNonNull(element);
        }
    }
}
