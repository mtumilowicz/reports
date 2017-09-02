package core.xml.builder.straight;

import core.xml.builder.XmlDocumentBuilderStraightImpl;
import core.xml.writer.XmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class StraightReportTypeXmlWriterShowcase extends XmlWriter {

    public StraightReportTypeXmlWriterShowcase(String rootName) {
        super(new XmlDocumentBuilderStraightImpl(rootName));
    }

    @Override
    public Document prepare() {
        Element node1 = createElement("node1")
                .addInnerElement(
                        createElement("node1_1")
                        .attribute("node1_1_a1", "node1_1_v1")
                        .attribute("node1_1_a2", "node1_1_v2")
                            .addInnerElement(
                                    createElement("node1_1_2")
                                    .attribute("node1_1_2_a1", "node1_1_2_v1")
                                    .build())
                        .build())
                .build();
        
        Element node2 = createElement("node1").build();
        
        addElement(node1)
        .addElement(node2);

        return getDocument();
    }

    public static void main(String[] args) {
        StraightReportTypeXmlWriterShowcase xmlWriter = new StraightReportTypeXmlWriterShowcase("node0");

        xmlWriter.print();
    }
}
