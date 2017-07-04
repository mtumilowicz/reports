package xml.builder;

import core.StaticDocumentBuilderFactory;
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
        Objects.requireNonNull(name);
        
        document = StaticDocumentBuilderFactory.createDocument(name);
    }

    public BaseXmlDocumentBuilderImpl(Document document) {
        Objects.requireNonNull(document);
        
        this.document = document;
    }
    
    public BaseXmlDocumentBuilderImpl addElement(Element e) {
        Objects.requireNonNull(e);
        
        document.getDocumentElement().appendChild(e);
        
        return this;
    }

    @Override
    public Document getDocument() {
        return document;
    }
    
    @Override
    public XmlElementBuilder getElementBuilder() {
        if (elementBuilder == null) {
            elementBuilder = createElementBuilder();
        }
        Objects.requireNonNull(elementBuilder);
        
        return elementBuilder;
    }
    
    public abstract XmlElementBuilder createElementBuilder();

    public abstract class BaseXmlElementBuilder implements XmlElementBuilder {

        private final Element element;

        public BaseXmlElementBuilder() {
            element = null;
        }

        BaseXmlElementBuilder(Element elem) {
            Objects.requireNonNull(elem);
            
            this.element = elem;
        }

        BaseXmlElementBuilder(Element elem, Element parentElement) {
            Objects.requireNonNull(elem);
            
            this.element = elem;
            document.getDocumentElement().appendChild(elem);
            if (parentElement != null) {
                parentElement.appendChild(elem);
            }
        }

        void addAttribute(String name, String value) {
            Objects.requireNonNull(name);
            Objects.requireNonNull(value);

            element.setAttribute(name, value);
        }

        void appendChild(Element innerElement) {
            Objects.requireNonNull(element);

            element.appendChild(innerElement);
        }

        Element createElement(String name) {
            Objects.requireNonNull(name);

            return document.createElement(name);
        }

        @Override
        public Element build() {
            return element;
        }

        Element getElement() {
            return element;
        }

        Element up(int steps) {
            Node currNode = getElement();
            int stepCount = 0;
            while (currNode.getParentNode() != document && stepCount <= steps) {
                currNode = currNode.getParentNode();
                stepCount++;
            }
            return (Element) currNode;
        }
    }
}
