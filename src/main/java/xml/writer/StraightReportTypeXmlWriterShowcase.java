package xml.writer;

import core.xml.builder.XmlDocumentBuilderStraightImpl;
import core.xml.writer.XmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class StraightReportTypeXmlWriterShowcase extends XmlWriter {

    public StraightReportTypeXmlWriterShowcase(String firstNodeName) {
        super(new XmlDocumentBuilderStraightImpl(firstNodeName));
    }

    @Override
    public Document prepare() {
        Element node1 = createElement("node1")
                .attribute("a1", "v1")
                .attribute("a2", "v2")
                .addInnerElement(createElement("node1_1")
                        .attribute("node_1_1_a1", "node_1_1_v1").build())
                .addInnerElement("node1_2")
                .build();
        Element node2 = createElement("node2").build();
        
        addElement(node1)
        .addElement(node2);

        return getDocument();
    }

    public static void main(String[] args) {
        StraightReportTypeXmlWriterShowcase xmlWriter = new StraightReportTypeXmlWriterShowcase("node0");

        xmlWriter.print();
    }
}
