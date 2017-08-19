package xml.writer;

import core.xml.writer.XmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import core.xml.builder.XmlDocumentBuilder.XmlElementBuilder;
import core.xml.builder.XmlDocumentBuilderStraightImpl;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class StraightReportTypeXmlWriter extends XmlWriter {

    private final XmlElementBuilder elementBuilder;

    public StraightReportTypeXmlWriter(String firstNodeName) {
        super(new XmlDocumentBuilderStraightImpl(firstNodeName));
        this.elementBuilder = super.getDocumentBuilder().getElementBuilder();
    }

    @Override
    public Document prepare() {
        Element node1 = elementBuilder.element("node1")
                .attribute("a1", "v1")
                .attribute("a2", "v2")
                .addInnerElement(elementBuilder.element("node1_1")
                        .attribute("node_1_1_a1", "node_1_1_v1").build())
                .addInnerElement("node1_2")
                .build();
        Element node2 = elementBuilder.element("node2").build();
        getDocumentBuilder().addElement(node1)
                .addElement(node2);

        return getDocumentBuilder().getDocument();
    }

    public static void main(String[] args) {
        StraightReportTypeXmlWriter xmlWriter = new StraightReportTypeXmlWriter("node0");

        xmlWriter.print();
    }
}
