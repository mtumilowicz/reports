package core.xml.builder.chain;

import com.google.common.base.Preconditions;
import core.dom.StaticDomDocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class XmlDocumentBuilderImpl implements XmlDocumentBuilder {

    private final Document document;
    private XmlElementBuilder elementBuilder;
    
    public XmlDocumentBuilderImpl(String rootName) {
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
        public XmlElementBuilder element(String name) {
            return new XmlElementBuilderImpl(document.createElement(Objects.requireNonNull(name)), element);
        }

        @Override
        public XmlElementBuilder attribute(String name, String value) {
            element.setAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));

            return this;
        }

        @Override
        public XmlElementBuilder up() {
            return new XmlElementBuilderImpl(up(1));
        }

        @Override
        public Element build() {
            return Objects.requireNonNull(element);
        }

        private Element up(int steps) {
            Preconditions.checkArgument(steps >= 0);

            Node currNode = element;
            int stepCount = 0;
            while (currNode.getParentNode() != Objects.requireNonNull(document) && stepCount <= steps) {
                currNode = currNode.getParentNode();
                stepCount++;
            }
            return Objects.requireNonNull((Element) currNode);
        }
    }
}
