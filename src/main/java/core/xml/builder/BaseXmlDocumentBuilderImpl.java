package core.xml.builder;

import com.google.common.base.Preconditions;
import core.dom.StaticDomDocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-07.
 */
public abstract class BaseXmlDocumentBuilderImpl implements XmlDocumentBuilder {
    
    private final Document document;
    private XmlElementBuilder elementBuilder;
    
    public BaseXmlDocumentBuilderImpl(String name) {
        document = StaticDomDocumentBuilderFactory.create(Objects.requireNonNull(name));
    }

    public BaseXmlDocumentBuilderImpl(Document document) {
        this.document = Objects.requireNonNull(document);
    }
    
    public BaseXmlDocumentBuilderImpl addElement(Element e) {
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
    
    public abstract XmlElementBuilder createElementBuilder();

    abstract class BaseXmlElementBuilder implements XmlElementBuilder {

        private final Element element;

        public BaseXmlElementBuilder() {
            element = null;
        }

        BaseXmlElementBuilder(Element elem) {
            this.element = Objects.requireNonNull(elem);
        }

        BaseXmlElementBuilder(Element elem, Element parentElement) {
            this.element = Objects.requireNonNull(elem);
            document.getDocumentElement().appendChild(elem);
            if (parentElement != null) {
                parentElement.appendChild(elem);
            }
        }

        void addAttribute(String name, String value) {
            element.setAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));
        }

        void appendChild(Element innerElement) {
            element.appendChild(Objects.requireNonNull(innerElement));
        }

        Element createElement(String name) {
            return document.createElement(Objects.requireNonNull(name));
        }

        @Override
        public Element build() {
            return Objects.requireNonNull(element);
        }

        Element getElement() {
            return element;
        }

        Element up(int steps) {
            Preconditions.checkArgument(steps >= 0);
            
            Node currNode = getElement();
            int stepCount = 0;
            while (currNode.getParentNode() != Objects.requireNonNull(document) && stepCount <= steps) {
                currNode = currNode.getParentNode();
                stepCount++;
            }
            return Objects.requireNonNull((Element) currNode);
        }
    }
}
